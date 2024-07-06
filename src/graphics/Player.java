package graphics;

import java.awt.*;

public class Player extends MovableObject{
    private int cooldown, shootTick;


    public Player(double x, double y, int width, int height, double maxVelo) {
        super(x, y, width, height, maxVelo);
        shootTick = -1;
        cooldown = 20;
    }

    public void drawSelf(Graphics g){
        g.setColor(Color.black);
        g.fillRect((int)getX(), (int)getY(), getWidth(), getHeight());
    }

    public PlayerAttack createHit(int tick){
        int offset = 100;
        int radius = 50;
        int dmg = 1;
        if(shootTick < tick){
            shootTick += cooldown;
            switch(getDirection()) {
                case 0:
                    return new PlayerAttack(getX() + getWidth() / 2, getY() - offset, radius, dmg);
                case 1:
                    return new PlayerAttack(getX() + offset + getWidth(), getY() + getHeight() / 2, radius, dmg);
                case 2:
                    return new PlayerAttack(getX() + getWidth(), getY() + offset + getHeight(), radius, dmg);
                case 3:
                    return new PlayerAttack(getX() - offset, getY() + getHeight() / 2, radius, dmg);
            }
        }
        return null;
    }

}
