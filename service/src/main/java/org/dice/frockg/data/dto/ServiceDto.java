package org.dice.frockg.data.dto;

public class ServiceDto {
    private String name;
    private double veracityScore;
    private String error;


    public ServiceDto(){}
    public ServiceDto(String name, double veracityScore, String error) {
        this.name = name;
        this.veracityScore = veracityScore;
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVeracityScore() {
        return veracityScore;
    }

    public void setVeracityScore(double veracityScore) {
        this.veracityScore = veracityScore;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        if(error!=null){
            error = error.replace("\"","");
        }
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"").append(name).append('\"');
        sb.append(", \"veracityScore\":").append(veracityScore);
        sb.append(", \"error\":\"").append(error).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
