package org.dice.frockg.data.dto;

import java.util.List;

public class Evidence implements Comparable<Evidence> {
    private double score;
    private String evidence;
    private List<SubPathDto> pathEvidences;
    private String verbalization;
    private String sample;
    private String source;

    public Evidence() {
    }

    public Evidence(double score, String evidence,List<SubPathDto> pathEvidences, String verbalization,String sample, String source) {
        this.score = score;
        this.evidence = evidence;
        this.pathEvidences = pathEvidences;
        this.verbalization = verbalization;
        this.sample = sample;
        this.source = source;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public List<SubPathDto> getPathEvidences() {
        return pathEvidences;
    }

    public void setPathEvidences(List<SubPathDto> pathEvidences) {
        this.pathEvidences = pathEvidences;
    }

    public String getVerbalization() {
        return verbalization;
    }

    public void setVerbalization(String verbalization) {
        this.verbalization = verbalization;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    @Override
    public String toString() {
        StringBuilder pathEvidencesSb = new StringBuilder();

        for(int i = 0 ; i < pathEvidences.size();i++){
            pathEvidencesSb.append("{");
            pathEvidencesSb.append("\"property\":\"").append(pathEvidences.get(i).getProperty()).append("\",");
            pathEvidencesSb.append("\"inverse\":");
            if(pathEvidences.get(i).isInverse()){
                pathEvidencesSb.append("true");
            }else{
                pathEvidencesSb.append("false");
            }
            pathEvidencesSb.append("}");
            if(i+1<pathEvidences.size()){
                pathEvidencesSb.append(",");
            }

        }

        return "{" +
                "\"score\":" + score +
                ", \"evidence\":\"" + evidence + '\"' +
                ", \"pathEvidences\":[" + pathEvidencesSb.toString() +"]"+
                ", \"verbalization\":\"" + verbalization + '\"' +
                ", \"source\":\"" + source + '\"' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Evidence evidence1 = (Evidence) o;

        if (Double.compare(evidence1.getScore(), getScore()) != 0) return false;
        if (getEvidence() != null ? !getEvidence().equals(evidence1.getEvidence()) : evidence1.getEvidence() != null)
            return false;
        return getVerbalization() != null ? getVerbalization().equals(evidence1.getVerbalization()) : evidence1.getVerbalization() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getScore());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getEvidence() != null ? getEvidence().hashCode() : 0);
        result = 31 * result + (getVerbalization() != null ? getVerbalization().hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Evidence evidence) {

        if (this.score - evidence.score == 0) {
            return 0;
        }

        if (this.score - evidence.score > 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
