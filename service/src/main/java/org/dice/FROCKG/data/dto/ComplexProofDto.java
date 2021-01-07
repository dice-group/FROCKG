package org.dice.FROCKG.data.dto;

public class ComplexProofDto {
  private String website;
  private String proofPhrase;

  public ComplexProofDto() {

  }

  public ComplexProofDto(String website, String proofPhrase) {
    super();
    this.website = website;
    this.proofPhrase = proofPhrase;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((proofPhrase == null) ? 0 : proofPhrase.hashCode());
    result = prime * result + ((website == null) ? 0 : website.hashCode());
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
    ComplexProofDto other = (ComplexProofDto) obj;
    if (proofPhrase == null) {
      if (other.proofPhrase != null)
        return false;
    } else if (!proofPhrase.equals(other.proofPhrase))
      return false;
    if (website == null) {
      if (other.website != null)
        return false;
    } else if (!website.equals(other.website))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "ComplexProofDto [website=" + website + ", proofPhrase=" + proofPhrase + "]";
  }


}
