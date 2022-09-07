package org.dice.frockg.data.dto;

import java.util.ArrayList;

public class CopaalResponseDto {
    private Double veracityValue;
    private String fact;
    private ArrayList<Evidence> piecesOfEvidence;

    public CopaalResponseDto() {
    }

    public CopaalResponseDto(Double veracityValue, String fact, ArrayList<Evidence> piecesOfEvidence) {
        this.veracityValue = veracityValue;
        this.fact = fact;
        this.piecesOfEvidence = piecesOfEvidence;
    }

    public Double getVeracityValue() {
        return veracityValue;
    }

    public void setVeracityValue(Double veracityValue) {
        this.veracityValue = veracityValue;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public ArrayList<Evidence> getPiecesOfEvidence() {
        return piecesOfEvidence;
    }

    public void setPiecesOfEvidence(ArrayList<Evidence> piecesOfEvidence) {
        this.piecesOfEvidence = piecesOfEvidence;
    }
}
