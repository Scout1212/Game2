package graphics;

public class PlayerAttack extends CollidableObject{
    private int xkb;
    private int ykb;

     PlayerAttack(double x, double y, int rad, int dmg, int seq, int xkb, int ykb){
         super(x, y, rad, dmg);

         this.xkb = xkb;
         this.ykb = ykb;

    }

    public double getYkb(){
         return ykb;
    }

    public double getXkb() {
         return xkb;
    }
}
