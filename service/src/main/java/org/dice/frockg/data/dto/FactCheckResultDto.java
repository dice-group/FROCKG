package org.dice.frockg.data.dto;

import java.util.Collections;
import java.util.List;

public class FactCheckResultDto {

  private final static int maximumNumberOfProofs = 3;

  private double facadeScore;
  private String explanation;
  //private List<PathDto> pathList;
  private List<Evidence> piecesOfEvidence;
  private String fact;
  private double veracityValue;
  private RdfTripleDto inputTriple;
  private String taskid;
  private String filedata;
  private double defactoScore;
  private List<ComplexProof> complexProofs;
  private String subject;
  private String predicate;
  private String object;

  private boolean corpusFactCheckIsSucceed = true;
  private String corpusFactCheckErrorMessage;

  private boolean graphBaseFactCheckIsSucceed = true;
  private String graphBaseFactCheckErrorMessage;


  public void updateFacadeScore(){
    this.facadeScore = Math.max(this.defactoScore,this.veracityValue);
  }
  public void updateIfNotNull(FactCheckResultDto forUpdate) {
    if (forUpdate.piecesOfEvidence != null && forUpdate.piecesOfEvidence.size() > 0) {
      this.piecesOfEvidence = forUpdate.piecesOfEvidence;
    }

    if(forUpdate.fact != null ){
      this.fact = forUpdate.fact;
    }

    if (forUpdate.veracityValue != 0) {
      this.veracityValue = forUpdate.veracityValue;
    }

    if (forUpdate.inputTriple != null) {
      this.inputTriple = forUpdate.inputTriple;
    }

    if (forUpdate.taskid != null) {
      this.taskid = forUpdate.taskid;
    }

    if (forUpdate.filedata != null) {
      this.filedata = forUpdate.filedata;
    }

    if (forUpdate.defactoScore != 0) {
      this.defactoScore = forUpdate.defactoScore;
    }

    if (forUpdate.complexProofs != null && forUpdate.complexProofs.size() > 0) {
      this.complexProofs = forUpdate.complexProofs;
    }

    if (forUpdate.subject != null) {
      this.subject = forUpdate.subject;
    }

    if (forUpdate.object != null) {
      this.object = forUpdate.object;
    }

    if (forUpdate.predicate != null) {
      this.predicate = forUpdate.predicate;
    }

    if (forUpdate.corpusFactCheckIsSucceed != true) {
      this.corpusFactCheckIsSucceed = forUpdate.corpusFactCheckIsSucceed;
    }
    if (forUpdate.corpusFactCheckErrorMessage != null) {
      this.corpusFactCheckErrorMessage = forUpdate.corpusFactCheckErrorMessage;
    }

    if (forUpdate.graphBaseFactCheckIsSucceed != true) {
      this.graphBaseFactCheckIsSucceed = forUpdate.graphBaseFactCheckIsSucceed;
    }
    if (forUpdate.graphBaseFactCheckErrorMessage != null) {
      this.graphBaseFactCheckErrorMessage = forUpdate.graphBaseFactCheckErrorMessage;
    }
  }

/*  We found several sources for the following evidence:

          "AAAAAAAA"
  We found the following evidence in our reference knowledge base:
          "B1B1B1B1B1B1B1B1"
          "B2B2B2B2B2B2B2"
  We found the following evidence in our reference corpus:
          "C1C1C1C1C1C1C1C1"
          "C2C2C2C2C2C2C2"*/

  public void generateExplanation(){
    this.facadeScore = Math.max(this.defactoScore,this.veracityValue);
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

    this.explanation = sb.toString();
  }

  public FactCheckResultDto() {}

  public String getExplanation() {
    return explanation;
  }


  public List<Evidence> getPiecesOfEvidence() {
    return piecesOfEvidence;
  }

  public void setPiecesOfEvidence(List<Evidence> piecesOfEvidence) {
    this.piecesOfEvidence = piecesOfEvidence;
  }

  public String getFact() {
    return fact;
  }

  public void setFact(String fact) {
    this.fact = fact;
  }

  public double getFacadeScore() {
    return facadeScore;
  }


  public double getVeracityValue() {
    return veracityValue;
  }

  public void setVeracityValue(double veracityValue) {
    this.veracityValue = veracityValue;
  }

  public RdfTripleDto getInputTriple() {
    return inputTriple;
  }


  public void setInputTriple(RdfTripleDto inputTriple) {
    this.inputTriple = inputTriple;
  }


  public String getTaskid() {
    return taskid;
  }


  public void setTaskid(String taskid) {
    this.taskid = taskid;
  }


  public String getFiledata() {
    return filedata;
  }


  public void setFiledata(String filedata) {
    this.filedata = filedata;
  }


  public double getDefactoScore() {
    return defactoScore;
  }


  public void setDefactoScore(double defactoScore) {
    this.defactoScore = defactoScore;
  }


  public List<ComplexProof> getComplexProofs() {
    return complexProofs;
  }


  public void setComplexProofs(List<ComplexProof> complexProofs) {
    this.complexProofs = complexProofs;
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


  public boolean isCorpusFactCheckIsSucceed() {
    return corpusFactCheckIsSucceed;
  }

  public void setCorpusFactCheckIsSucceed(boolean corpusFactCheckIsSucceed) {
    this.corpusFactCheckIsSucceed = corpusFactCheckIsSucceed;
  }

  public String getCorpusFactCheckErrorMessage() {
    return corpusFactCheckErrorMessage;
  }

  public void setCorpusFactCheckErrorMessage(String corpusFactCheckErrorMessage) {
    this.corpusFactCheckErrorMessage = corpusFactCheckErrorMessage;
  }

  public boolean isGraphBaseFactCheckIsSucceed() {
    return graphBaseFactCheckIsSucceed;
  }

  public void setGraphBaseFactCheckIsSucceed(boolean graphBaseFactCheckIsSucceed) {
    this.graphBaseFactCheckIsSucceed = graphBaseFactCheckIsSucceed;
  }

  public String getGraphBaseFactCheckErrorMessage() {
    return graphBaseFactCheckErrorMessage;
  }

  public void setGraphBaseFactCheckErrorMessage(String graphBaseFactCheckErrorMessage) {
    this.graphBaseFactCheckErrorMessage = graphBaseFactCheckErrorMessage;
  }






}
