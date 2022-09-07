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
                    mappedResult.addPiecesOfEvidence(new Evidence(evidence.getScore(), evidence.getEvidence(), subPathDtoMapper.map(evidence.getEvidence()), evidence.getVerbalization(), "Copaal"));
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
                    mappedResult.addPiecesOfEvidence(new Evidence(prof.getTrustworthiness(), prof.getProofPhrase(), new ArrayList<>(), "We found this evidence in our reference knowledge base: " + prof.getProofPhrase(), "Factcheck"));
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

        return mappedResult;
    }
}
