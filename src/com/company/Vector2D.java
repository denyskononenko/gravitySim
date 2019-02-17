package com.company;

public class Vector2D {

    private double x;
    private double y;

    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }

    public double module(){
        return Math.sqrt(this.x * this.x + this.y * this.y );
    }

    public static double scalarProd(Vector2D a, Vector2D b){
        return (a.x * b.x + a.y * b.y);
    }

    public static Vector2D add(Vector2D a,  Vector2D b){
        return new Vector2D(a.x + b.x, a.y + b.y);
    }

    public static Vector2D subsr(Vector2D a, Vector2D b){
        return new Vector2D(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public static Vector2D multiplyOnScalar(Vector2D a, double s){
        return new Vector2D(a.getX() * s, a.getY() * s);
    }

    public Vector2D normalise(){
        return new Vector2D(this.x / this.module(), this.y / this.module());
    }

    public double Projection(Vector2D b){
        return Vector2D.scalarProd(this, b) / b.module();
    }
}

