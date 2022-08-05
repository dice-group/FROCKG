package org.dice.frockg.data.dto;

public class Evidence implements Comparable<Evidence> {
    private double score;
    private String evidence;
    private String verbalization;

    public Evidence() {
    }

    public Evidence(double score, String evidence, String verbalization) {
        this.score = score;
        this.evidence = evidence;
        this.verbalization = verbalization;
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

    public String getVerbalization() {
        return verbalization;
    }

    public void setVerbalization(String verbalization) {
        this.verbalization = verbalization;
    }

    @Override
    public String toString() {
        return "Evidence{" +
                "score=" + score +
                ", evidence='" + evidence + '\'' +
                ", verbalization='" + verbalization + '\'' +
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
