package org.dice.frockg.data.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class FactcheckResponseDto implements Serializable {
    public String taskid;
    public String filedata;
    public double defactoScore;
    public ArrayList<ComplexProof> complexProofs;
    public String subject;
    public String predicate;
    public String object;
    public FactcheckResponseDto() {
        super();
    }

    public FactcheckResponseDto(String taskid, String filedata) {

        this.taskid = taskid;
        this.filedata = filedata;
        this.defactoScore = 0;
        this.complexProofs = new ArrayList<>();
    }


    public void setDefactoScore(double defactoScore) {
        this.defactoScore = defactoScore;
    }

    public String getTaskid() { return taskid; }

    public String getFile() {
        return filedata;
    }

    public double getDefactoScore() {
        return defactoScore;
    }

    public ArrayList<ComplexProof> getComplexProofs() {
        return complexProofs;
    }

    public void setComplexProofs(ArrayList<ComplexProof> complexProofs) {
        this.complexProofs = complexProofs;
    }
}

