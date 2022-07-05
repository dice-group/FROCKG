package org.dice.FROCKG.data.dto;

import org.dice.FROCKG.customvalidator.IUriConstraint;

public class FactCheckRequestDto {

  @IUriConstraint private String subject;

  @IUriConstraint private String object;

  @IUriConstraint private String predicate;

  public FactCheckRequestDto() {}

  public FactCheckRequestDto(
      String subject,
      String object,
      String predicate) {
    super();
    this.subject = subject;
    this.object = object;
    this.predicate = predicate;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getObject() {
    return object;
  }

  public void setObject(String object) {
    this.object = object;
  }

  public String getPredicate() {
    return predicate;
  }

  public void setPredicate(String predicate) {
    this.predicate = predicate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FactCheckRequestDto that = (FactCheckRequestDto) o;

    if (getSubject() != null ? !getSubject().equals(that.getSubject()) : that.getSubject() != null) return false;
    if (getObject() != null ? !getObject().equals(that.getObject()) : that.getObject() != null) return false;
    return getPredicate() != null ? getPredicate().equals(that.getPredicate()) : that.getPredicate() == null;
  }

  @Override
  public int hashCode() {
    int result = getSubject() != null ? getSubject().hashCode() : 0;
    result = 31 * result + (getObject() != null ? getObject().hashCode() : 0);
    result = 31 * result + (getPredicate() != null ? getPredicate().hashCode() : 0);
    return result;
  }
}
