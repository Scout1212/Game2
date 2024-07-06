package graphics;

public class MovableObject{
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double maxVelo;
    private double acceleration;
    private int width;
    private int height;
    private int direction;

    public MovableObject(double x, double y, int width, int height, double maxVelo){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        vx = 0;
        vy = 0;
        direction = 0;

        acceleration = 2;

        this.maxVelo = maxVelo;
    }

    public void moveRight(){
        if(vx + acceleration > maxVelo)
            vx = maxVelo;
        else if(vx < maxVelo)
            vx += acceleration;
        direction = 1;
    }

    public void moveLeft(){
        if(vx + acceleration < -maxVelo){
            vx = -maxVelo;
        }
        else if(vx > -maxVelo){
            vx -= acceleration;
        }
        direction = 0;
    }

    public void moveUp(){
        if(vy - acceleration < -maxVelo){
            vy = -maxVelo;
        }
        else{
            vy -= acceleration;
        }
    }

    public void moveDown(){
        if(vy + acceleration > maxVelo){
            vy = maxVelo;
        }
        else{
            vy += acceleration;
        }
    }

    public void resetXVelo(){
        vx = 0;
    }
    public void resetYVelo(){
        vy = 0;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getVx(){
        return vx;
    }
    public double getNextX(){
        return vx + x;
    }
    public double getNextY(){
        return vy + y;
    }
    public double getVy(){
        return vy;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public double getMaxVelo(){
        return maxVelo;
    }
    public double getCenterX(){
        return x + width/2;
    }
    public double getCenterY(){
        return y + height/2;
    }
    public int getDirection(){return direction;}
    public void addVx(double vx){
        this.vx += vx;
    }
    public void addVy(double vy){
        this.vy += vy;
    }
    public void applyVelo(){
        x += vx;
        y += vy;
    }
    public void changeX(double vx){
        x += vx;
    }

}
