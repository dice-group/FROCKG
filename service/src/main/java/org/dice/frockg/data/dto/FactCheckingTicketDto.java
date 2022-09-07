package org.dice.frockg.data.dto;

public class FactCheckingTicketDto{
    private String taskId;
    private String status;
    private String error;

    public FactCheckingTicketDto(){}

    public FactCheckingTicketDto(String taskId, String status, String error) {
        this.taskId = taskId;
        this.status = status;
        this.error = error;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "{" +
                "taskId='" + taskId + '\'' +
                ", status='" + status + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
