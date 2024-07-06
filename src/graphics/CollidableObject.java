package graphics;

import java.awt.*;

public class CollidableObject{
    private double x, y;
    private int radius;
    private int tickLife, dmg;

    public CollidableObject(double x, double y, int radius, int dmg){
        this.radius = radius;
        //places middle of circle at x and y
        this.x = x - radius/2;
        this.y = y - radius/2;
        tickLife = 10;
        this.dmg = dmg;
    }

    public boolean collideSquare(double rx, double ry, int rw, int rh){
        double centerX = x + radius/2;
        double centerY = y + radius/2;
        for(double i = rx; i < rx + rw; i++){
            if(distance(i, ry, centerX, centerY) < radius || distance(i, ry + rh, centerX, centerY) < radius){
                return true;
            }
        }
        for(double i = ry; i < ry + rh; i++){
            if(distance(i, rx, centerX, centerY) < radius || distance(i, rx +rw , centerX, centerY) < radius){
                return true;
            }
        }

        return false;
    }

    public int getDmg(){
        return dmg;
    }

    public boolean act(){
        tickLife --;

        return tickLife <= 0;
    }
    public void drawSelf(Graphics g){
        g.fillOval((int)x, (int)y, radius, radius);
    }

    public double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
    }

    public double getCenterX(){
        return x + radius/2;
    }
}
