package org.dice.frockg.mapper;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dice.frockg.data.dto.*;
import org.dice.frockg.data.model.ServicesResponses;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class FacadeResultMapper {
    private static final Logger logger = LogManager.getLogger(FacadeResultMapper.class);
    SubPathDtoMapper subPathDtoMapper = new SubPathDtoMapper();
    public FacadeResultDto map(ServicesResponses serviceResponse){
        FacadeResultDto mappedResult = new FacadeResultDto();
        mappedResult.setStatus("ongoing");

        if(serviceResponse.getTaskId()==null){
            return mappedResult;
        }

        mappedResult.setTaskId(serviceResponse.getTaskId());
        mappedResult.setSubject(serviceResponse.getSubject());
        mappedResult.setObject(serviceResponse.getObject());
        mappedResult.setPredicate(serviceResponse.getPredicate());
        mappedResult.setVeracityScore(0.0);

        if(serviceResponse.getCopaalResultIsReady()){
            Gson g = new Gson();
            try {
                CopaalResponseDto copaalResponse = g.fromJson(serviceResponse.getCopaalResult(), CopaalResponseDto.class);
                for (Evidence evidence : copaalResponse.getPiecesOfEvidence()) {
                    mappedResult.addPiecesOfEvidence(new Evidence(evidence.getScore(), evidence.getEvidence(), subPathDtoMapper.map(evidence.getEvidence()), evidence.getVerbalization(), evidence.getSample(),"Copaal"));
                }

                if (mappedResult.getVeracityScore() < copaalResponse.getVeracityValue()) {
                    mappedResult.setVeracityScore(copaalResponse.getVeracityValue());
                }

                mappedResult.addServices(new ServiceDto("Copaal", copaalResponse.getVeracityValue(),""));
            }catch (Exception ex){
                mappedResult.addServices(new ServiceDto("Copaal", 0.0,ex.getMessage()));
            }
        }else{
            if(serviceResponse.getCopaalFacedError()){
                mappedResult.addServices(new ServiceDto("Copaal", 0.0,serviceResponse.getCopaalError()));
            }
        }


        if(serviceResponse.getFactcheckResultIsReady()){
            Gson g = new Gson();
            try {
                FactcheckResponseDto fcresponse = g.fromJson(serviceResponse.getFactcheckResult(), FactcheckResponseDto.class);
                for (ComplexProof prof : fcresponse.getComplexProofs()) {
                    mappedResult.addPiecesOfEvidence(new Evidence(prof.getTrustworthiness(), prof.getProofPhrase(), new ArrayList<>(), "We found this evidence in our reference knowledge base: " + prof.getProofPhrase(),"" ,"Factcheck"));
                }
                if (mappedResult.getVeracityScore() < fcresponse.getDefactoScore()) {
                    mappedResult.setVeracityScore(fcresponse.getDefactoScore());
                }

                mappedResult.addServices(new ServiceDto("Factcheck", fcresponse.getDefactoScore(),""));
            }catch (Exception ex){
                mappedResult.addServices(new ServiceDto("Factcheck", 0.0,ex.getMessage()));
            }
        }else{
            if(serviceResponse.getFactcheckFacedError()){
                mappedResult.addServices(new ServiceDto("Factcheck", 0.0,serviceResponse.getFactcheckError()));
            }
        }

        if(serviceResponse.getCopaalResultIsReady() && serviceResponse.getFactcheckResultIsReady()){
            mappedResult.setStatus("done");
        }

        mappedResult.setExplanation(generateVerbalization(serviceResponse, mappedResult));

        return mappedResult;
    }

    private String generateVerbalization(ServicesResponses serviceResponse, FacadeResultDto mappedResult) {
        StringBuilder response = new StringBuilder();

        int factcheckCount = 0;
        int copaalCount = 0;

        for(Evidence ev :mappedResult.getPiecesOfEvidence()){
            if(ev.getSource().equalsIgnoreCase("Copaal")){
                // copaal
                copaalCount = copaalCount + 1;
            }else{
                factcheckCount = factcheckCount + 1;
            }
        }

        response.append("\n\n");

        if(!serviceResponse.getFactcheckResultIsReady() && !serviceResponse.getCopaalResultIsReady()){
            // no result is ready
            response.append("There are no results");
        }else {
            response.append("I checked the following claim:\n");
            response.append(getlabel(mappedResult.getSubject())+" "+getlabel(mappedResult.getPredicate())+" "+getlabel(mappedResult.getObject().toString())+"\n\n");

            if (serviceResponse.getFactcheckResultIsReady() && serviceResponse.getCopaalResultIsReady()) {
                if(copaalCount <= 0 && factcheckCount <= 0){
                    response.append("In my knowledge base, I could not found any evidences\n");
                }else{
                    if(copaalCount <= 0 && factcheckCount > 0){
                        // just fact check has result
                        response.append("In my knowledge base, I could not found the fact that backs up the claim\n\n");
                        response.append("but, I found the following pieces of text that could serve as evidence:\n");
                        for(Evidence ev :mappedResult.getPiecesOfEvidence()){
                            if(ev.getSource().equalsIgnoreCase("Factcheck")){
                                // Factcheck
                                response.append(ev.getVerbalization().replace("We found this evidence in our reference knowledge base:","").replace("-LRB-","").replace("-RRB-","") + " \n");
                            }
                        }
                    }
                    if(copaalCount > 0 && factcheckCount <= 0){
                        // just copaal has result
                        response.append("In my knowledge base, I found the following fact that backs up the claim\n\n");
                        // results are generate the text
                        for(Evidence ev :mappedResult.getPiecesOfEvidence()){
                            if(ev.getSource().equalsIgnoreCase("Copaal")){
                                // copaal
                                response.append(ev.getVerbalization().replace("We found this evidence in our reference knowledge base:","").replace("-LRB-","").replace("-RRB-","") + " \n");
                            }
                        }
                        response.append("\nI could not found pieces of text that could serve as evidence");
                    }
                    if(copaalCount > 0 && factcheckCount > 0){
                        // both has result
                        response.append("In my knowledge base, I found the following fact that backs up the\n\n");
                        // results are generate the text
                        for(Evidence ev :mappedResult.getPiecesOfEvidence()){
                            if(ev.getSource().equalsIgnoreCase("Copaal")){
                                // copaal
                                response.append(ev.getVerbalization().replace("We found this evidence in our reference knowledge base:","").replace("-LRB-","").replace("-RRB-","") + " \n");
                            }
                        }
                        response.append("\nIn addition, I found the following pieces of text that could serve as evidence:\n");
                        for(Evidence ev :mappedResult.getPiecesOfEvidence()){
                            if(ev.getSource().equalsIgnoreCase("Factcheck")){
                                // Factcheck
                                response.append(ev.getVerbalization().replace("We found this evidence in our reference knowledge base:","").replace("-LRB-","").replace("-RRB-","") + " \n");
                            }
                        }
                    }
                }

            }else{
                // on result is ready
                if (serviceResponse.getFactcheckResultIsReady() && !serviceResponse.getCopaalResultIsReady()) {
                    if (factcheckCount > 0) {
                        // just fact check result is ready
                        response.append("In my knowledge base, I try finding the facts that backs up the claim\n");
                        response.append("but it needs more time, I found the following pieces of text that could serve as evidence:\n");
                        for (Evidence ev : mappedResult.getPiecesOfEvidence()) {
                            if (ev.getSource().equalsIgnoreCase("Factcheck")) {
                                // Factcheck
                                response.append(ev.getVerbalization().replace("We found this evidence in our reference knowledge base:", "").replace("-LRB-", "").replace("-RRB-", "") + " \n");
                            }
                        }
                    } else {
                        response.append("In my knowledge base, I try finding the facts that backs up the claim\n");
                        response.append("but it needs more time, I could not found pieces of text that could serve as evidence\n");
                    }
                }
                if(serviceResponse.getCopaalResultIsReady() && !serviceResponse.getFactcheckResultIsReady()){
                    // just copaal is ready
                    if(copaalCount>0){
                        // just copaal has result
                        response.append("In my knowledge base, I found the following fact that backs up the claim\n");
                        // results are generate the text
                        for(Evidence ev :mappedResult.getPiecesOfEvidence()){
                            if(ev.getSource().equalsIgnoreCase("Copaal")){
                                // copaal
                                response.append(ev.getVerbalization().replace("We found this evidence in our reference knowledge base:","").replace("-LRB-","").replace("-RRB-","") + " \n");
                            }
                        }
                        response.append("\nI am searching for pieces of text that could serve as evidence");
                    }else{
                        response.append("In my knowledge base, I could not found the fact that backs up the claim\n");
                        // results are generate the text
                        for(Evidence ev :mappedResult.getPiecesOfEvidence()){
                            if(ev.getSource().equalsIgnoreCase("Copaal")){
                                // copaal
                                response.append(ev.getVerbalization().replace("We found this evidence in our reference knowledge base:","").replace("-LRB-","").replace("-RRB-","") + " \n");
                            }
                        }
                        response.append("\nbut, I am searching for pieces of text that could serve as evidence");
                    }
                }
            }
        }

        return  response.toString();
    }

    private String getlabel(String subject) {
        String[] parts = subject.split("/");
        if(parts.length > 0){
            return parts[parts.length-1];
        }else{
            return "";
        }
    }

}
