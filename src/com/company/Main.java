package com.company;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



public class Main extends JFrame{

    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 1000;

    public Main(int width, int height){
        this.setTitle("Particles System Simulation");
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args){
        //create JFrame instance
        Main canvas = new Main(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        JButton button = new JButton("Add particle me");
        button.setSize(300, 64);

        JLabel infoLabel = new JLabel("out");

        Vector2D center = new Vector2D(Main.SCREEN_WIDTH /2 - 50, Main.SCREEN_HEIGHT / 2 - 50); // Radii of center of the screen
        Vector2D pivotPoint = new Vector2D(300, 300); // coordinate pivot point
        Vector2D delta = new Vector2D(2, 0); // init velocity of small particle

        Particle par1 = new Particle(pivotPoint, delta, 10);
        Particle par_sun= new Particle(center,50, 10);

        ArrayList<Particle> particles = new ArrayList<>();
        particles.add(par1);
        particles.add(new Particle(new Vector2D(250, 250), delta, 20));

        // add button and label
        canvas.add(button, BorderLayout.NORTH);
        canvas.add(infoLabel, BorderLayout.SOUTH);
        canvas.revalidate();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                particles.add(new Particle(new Vector2D(250,250), delta, 30));
                canvas.add(particles.get(particles.size() - 1));
                infoLabel.setText(Integer.toString(particles.size()));
            }
        });

        //add particles to canvas
        // small particles
        for (int i = 0; i < particles.size(); i++){
            canvas.add(particles.get(i));
            canvas.revalidate();
        }

        // solar particle
        canvas.add(par_sun);
        canvas.revalidate();

        // display movement of ball in timer
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int t = 0; // t0
            Vector2D deltaR = new Vector2D(); // radii between centers of particles
            Vector2D deltaV = new Vector2D(); // change of small particle velocity per snapshot

            @Override
            public void run() {
                for (int i = 0; i < particles.size(); i++){
                    if (particles.get(i).position.getX() < 0 || particles.get(i).position.getX() > Main.SCREEN_WIDTH - particles.get(i).radius){
                        delta.setX(-delta.getX());
                    }else if (particles.get(i).position.getY() < 0 || particles.get(i).position.getY() > Main.SCREEN_WIDTH - particles.get(i).radius) {
                        delta.setY(-delta.getY());
                    }

                    particles.get(i).position = Vector2D.add(particles.get(i).position, particles.get(i).velocity);
                    particles.get(i).revalidate();
                    canvas.repaint();
                    t += 1; // refresh time
                    //
                    // calculate radii between centers of particles
                    deltaR = Vector2D.subsr(par_sun.position, particles.get(i).position);

                    // calculate change of small particle velocity per snapshot
                    deltaV = Vector2D.add(deltaV, Vector2D.multiplyOnScalar(deltaR, 1/ deltaR.module()));
                    particles.get(i).velocity = Vector2D.add(particles.get(i).velocity, Vector2D.multiplyOnScalar(deltaR, par_sun.mass/ (deltaR.module() * deltaR.module())));

                    System.out.println("\n X, Y:");
                    System.out.println(particles.get(i).position.getX());
                    System.out.println(particles.get(i).position.getY());
                    System.out.println("\n time:");
                    System.out.println(t);
                    System.out.println("\n r12 length:");
                    System.out.println(deltaR.module());
                }
            }
        }, 5, 5);
    }
}
