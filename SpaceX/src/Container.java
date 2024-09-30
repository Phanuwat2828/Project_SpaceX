
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;

public class Container extends JPanel{
    private Setting setting ;
    private Data data ;
    private int Count_Meteor;
    private boolean[] status_bomb ;
    Container(Setting setting){
        this.setting = setting;
        this.Count_Meteor = setting.getCount_Meteor();
        this.data = new Data(setting);
        setBackground(Color.BLACK);
        setSize(setting.getWith_space(),setting.getHight_space());
        setLocation(0,0);
        status_bomb  = new boolean[Count_Meteor];

        for (int i = 0; i < Count_Meteor; i++) {
            if (data.getStatus_()[i]) {
                this.status_bomb[i] = false;
                RandomPosition tH = new RandomPosition(data, i, this,setting);
                tH.start();
            }
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(e.getClickCount()== 2  && data.getMouse(e.getX(),e.getY())){
                    int position = data.setMouse(e.getX(),e.getY()); 
                    if(data.getStatus_(position)){
                        System.out.println("Boom");
                        data.setStatus_(position,false);
                        ShowBom bm1 = new ShowBom(get_here(),position);
                        bm1.start();
                    }
                }
            }
        });
    }
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, setting.getWith_space(),setting.getHight_space());
        for(int i=0;i<Count_Meteor;i++){
            int x = data.getPosition_M()[i][0];
            int y = data.getPosition_M()[i][1];
            Image path_image = data.getImage()[i];
            if(data.getStatus_()[i]){
                if(setting.gethitbox()){
                    g.setColor(Color.GREEN);
                    g.drawString("Number : "+(i+1),x-30, y-45);
                    g.drawString("X:"+x+"Y :"+y,x-30, y-30);
                    g.drawRect(x,y,50, 50);
                    g.drawString("speedX : "+data.getMode()[i][0]+"px per "+data.getSpeed_(i),x-30, y-20);
                    g.drawString("speedY : "+data.getMode()[i][1]+"px per "+data.getSpeed_(i),x-30, y-10);
                }                
                g.drawImage(path_image, x, y,50,50,this);
            }else if(!data.getStatus_()[i]){ 
                if(this.status_bomb[i]){
                    g.drawImage(data.getBomb(), x-20, y-20, this);
                }
            }
        }
    }
    public void setBomb(boolean status,int i){
        this.status_bomb[i] = status;
    }
    public boolean getBomb(int i){
        return this.status_bomb[i] ;
    }
    public Container get_here(){
        return this;
    }
}

