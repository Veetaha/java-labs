package com.mycompany.figures;

import org.javatuples.Pair;


public class Circle {
    private Pair<Float, Float> center;
    private float radius;

    public Circle(Pair<Float, Float> center, float radius) throws Exception {
        if (radius < 0) {
            throw new Exception("radius cannot be negative");
        }
        this.center = center;
        this.radius = radius;
    }

    public Pair<Float, Float> getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }

    public float getCircumeference() {
        return (float)Math.PI * radius * radius;
    }

}

