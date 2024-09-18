import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.TimerTask;
import java.util.Random;
import java.util.Timer;

public class App {
    public static void main(String[] args) throws Exception {
        Frame_ frame = new Frame_();
        frame.setVisible(true);
    }
}
class Frame_ extends JFrame {
    Frame_(){
        setSize(650,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Container panel = new Container();
        add(panel);
        
    }
}

class Container extends JPanel{
    private Setting setting = new Setting();
    private Data data = new Data();
    Container(){
        setBackground(Color.BLACK);
        setSize(650,700);
        setLocation(0,0);
        for (int i = 0; i < setting.getCount_Meteor(); i++) {
            if (data.getStatus_()[i]) {
                RandomPosition tH = new RandomPosition(data, i, this);
                tH.start();
            }
        }
       
    }
    protected void paintComponent(Graphics g) {
        repaint();
        g.fillRect(0, 0, 650, 700);
        g.setColor(new Color(255,255,255));
        for(int i=0;i<setting.getCount_Meteor();i++){
            if(data.getStatus_()[i]){
                g.setColor(Color.GREEN);
                g.drawString("X:"+data.getPosition_M()[i][0]+"Y :" +data.getPosition_M()[i][1],data.getPosition_M()[i][0], data.getPosition_M()[i][1]-5);
                g.drawRect(data.getPosition_M()[i][0], data.getPosition_M()[i][1],50, 50);
                g.drawImage(data.getImage()[i], data.getPosition_M()[i][0], data.getPosition_M()[i][1],50,50,this);
            }
        }
       
    }
}
class RandomPosition extends Thread{
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
            // if(one<position_M[i][0]+100 && one>position_M[i][0]-100 && two<position_M[i][1]+100 && two>position_M[i][1]-100){
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
            if(x<=0 || x>=590){
                data.setChange('x', position);
            }else if(y<=0 || y>=600){
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