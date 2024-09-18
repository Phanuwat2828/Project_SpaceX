import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Container extends JPanel{
    private Setting setting ;
    private Data data = new Data();
    private int Count_Meteor;
    Container(Setting setting){
        this.setting = setting;
        this.Count_Meteor = setting.getCount_Meteor();
        setBackground(Color.BLACK);
        setSize(setting.getWith_space(),setting.getHight_space());
        setLocation(0,0);
        for (int i = 0; i < Count_Meteor; i++) {
            if (data.getStatus_()[i]) {
                RandomPosition tH = new RandomPosition(data, i, this);
                tH.start();
            }
        }
    }
    protected void paintComponent(Graphics g) {
        repaint();
        g.fillRect(0, 0, setting.getWith_space(),setting.getHight_space());
        g.setColor(new Color(255,255,255));
        for(int i=0;i<Count_Meteor;i++){
            int x = data.getPosition_M()[i][0];
            int y = data.getPosition_M()[i][1];
            Image path_image = data.getImage()[i];
            if(data.getStatus_()[i]){
                if(setting.gethitbox()){
                    g.setColor(Color.GREEN);
                    g.drawString("X:"+x+"Y :"+y,x, y-5);
                    g.drawRect(x,y,50, 50);
                }                
                g.drawImage(path_image, x, y,50,50,this);
            }
        }
    }
}

