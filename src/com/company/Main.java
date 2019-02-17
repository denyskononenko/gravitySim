package com.company;
import javax.swing.JFrame;
import java.io.IOException;
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
        Main canvas = new Main(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        Vector2D center = new Vector2D(Main.SCREEN_WIDTH /2 - 50, Main.SCREEN_HEIGHT / 2 - 50); // Radii of center of the screen
        Vector2D pivotPoint = new Vector2D(300, 300); // coordinate pivot point
        Vector2D delta = new Vector2D(2, 0); // init velocity of small particle

        Particle par1 = new Particle(pivotPoint, delta, 10);
        Particle par_sun= new Particle(center,50, 10);

        //add particles to canvas
        // small particle
        canvas.add(par1);
        canvas.revalidate();

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
                if (par1.position.getX() < 0 || par1.position.getX() > Main.SCREEN_WIDTH - par1.radius){
                    delta.setX(-delta.getX());
                }else if (par1.position.getY() < 0 || par1.position.getY() > Main.SCREEN_WIDTH - par1.radius) {
                    delta.setY(-delta.getY());
                }

                par1.position = Vector2D.add(par1.position, par1.velocity);
                par1.revalidate();
                canvas.repaint();
                t += 1; // refresh time
                //
                // calculate radii between centers of particles
                deltaR = Vector2D.subsr(par_sun.position, par1.position);

                // calculate change of small particle velocity per snapshot
                deltaV = Vector2D.add(deltaV, Vector2D.multiplyOnScalar(deltaR, 1/ deltaR.module()));
                par1.velocity = Vector2D.add(par1.velocity, Vector2D.multiplyOnScalar(deltaR, par_sun.mass/ (deltaR.module() * deltaR.module())));

                System.out.println("\n X, Y:");
                System.out.println(par1.position.getX());
                System.out.println(par1.position.getY());
                System.out.println("\n time:");
                System.out.println(t);
                System.out.println("\n r12 length:");
                System.out.println(deltaR.module());
            }
        }, 5, 5);
    }
}
