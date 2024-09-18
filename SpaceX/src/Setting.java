
public class Setting {
    private int Count_Meteor = 7 ;
    private boolean hitbox = true;
    private int Speed_f = 3;
    private int Speed_l = 14;
    private int with_space = 650;
    private int hight_space = 700;

    public int getCount_Meteor() {
        return this.Count_Meteor;
    }
    public int getHight_space() {
        return hight_space;
    }
    public int getSpeed_f() {
        return Speed_f;
    }
    public int getSpeed_l() {
        return Speed_l;
    }
    public int getWith_space() {
        return with_space;
    }
    public boolean gethitbox(){
        return hitbox;
    }
}
