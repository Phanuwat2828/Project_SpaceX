import java.util.Scanner;

public class Setting {
    private int Count_Meteor = 0 ;
    private boolean hitbox = false;
    private int Speed_f = 3;
    private int Speed_l = 14;
    private int with_space = 650;
    private int hight_space = 700;
    
    Setting(){
        System.out.print("Enter SpaceX : ");
        Scanner input = new Scanner(System.in);
        Count_Meteor = input.nextInt();
    }

    public void setCount_Meteor(int count_Meteor) {
        this.Count_Meteor = count_Meteor;
    }
    public int getCount_Meteor() {
        return this.Count_Meteor;
    }
    public int getHight_space() {
        return this.hight_space;
    }
    public int getSpeed_f() {
        return this.Speed_f;
    }
    public int getSpeed_l() {
        return this.Speed_l;
    }
    public int getWith_space() {
        return this.with_space;
    }
    public boolean gethitbox(){
        return this.hitbox;
    }
}
