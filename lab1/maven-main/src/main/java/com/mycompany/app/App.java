package com.mycompany.app;

import com.mycompany.figures.Circle;

import org.javatuples.Pair;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Circle myCircle = new Circle(new Pair<Float, Float>(new Float(1.32), new Float(3.14)), (float)42.0);
            System.out.println( "Hello World! My circumference: " + myCircle.getCircumeference());
        } catch (Exception e) {
            System.err.println("Oh no!");
            return;
        }
    }
}
