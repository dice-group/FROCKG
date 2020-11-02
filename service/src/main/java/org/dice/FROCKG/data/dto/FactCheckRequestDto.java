package org.dice.FROCKG.data.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.dice.FROCKG.customvalidator.IUriConstraint;

public class FactCheckRequestDto {

  @IUriConstraint private String subject;

  @IUriConstraint private String object;

  @IUriConstraint private String predicate;

  @NotNull
  @Min(2)
  @Max(3)
  private Integer pathlength = 2;

  private boolean virtualtype;

  private boolean verbalize;

  public FactCheckRequestDto() {}

  public FactCheckRequestDto(
      String subject,
      String object,
      String predicate,
      Integer pathlength,
      boolean virtualtype,
      boolean verbalize) {
    super();
    this.subject = subject;
    this.object = object;
    this.predicate = predicate;
    this.pathlength = pathlength;
    this.virtualtype = virtualtype;
    this.verbalize = verbalize;
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

  public Integer getPathlength() {
    return pathlength;
  }

  public void setPathlength(Integer pathlength) {
    this.pathlength = pathlength;
  }

  public boolean isVirtualtype() {
    return virtualtype;
  }

  public void setVirtualtype(boolean virtualtype) {
    this.virtualtype = virtualtype;
  }

  public boolean isVerbalize() {
    return verbalize;
  }

  public void setVerbalize(boolean verbalize) {
    this.verbalize = verbalize;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (virtualtype ? 1231 : 1237);
    result = prime * result + ((object == null) ? 0 : object.hashCode());
    result = prime * result + ((pathlength == null) ? 0 : pathlength.hashCode());
    result = prime * result + ((predicate == null) ? 0 : predicate.hashCode());
    result = prime * result + ((subject == null) ? 0 : subject.hashCode());
    result = prime * result + (verbalize ? 1231 : 1237);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    FactCheckRequestDto other = (FactCheckRequestDto) obj;
    if (virtualtype != other.virtualtype) return false;
    if (object == null) {
      if (other.object != null) return false;
    } else if (!object.equals(other.object)) return false;
    if (pathlength == null) {
      if (other.pathlength != null) return false;
    } else if (!pathlength.equals(other.pathlength)) return false;
    if (predicate == null) {
      if (other.predicate != null) return false;
    } else if (!predicate.equals(other.predicate)) return false;
    if (subject == null) {
      if (other.subject != null) return false;
    } else if (!subject.equals(other.subject)) return false;
    if (verbalize != other.verbalize) return false;
    return true;
  }
}
