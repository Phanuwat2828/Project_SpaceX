import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.Random;

public class Data {
    private Setting set = new Setting();
    private int Count_Meteor = set.getCount_Meteor();
    private int[][] position_M= new int[Count_Meteor][2];
    private Image[] image = new Image[Count_Meteor];
    private Boolean[] status_ = new Boolean[Count_Meteor];
    private int[][] mode = new int[Count_Meteor][2];
    private int[] Speed_ = new int[Count_Meteor];


    Data(){
        setposition();
        setSpeedPX_();
        setSpeed();
    }

    public void setSpeed(){
        for(int i=0;i<Count_Meteor ;i++){
            Speed_[i] = new Random().nextInt(1,11);
        }

    }
    public int[] getSpeed_() {
        return Speed_;
    }

    public int[][] getMode() {
        return mode;
    }
    public void setMode(int[][] mode) {
        this.mode = mode;
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
            int rnx = new Random().nextInt(50,550);
            int rny = new Random().nextInt(50,600);
            if(check_position(rnx, rny)){
                position_M[i][0] = rnx;
                position_M[i][1] = rny;
                image[i]= Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir")+File.separator+"image"+File.separator+new Random().nextInt(1,11)+".png");
                status_[i] = true;
                i+=1;
            }
        }
    }
    public Image[] getImage() {
        return image;
    }
    public Boolean[] getStatus_() {
        return status_;
    }
    public boolean check_position(int one,int two){
        boolean status = true;
        for(int i=0;i<Count_Meteor;i++){
            if(one<position_M[i][0]+100 && one>position_M[i][0]-100 && two<position_M[i][1]+100 && two>position_M[i][1]-100){
               status = false;
               break;
            }
        }
        return status;
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
    public void sethit(int one,int two){
        int hold[] = mode[one];
        mode[one] = mode[two];
        mode[two] = hold;
    }
    public void setChange(char text,int position){
        if(text=='x'){
            Speed_[position] = new Random().nextInt(1,11);
            if(mode[position][0]>0){
                mode[position][0]=-1;
            }else{
                mode[position][0]=+1;
            }
            if(mode[position][1]==0){
                mode[position][1] = -1;
            }
        }else if(text=='y'){
            Speed_[position] = new Random().nextInt(1,11);
            if(mode[position][1]>0){
                mode[position][1]=-1;
            }else{
                mode[position][1]=+1;
            }
            if(mode[position][0]==0){
                mode[position][0] = +1;
            }
        }
        
    }
}
