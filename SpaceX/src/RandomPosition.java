import javax.swing.JPanel;

public class RandomPosition extends Thread{
    private Data data;
    private int position;
    private JPanel panel;
    private Setting setting;
    RandomPosition(Data data,int position,JPanel panel,Setting setting){
        this.data = data;
        this.position = position;
        this.panel = panel;
        this.setting = setting;
    }
    public Boolean check_position(int x ,int y,int position_){
        int position[][] = data.getPosition_M();
        for(int i=0;i<setting.getCount_Meteor();i++){
            int xc = position[i][0];
            int yc = position[i][1];
            if(i!=position_){
                if(Math.abs(x - xc) < 45 && Math.abs(y - yc) < 45 ){
                    data.setChange_HIT('x', position_); // เปลี่ยนทิศทางในแกน X
                    data.setChange_HIT('y', position_); // เปลี่ยนทิศทางในแกน Y
                    data.setChange_HIT('x', i); // เปลี่ยนทิศทางในแกน X
                    data.setChange_HIT('y', i);
                    break;
                }
            }
        }
        return true;
    }
    public void run(){
        while (true) {
            int position_M[][] = data.getPosition_M();
            int x = position_M[position][0];
            int y = position_M[position][1];
            if(x<=0 || x>=setting.getWith_space()-60){
                data.setChange('x', position);
            }else if(y<=10 || y>=setting.getWith_space()-40){
                data.setChange('y', position);
            }
            check_position(x+data.getMode()[position][0],y+ data.getMode()[position][1],position);
            int mode_x =data.getMode()[position][0];
            int mode_y = data.getMode()[position][1];
            data.setposition_puss(x+mode_x,y+mode_y, position);
            
            panel.repaint();
            try {
                Thread.sleep(data.getSpeed_()[position]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
}