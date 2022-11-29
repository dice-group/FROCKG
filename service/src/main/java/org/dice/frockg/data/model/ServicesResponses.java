package org.dice.frockg.data.model;

import javax.persistence.*;

@Entity
@Table(name = "ServicesResponses")
public class ServicesResponses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="TaskID")
    private String taskId;

    @Column(name="Subject")
    private String subject;

    @Column(name="Predicate")
    private String predicate;

    @Column(name="Object")
    private String object;

    @Column(name="CopaalResult", columnDefinition = "NVARCHAR(MAX)")
    private String copaalResult;

    @Column(name="CopaalResultIsReady")
    private Boolean copaalResultIsReady;

    @Column(name="CopaalFacedError")
    private Boolean copaalFacedError;

    @Column(name="CopaalError", columnDefinition = "NVARCHAR(MAX)")
    private String copaalError;

    @Column(name="FactcheckResult", columnDefinition = "NVARCHAR(MAX)")
    private String factcheckResult;

    @Column(name="FactcheckResultIsReady")
    private Boolean factcheckResultIsReady;

    @Column(name="FactcheckFacedError")
    private Boolean factcheckFacedError;

    @Column(name="FactcheckError", columnDefinition = "NVARCHAR(MAX)")
    private String factcheckError;

    public ServicesResponses(){}

    public ServicesResponses(String taskId, String subject, String predicate, String object, String copaalResult, Boolean copaalResultIsReady, Boolean copaalFacedError, String copaalError, String factcheckResult, Boolean factcheckResultIsReady, Boolean factcheckFacedError, String factcheckError) {
        this.taskId = taskId;
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
        this.copaalResult = copaalResult;
        this.copaalResultIsReady = copaalResultIsReady;
        this.copaalFacedError = copaalFacedError;
        this.copaalError = copaalError;
        this.factcheckResult = factcheckResult;
        this.factcheckResultIsReady = factcheckResultIsReady;
        this.factcheckFacedError = factcheckFacedError;
        this.factcheckError = factcheckError;
    }

    public long getId() {
        return id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public String getCopaalResult() {
        return copaalResult;
    }

    public void setCopaalResult(String copaalResult) {
        this.copaalResult = copaalResult;
    }

    public Boolean getCopaalResultIsReady() {

        if(copaalResultIsReady == null){
            return false;
        }
        return copaalResultIsReady;
    }

    public void setCopaalResultIsReady(Boolean copaalResultIsReady) {
        this.copaalResultIsReady = copaalResultIsReady;
    }

    public Boolean getCopaalFacedError() {

        if(copaalFacedError == null){
            return false;
        }
        return copaalFacedError;
    }

    public void setCopaalFacedError(Boolean copaalFacedError) {
        this.copaalFacedError = copaalFacedError;
    }

    public String getCopaalError() {
        return copaalError;
    }

    public void setCopaalError(String copaalError) {
        this.copaalError = copaalError;
    }

    public String getFactcheckResult() {
        return factcheckResult;
    }

    public void setFactcheckResult(String factcheckResult) {
        this.factcheckResult = factcheckResult;
    }

    public Boolean getFactcheckResultIsReady() {

        if(factcheckResultIsReady == null){
            return false;
        }
        return factcheckResultIsReady;
    }

    public void setFactcheckResultIsReady(Boolean factcheckResultIsReady) {
        this.factcheckResultIsReady = factcheckResultIsReady;
    }

    public Boolean getFactcheckFacedError() {
        if(factcheckFacedError == null){
            return false;
        }
        return factcheckFacedError;
    }

    public void setFactcheckFacedError(Boolean factcheckFacedError) {
        this.factcheckFacedError = factcheckFacedError;
    }

    public String getFactcheckError() {
        return factcheckError;
    }

    public void setFactcheckError(String factcheckError) {
        this.factcheckError = factcheckError;
    }
}
