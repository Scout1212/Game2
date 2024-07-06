package graphics;

import java.awt.*;

public class Enemy extends MovableObject{
    private int hp;
    public Enemy(double x, double y, int width, int height, double maxVelo) {
        super(x, y, width, height, maxVelo);
        hp = 10;
    }

    //todo fix the y

    public void chaseTarget(double tx, double ty){
        if(getX() > tx)
            moveLeft();
        else
            moveRight();

        if(getY() > ty)
            moveUp();
        else
            moveDown();
    }

    public void CollideWithOthers(Enemy b){
        if(getX() < b.getX() + b.getWidth() && getX() + getWidth() > b.getX() && getY() < b.getY() + b.getHeight() && getY() + getHeight() > b.getY()){
            if(getCenterX() < b.getCenterX()){
                moveLeft();
                b.moveRight();
            }
            else{
                moveRight();
                b.moveLeft();
            }

            if(getCenterY() < b.getCenterY()){
                moveUp();
                b.moveDown();
            }
            else{
                moveDown();
                b.moveUp();
            }
        }
    }

    public void getHit(int i, double xkb, double ykb){
        //todo add stuns and smooth out the 3rd hit, so its like it rises in the air then falls down
        hp -= i;
        addVx(xkb);
        addVy(ykb);
        applyVelo();
    }

    public boolean isDead(){
        return hp <= 0;
    }

    public void drawSelf(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int)getX(), (int)getY(), getWidth(), getHeight());
    }

    public void move(double vx){
        changeX(vx);
    }
}
