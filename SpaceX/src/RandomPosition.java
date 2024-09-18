import javax.swing.JPanel;

public class RandomPosition extends Thread{
    private Data data;
    private int position;
    private JPanel panel;
    private Setting setting = new Setting();
    RandomPosition(Data data,int position,JPanel panel){
        this.data = data;
        this.position = position;
        this.panel = panel;
    }
    public Boolean check_position(int position_){
        int position[][] = data.getPosition_M();
        int x = position[position_][0];
        int y = position[position_][1];

        for(int i=0;i<setting.getCount_Meteor();i++){
            int xc = position[i][0];
            int yc = position[i][1];
            if(i!=position_){
                if(Math.abs(x - xc) < 50 && Math.abs(y - yc) < 50){
                    data.setChange('x', position_); // เปลี่ยนทิศทางในแกน X
                    data.setChange('y', position_); // เปลี่ยนทิศทางในแกน Y
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
            }else if(y<=0 || y>=setting.getWith_space()-40){
                data.setChange('y', position);
            }
            data.setposition_puss(x+data.getMode()[position][0],y+data.getMode()[position][1], position);
            check_position(position);
            panel.repaint();
            try {
                Thread.sleep(data.getSpeed_()[position]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
}