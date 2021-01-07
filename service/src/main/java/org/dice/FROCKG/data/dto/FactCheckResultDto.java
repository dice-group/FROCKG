package org.dice.FROCKG.data.dto;

import java.util.List;

public class FactCheckResultDto {

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
  }

  public FactCheckResultDto() {

  }


  public List<PathDto> getPathList() {
    return pathList;
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
