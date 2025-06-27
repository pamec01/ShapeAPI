package dev.danvega.util;

import dev.danvega.model.Shape;
import dev.danvega.model.ShapeType;

public class ShapeCalculator {

    public static Shape calculateMissingAttribute(Shape shape) {
        if (shape.getType() == null) {
            if (shape.getRadius() != null) {
                shape.setType(ShapeType.CIRCLE);
            } else if (shape.getA() != null && shape.getB() != null && shape.getC() != null) {
                shape.setType(ShapeType.TRIANGLE);
            } else if ((shape.getWidth() != null || shape.getHeight() != null) && (shape.getArea() != null || shape.getCircumference() != null)) {
                shape.setType(ShapeType.RECTANGLE);
            }
            shape.setMissingAttribute("type");
        }


        if (shape == null || shape.getType() == null) {
            return shape;
        }

        switch (shape.getType()) {
            case TRIANGLE -> calculateTriangle(shape);
            case RECTANGLE -> calculateRectangle(shape);
            case CIRCLE -> calculateCircle(shape);
        }

        return shape;
    }

    private static void calculateTriangle(Shape shape) {
        Double a = shape.getA();
        Double b = shape.getB();
        Double c = shape.getC();
        Double area = shape.getArea();
        Double circumference = shape.getCircumference();

        //obvod
        if (a != null && b != null && c != null && circumference == null) {
            shape.setCircumference(a + b + c);
            shape.setMissingAttribute("circumference");
        }

        //strany
        if (circumference != null) {
            if (a == null && b != null && c != null) {
                shape.setA(circumference - b - c);
                shape.setMissingAttribute("a");
            } else if (b == null && a != null && c != null) {
                shape.setB(circumference - a - c);
                shape.setMissingAttribute("b");
            } else if (c == null && a != null && b != null) {
                shape.setC(circumference - a - b);
                shape.setMissingAttribute("c");
            }
        }

        //obsah
        if (a != null && b != null && c != null && area == null) {
            double s = (a + b + c) / 2.0;
            double heronArea = Math.sqrt(s * (s - a) * (s - b) * (s - c));
            shape.setArea(heronArea);
            shape.setMissingAttribute("area");
        }

        //chybějící strany
        if (area != null) {
            if (a == null && b != null && c != null) {
                shape.setA(solveSideFromArea(area, b, c));
                shape.setMissingAttribute("a");
            } else if (b == null && a != null && c != null) {
                shape.setB(solveSideFromArea(area, a, c));
                shape.setMissingAttribute("b");
            } else if (c == null && a != null && b != null) {
                shape.setC(solveSideFromArea(area, a, b));
                shape.setMissingAttribute("c");
            }
        }
    }
    private static double solveSideFromArea(double area, double side1, double side2) {
        double tolerance = 0.00001;
        double guess = 0.0001;
        double max = side1 + side2 - 0.0001;

        while (guess < max) {
            double s = (guess + side1 + side2) / 2.0;
            double heron = Math.sqrt(s * (s - guess) * (s - side1) * (s - side2));
            if (Double.isFinite(heron) && Math.abs(heron - area) < tolerance) {
                return guess;
            }
            guess += 0.0001;
        }
        return 0.0;
    }


    private static void calculateRectangle(Shape shape) {
        Double width = shape.getWidth();
        Double height = shape.getHeight();
        Double area = shape.getArea();
        Double circumference = shape.getCircumference();

        if (width != null && height != null) {
            if (area == null) {
                shape.setArea(width * height);
                shape.setMissingAttribute("area");
            }
            if (circumference == null) {
                shape.setCircumference(2 * (width + height));
                shape.setMissingAttribute("circumference");
            }
        }

        //výpočet výšky nebo šířky
        if (width != null && area != null && height == null) {
            shape.setHeight(area / width);
            shape.setMissingAttribute("height");
        } else if (height != null && area != null && width == null) {
            shape.setWidth(area / height);
            shape.setMissingAttribute("width");
        }
    }

    private static void calculateCircle(Shape shape) {
        Double radius = shape.getRadius();
        Double area = shape.getArea();
        Double circumference = shape.getCircumference();

        if (radius != null) {
            if (area == null) {
                shape.setArea(Math.PI * radius * radius);
                shape.setMissingAttribute("area");
            }
            if (circumference == null) {
                shape.setCircumference(2 * Math.PI * radius);
                shape.setMissingAttribute("circumference");
            }
        }

        //výpočet poloměru z plochy nebo obvodu
        if (radius == null && area != null) {
            shape.setRadius(Math.sqrt(area / Math.PI));
            shape.setMissingAttribute("radius");
        } else if (radius == null && circumference != null) {
            shape.setRadius(circumference / (2 * Math.PI));
            shape.setMissingAttribute("radius");
        }
    }
}
