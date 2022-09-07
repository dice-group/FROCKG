package org.dice.frockg.data.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FacadeResultDto {
    private String taskId;
    private String status;
    private String subject;
    private String predicate;
    private String object;
    private Double veracityScore;
    private String explanation;
    private String error;
    private List<Evidence> piecesOfEvidence;
    private List<ServiceDto> services;

    public FacadeResultDto(){
        piecesOfEvidence = new ArrayList<>();
        services = new ArrayList<>();
    }

    public FacadeResultDto(String taskId, String status, String subject, String predicate, String object, Double veracityScore, String explanation, String error, List<Evidence> piecesOfEvidence, List<ServiceDto> services) {
        this.taskId = taskId;
        this.status = status;
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
        this.veracityScore = veracityScore;
        this.explanation = explanation;
        this.error = error;
        this.piecesOfEvidence = piecesOfEvidence;
        this.services = services;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPredicate() {
        return predicate;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Double getVeracityScore() {
        return veracityScore;
    }

    public void setVeracityScore(Double veracityScore) {
        this.veracityScore = veracityScore;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getError() {
        return error;
    }

    public List<Evidence> getPiecesOfEvidence() {
        return piecesOfEvidence;
    }

    public List<ServiceDto> getServices() {
        return services;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void addPiecesOfEvidence(Evidence evidence){
        if( piecesOfEvidence == null){
            piecesOfEvidence = new ArrayList<>();
        }
        piecesOfEvidence.add(evidence);
    }

    public void addServices(ServiceDto service){
        if(services == null){
            services = new ArrayList<>();
        }
        services.add(service);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"taskId\":\"").append(taskId).append('\"');
        sb.append(",\"status\":\"").append(status).append('\"');
        sb.append(",\"subject\":\"").append(subject).append('\"');
        sb.append(",\"predicate\":\"").append(predicate).append('\"');
        sb.append(",\"object\":\"").append(object).append('\"');
        sb.append(",veracityScore:").append(veracityScore);
        sb.append(",\"explanation\":\"").append(explanation).append('\"');
        sb.append(",\"error\":\"").append(error).append('\"');
        sb.append(",\"piecesOfEvidence\":[");
        for(int i = 0 ; i < piecesOfEvidence.size() ; i++) {
            sb.append(piecesOfEvidence.get(i).toString());
            if(i+1<piecesOfEvidence.size()){sb.append(",");}
        }
        sb.append("]");
        sb.append(",\"services\":").append(services);
        sb.append('}');
        return sb.toString();
    }

    public void generateExplanation() {
        /*this.veracityScore = Math.max(this.defactoScore,this.veracityValue);
        StringBuilder sb = new StringBuilder();
        //sb.append("We found several sources for the following evidence:\n");
        sb.append("we check this fact\n");
        sb.append(fact+"\n");

        if(this.facadeScore<=0){
            sb.append("we could not find any evidence\n");
            this.explanation = sb.toString();
            return;
        }

        int pathnumber = 0;

        //results from Graph fact check
        if(piecesOfEvidence!=null) {
            sb.append("We found the following evidence in our reference knowledge base:\n");
            pathnumber=Math.min(piecesOfEvidence.size(), maximumNumberOfProofs);
            Collections.sort(piecesOfEvidence);
        }else{
            sb.append("We did not find any evidence in our reference knowledge base\n");
        }

        for (int i = 0 ; i < pathnumber ; i++){
            sb.append(piecesOfEvidence.get(i).toString());
            sb.append("\n");
        }

        //results from text based fact check
        int textProofnumber = 0;
        if(complexProofs!=null) {
            sb.append("We found the following evidence in our reference corpus:\n");
            textProofnumber=Math.min(complexProofs.size(), maximumNumberOfProofs);
            Collections.sort(complexProofs);
        }else{
            sb.append("We did not find any evidence in our reference corpus\n");
        }

        for (int i = 0 ; i < textProofnumber ; i++){
            sb.append(complexProofs.get(i).getProofPhrase().replace("-LRB-","(").replace("-RRB-",")"));
            sb.append("\n");
        }

        this.explanation = sb.toString();*/
    }


}
