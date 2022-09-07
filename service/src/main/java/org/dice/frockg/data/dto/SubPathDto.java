package org.dice.frockg.data.dto;

public class SubPathDto {
    private String property;
    private boolean inverse;

    public SubPathDto(){}
    public SubPathDto(String property, boolean inverse) {
        this.property = property;
        this.inverse = inverse;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public boolean isInverse() {
        return inverse;
    }

    public void setInverse(boolean inverse) {
        this.inverse = inverse;
    }

    @Override
    public String toString() {
        return "{" +
                "\"property\":\"" + property + '\"' +
                ", \"inverse\":" + inverse +
                '}';
    }
}
