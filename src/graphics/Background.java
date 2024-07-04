package graphics;

import javax.swing.*;
import java.awt.*;

public class Background{
    private double x;
    private double y;
    private int width;
    private int height;

    Image back = new ImageIcon(getClass().getResource("LongImage.jpg")).getImage();

    public Background(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    //spongegar

    public void drawSelf(Graphics g){
        g.drawImage(back, (int)x, (int)y, width, height, null);
    }

    public void move(double vx){
        x += vx;
    }

}
