package com.company;
import java.awt.*;
import javax.swing.*;

public class Particle extends JPanel {

    public Vector2D position;
    public Vector2D velocity;
    public int radius;
    public int mass;

    public Particle(Vector2D initPosition,  Vector2D initVelocity, int initRadius, int initMass){
        this.position = initPosition;
        this.velocity = initVelocity;
        this.radius = initRadius;
        this.mass = initMass;
    }

    public Particle(Vector2D initPosition,  Vector2D initVelocity, int initRadius){
        this.position = initPosition;
        this.velocity = initVelocity;
        this.radius = initRadius;
        this.mass = 0;
    }

    public Particle(Vector2D initPosition, int initRadius, int initMass){
        this.velocity = new Vector2D(0,0);
        this.position = initPosition;
        this.radius = initRadius;
        this.mass = initMass;
    }

    public Particle(){
        this.position = new Vector2D(0,0);
        this.velocity = new Vector2D(0,0);
        this.radius = 10;
        this.mass = 0;
    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval((int) this.position.getX(), (int) this.position.getY(), this.radius, this.radius);
    }
}

