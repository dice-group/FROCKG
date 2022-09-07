package org.dice.frockg.data.dto;

import java.util.Objects;

public class ComplexProof implements Comparable<ComplexProof>{
  private String website;
  private String proofPhrase;
  private double trustworthiness;

  public ComplexProof() {

  }

  public ComplexProof(String website, String proofPhrase, double trustworthiness) {
    super();
    this.website = website;
    this.proofPhrase = proofPhrase;
    this.trustworthiness = trustworthiness;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public String getProofPhrase() {
    return proofPhrase;
  }

  public void setProofPhrase(String proofPhrase) {
    this.proofPhrase = proofPhrase;
  }

  public double getTrustworthiness() {
    return trustworthiness;
  }

  public void setTrustworthiness(double trustworthiness) {
    this.trustworthiness = trustworthiness;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ComplexProof that = (ComplexProof) o;
    return Double.compare(that.getTrustworthiness(), getTrustworthiness()) == 0 &&
            Objects.equals(getWebsite(), that.getWebsite()) &&
            Objects.equals(getProofPhrase(), that.getProofPhrase());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getWebsite(), getProofPhrase(), getTrustworthiness());
  }

  @Override
  public String toString() {
    return "ComplexProofDto{" +
            "website='" + website + '\'' +
            ", trustworthiness=" + trustworthiness +'\'' +
            ", proofPhrase='" + proofPhrase +

            '}';
  }

  @Override
  public int compareTo(ComplexProof complexProof) {
    if(this.trustworthiness - complexProof.trustworthiness == 0){
      return 0;
    }

    if(this.trustworthiness - complexProof.trustworthiness > 0){
      return -1;
    }else{
      return 1;
    }
  }
}
