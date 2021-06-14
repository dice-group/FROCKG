package org.dice.FROCKG.data.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FactCheckResultDto {

  private final static int maximumNumberOfProofs = 3;

  private double facadeScore;
  private String explanation;
  private List<PathDto> pathList;
  private double graphScore;
  private RdfTripleDto inputTriple;
  private String taskid;
  private String filedata;
  private double defactoScore;
  private List<ComplexProofDto> complexProofs;
  private String subject;
  private String predicate;
  private String object;

  private boolean corpusFactCheckIsSucceed = true;
  private String corpusFactCheckErrorMessage;

  private boolean graphBaseFactCheckIsSucceed = true;
  private String graphBaseFactCheckErrorMessage;


  public void updateFacadeScore(){
    this.facadeScore = Math.max(this.defactoScore,this.graphScore);
  }
  public void updateIfNotNull(FactCheckResultDto forUpdate) {
    if (forUpdate.pathList != null && forUpdate.pathList.size() > 0) {
      this.pathList = forUpdate.pathList;
    }

    if (forUpdate.graphScore != 0) {
      this.graphScore = forUpdate.graphScore;
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
    this.facadeScore = Math.max(this.defactoScore,this.graphScore);
    StringBuilder sb = new StringBuilder();
    //sb.append("We found several sources for the following evidence:\n");
    sb.append("we check this fact\n");
    sb.append(subject+" "+predicate+" "+object+"\n");

    if(this.facadeScore<=0){
      sb.append("we could not find any evidence\n");
      this.explanation = sb.toString();
      return;
    }

    int pathnumber = 0;

    //results from Graph fact check
    if(pathList!=null) {
      sb.append("We found the following evidence in our reference knowledge base:\n");
      pathnumber=Math.min(pathList.size(), maximumNumberOfProofs);
      Collections.sort(pathList);
    }else{
      sb.append("We did not find any evidence in our reference knowledge base\n");
    }

    for (int i = 0 ; i < pathnumber ; i++){
      sb.append(pathList.get(i).getPathText());
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

  public List<PathDto> getPathList() {
    return pathList;
  }


  public double getFacadeScore() {
    return facadeScore;
  }

  public void setPathList(List<PathDto> pathList) {
    this.pathList = pathList;
  }


  public double getGraphScore() {
    return graphScore;
  }


  public void setGraphScore(double graphScore) {
    this.graphScore = graphScore;
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


  public List<ComplexProofDto> getComplexProofs() {
    return complexProofs;
  }


  public void setComplexProofs(List<ComplexProofDto> complexProofs) {
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((complexProofs == null) ? 0 : complexProofs.hashCode());
    long temp;
    temp = Double.doubleToLongBits(defactoScore);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((filedata == null) ? 0 : filedata.hashCode());
    temp = Double.doubleToLongBits(graphScore);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((inputTriple == null) ? 0 : inputTriple.hashCode());
    result = prime * result + ((object == null) ? 0 : object.hashCode());
    result = prime * result + ((pathList == null) ? 0 : pathList.hashCode());
    result = prime * result + ((predicate == null) ? 0 : predicate.hashCode());
    result = prime * result + ((subject == null) ? 0 : subject.hashCode());
    result = prime * result + ((taskid == null) ? 0 : taskid.hashCode());
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    FactCheckResultDto other = (FactCheckResultDto) obj;
    if (complexProofs == null) {
      if (other.complexProofs != null)
        return false;
    } else if (!complexProofs.equals(other.complexProofs))
      return false;
    if (Double.doubleToLongBits(defactoScore) != Double.doubleToLongBits(other.defactoScore))
      return false;
    if (filedata == null) {
      if (other.filedata != null)
        return false;
    } else if (!filedata.equals(other.filedata))
      return false;
    if (Double.doubleToLongBits(graphScore) != Double.doubleToLongBits(other.graphScore))
      return false;
    if (inputTriple == null) {
      if (other.inputTriple != null)
        return false;
    } else if (!inputTriple.equals(other.inputTriple))
      return false;
    if (object == null) {
      if (other.object != null)
        return false;
    } else if (!object.equals(other.object))
      return false;
    if (pathList == null) {
      if (other.pathList != null)
        return false;
    } else if (!pathList.equals(other.pathList))
      return false;
    if (predicate == null) {
      if (other.predicate != null)
        return false;
    } else if (!predicate.equals(other.predicate))
      return false;
    if (subject == null) {
      if (other.subject != null)
        return false;
    } else if (!subject.equals(other.subject))
      return false;
    if (taskid == null) {
      if (other.taskid != null)
        return false;
    } else if (!taskid.equals(other.taskid))
      return false;
    return true;
  }


  @Override
  public String toString() {
    return "FactCheckResultDto [pathList=" + pathList + ", graphScore=" + graphScore
        + ", inputTriple=" + inputTriple + ", taskid=" + taskid + ", filedata=" + filedata
        + ", defactoScore=" + defactoScore + ", complexProofs=" + complexProofs + ", subject="
        + subject + ", predicate=" + predicate + ", object=" + object + "]";
  }



}
