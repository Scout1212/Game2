package graphics;

import java.awt.*;

public class Player extends MovableObject{
    private int cooldown, shootTick, attackSeq, lastTick;


    public Player(double x, double y, int width, int height, double maxVelo) {
        super(x, y, width, height, maxVelo);
        shootTick = -1;
        cooldown = 20;
        attackSeq = 0;
        lastTick = -1;
    }

    public void drawSelf(Graphics g){
        g.setColor(Color.black);
        g.fillRect((int)getX(), (int)getY(), getWidth(), getHeight());
    }

    public PlayerAttack createHit(int tick) {
        if(shootTick < tick){
            attackSeq = (lastTick - tick < cooldown + 10) && (attackSeq < 3)? attackSeq + 1 : 0;
            int ykb = attackSeq == 3 ? -30 : 0;
            int xkb = getDirection() == 0 ? -20 : 20;
            shootTick = tick + cooldown;
            lastTick = tick;
            double x = (getDirection() == 0) ? getX() - 100 : getX() + 100 + getWidth();
            return new PlayerAttack(x, getY() + getHeight() / 2, 50, 1, attackSeq, xkb, ykb);
        }
        return null;
    }
}
