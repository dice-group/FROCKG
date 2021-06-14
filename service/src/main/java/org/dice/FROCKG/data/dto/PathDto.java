package org.dice.FROCKG.data.dto;

import java.util.ArrayList;
import java.util.List;

public class PathDto implements Comparable<PathDto>{

  private List<RdfTripleDto> path = new ArrayList<RdfTripleDto>();

  private double pathScore;

  private String pathText;

  public PathDto() {

  }

  public PathDto(List<RdfTripleDto> path, double pathScore, String pathText) {
    this.path = path;
    this.pathScore = pathScore;
    this.pathText = pathText;
  }

  public List<RdfTripleDto> getPath() {
    return path;
  }

  public void setPath(List<RdfTripleDto> path) {
    this.path = path;
  }

  public double getPathScore() {
    return pathScore;
  }

  public void setPathScore(double pathScore) {
    this.pathScore = pathScore;
  }

  public String getPathText() {
    return pathText;
  }

  public void setPathText(String pathText) {
    this.pathText = pathText;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((path == null) ? 0 : path.hashCode());
    long temp;
    temp = Double.doubleToLongBits(pathScore);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((pathText == null) ? 0 : pathText.hashCode());
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
    PathDto other = (PathDto) obj;
    if (path == null) {
      if (other.path != null)
        return false;
    } else if (!path.equals(other.path))
      return false;
    if (Double.doubleToLongBits(pathScore) != Double.doubleToLongBits(other.pathScore))
      return false;
    if (pathText == null) {
      if (other.pathText != null)
        return false;
    } else if (!pathText.equals(other.pathText))
      return false;
    return true;
  }

  @Override
  public int compareTo(PathDto pathDto) {

    if(this.pathScore - pathDto.pathScore == 0){
      return 0;
    }

    if(this.pathScore - pathDto.pathScore > 0){
      return -1;
    }else{
      return 1;
    }
  }
}
