package graphics;

import java.awt.*;

public class Player extends MovableObject{
    private int cooldown, shootTick;


    public Player(double x, double y, int width, int height, double maxVelo) {
        super(x, y, width, height, maxVelo);
        shootTick = -1;
        cooldown = 200;
    }

    public void drawSelf(Graphics g){
        g.setColor(Color.black);
        g.fillRect((int)getX(), (int)getY(), getWidth(), getHeight());
    }

    public CollidableObject createHit(int tick){
        int offset = 100;
        int radius = 50;
        if(shootTick < tick){
            shootTick += cooldown;
            switch(getDirection()) {
                case 0:
                    return new CollidableObject(getX() + getWidth() / 2, getY() - offset, radius);
                case 1:
                    return new CollidableObject(getX() + offset + getWidth(), getY() + getHeight() / 2, radius);
                case 2:
                    return new CollidableObject(getX() + getWidth(), getY() + offset + getHeight(), radius);
                case 3:
                    return new CollidableObject(getX() - offset, getY() + getHeight() / 2, radius);
            }
        }
        return null;
    }

}
