package com.mycompany.gradle;

import com.mycompany.gradle.figures.Circle;
import org.javatuples.Pair;

public class Main {
    public static void main(String[] args) {
        try {
            Circle myCircle = new Circle(new Pair<Float, Float>(new Float(1.32), new Float(3.14)), (float)42.0);
            System.out.println( "Hello World! My circumference: " + myCircle.getCircumeference());
        } catch (Exception e) {
            System.err.println("Oh no!");
            return;
        }
    }
}

