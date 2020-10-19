package org.dice.FROCKG.data.dto;

import java.util.List;

public class FactCheckResultDto {

  private List<PathDto> pathList;
  private double graphScore;
  private RdfTripleDto inputTriple;

  public FactCheckResultDto() {

  }

  public FactCheckResultDto(List<PathDto> pathList, double graphScore, RdfTripleDto inputTriple) {
    this.pathList = pathList;
    this.graphScore = graphScore;
    this.inputTriple = inputTriple;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(graphScore);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((inputTriple == null) ? 0 : inputTriple.hashCode());
    result = prime * result + ((pathList == null) ? 0 : pathList.hashCode());
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
    if (Double.doubleToLongBits(graphScore) != Double.doubleToLongBits(other.graphScore))
      return false;
    if (inputTriple == null) {
      if (other.inputTriple != null)
        return false;
    } else if (!inputTriple.equals(other.inputTriple))
      return false;
    if (pathList == null) {
      if (other.pathList != null)
        return false;
    } else if (!pathList.equals(other.pathList))
      return false;
    return true;
  }

}
