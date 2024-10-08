import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.Random;
public class Data {
    private Setting setting ;
    private int Count_Meteor;
    private int[][] position_M;
    private Image[] image;
    private Boolean[] status_;
    private int[][] mode ;
    private int[] Speed_;
    private Image boomb = Toolkit.getDefaultToolkit().createImage(
        System.getProperty("user.dir")+
        File.separator+"SpaceX"+
        File.separator+"src"+
        File.separator+"image"+
        File.separator+"bomb"+
        ".gif"
    );
    Data(Setting setting){
        this.setting = setting;
        this.Count_Meteor = setting.getCount_Meteor();
        this.position_M = new int[Count_Meteor][2];
        image = new Image[Count_Meteor];
        status_ = new Boolean[Count_Meteor];
        mode = new int[Count_Meteor][2];
        Speed_ = new int[Count_Meteor];
        setSpeedPX_();
        setposition();
        setSpeed();
    }
    public void setPosition_X(int position_M,int i) {
        this.position_M[i][0] += position_M;
    }
    public void setPosition_Y(int position_M,int i) {
        this.position_M[i][1] += position_M;
    }
    public Image getBomb(){
        return this.boomb;
    }
    public void setSpeed(){
        for(int i=0;i<Count_Meteor ;i++){
            Speed_[i] = new Random().nextInt(setting.getSpeed_f(),setting.getSpeed_l());
        }

    }
    public int[] getSpeed_() {
        return Speed_;
    }
    public int getSpeed_(int i) {
        return Speed_[i];
    }

    public int[][] getMode() {
        return mode;
    }
    public void setMode(int x ,int y,int position) {
        this.mode[position][1] = y;
        this.mode[position][0] = x;
    }
    public void setposition_puss(int x ,int y,int i){
        this.position_M[i][0] = x;
        this.position_M[i][1] = y;
    }
    public int[][] getPosition_M() {
        return position_M;
    }
    public void setposition(){
        int i=0;
        while(i<Count_Meteor){
            int rnx = new Random().nextInt(50,setting.getWith_space()-100);
            int rny = new Random().nextInt(50,setting.getHight_space()-100);
            Image pathImage_rn = Toolkit.getDefaultToolkit().createImage(
                System.getProperty("user.dir")+
                File.separator+"SpaceX"+
                File.separator+"src"+
                File.separator+"image"+
                File.separator+new Random().nextInt(1,11)+
                ".png"
            );
            position_M[i][0] = rnx;
            position_M[i][1] = rny;
            image[i]= pathImage_rn;
            status_[i] = true;
            i+=1;
        }
    }
    public Image[] getImage() {
        return image;
    }
    public Boolean[] getStatus_() {
        return status_;
    }
    public Boolean getStatus_(int position) {
        return status_[position];
    }
    public void setStatus_(int position,boolean status) {
        status_[position] = status;
    }
    public void setSpeedPX_(){
        int i=0;
        while(i<Count_Meteor){
            int rn1 = new Random().nextInt(-1,2);
            int rn2 = new Random().nextInt(-1,2);
            if(rn1!=rn2){
                mode[i][0] = rn1;
                mode[i][1] = rn2;
                i+=1;
            }
        }
    }
    public int Random_Target1(){
        int num = new Random().nextInt(0,2);
        System.out.println(num);
        return num;
    }
    public int Random_Target2(){
        int num = new Random().nextInt(-1,1);
        System.out.println(num);
        return num;
    }
    public void setChange(char text,int position){
        int modeX =mode[position][0];
        int modeY =mode[position][1];
        if(text=='x'){
            Speed_[position] = new Random().nextInt(setting.getSpeed_f(),setting.getSpeed_l());
            if(modeX>0){
                mode[position][0]=-1;
            }else{
                mode[position][0]=1;
            }
        }else if(text=='y'){
            Speed_[position] = new Random().nextInt(setting.getSpeed_f(),setting.getSpeed_l());
            if(modeY>0){
                mode[position][1]=-1;
            }else{
                mode[position][1]=1;
            }
        }
        
    }

    public void setChange_HIT(char text,int position){
        int modeX =mode[position][0];
        int modeY =mode[position][1];
        if(text=='x'){
            Speed_[position] = new Random().nextInt(setting.getSpeed_f(),setting.getSpeed_l());
            if(modeX>0){
                mode[position][0]=-1;
            }else{
                mode[position][0]=1;
            }
            if(modeY==0){
                mode[position][1]=-1;
            }
        }else if(text=='y'){
            Speed_[position] = new Random().nextInt(setting.getSpeed_f(),setting.getSpeed_l());
            if(modeY>0){
                mode[position][1]=-1;
            }else{
                mode[position][1]=+1;
            }
            if(modeX==0){
                mode[position][1]=1;
            }

        }
    }
    public int setMouse(int x,int y){
        int position =0;
        for(int i=0;i<Count_Meteor;i++){
            if(x>position_M[i][0] && x<position_M[i][0]+50 && y>position_M[i][1] && y<position_M[i][1]+50 && status_[i]){
                position = i;
                break;
            }
        }
        return position;
    }
    public boolean getMouse(int x,int y){
        boolean position =false;
        for(int i=0;i<Count_Meteor;i++){
            if(x>position_M[i][0]-20 && x<position_M[i][0]+70 && y>position_M[i][1]-20 && y<position_M[i][1]+70 && status_[i]){
                position = true;
                break;
            }
        }
        return position;
    }
}
