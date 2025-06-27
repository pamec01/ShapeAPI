package dev.danvega.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Shape {

    //typ
    private ShapeType type;

    //trojúhelník
    private Double a;
    private Double b;
    private Double c;

    //obdélník
    private Double width;
    private Double height;

    //kruh
    private Double radius;

    //výpočet
    private Double area;
    private Double circumference;

    private String missingAttribute;



    public ShapeType getType() {
        return type;
    }

    public void setType(ShapeType type) {
        this.type = type;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getCircumference() {
        return circumference;
    }

    public void setCircumference(Double circumference) {
        this.circumference = circumference;
    }

    public String getMissingAttribute() {
        return missingAttribute;
    }

    public void setMissingAttribute(String missingAttribute) {
        this.missingAttribute = missingAttribute;
    }
}
