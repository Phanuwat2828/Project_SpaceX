import javax.swing.JPanel;
import java.awt.Rectangle;

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
    public Boolean check_position(int x, int y, int position_) {
        int position[][] = data.getPosition_M();
        Rectangle r1 = new Rectangle(x, y, 50, 50);
    
        for (int i = 0; i < setting.getCount_Meteor(); i++) {
            if (i != position_) {
                int xc = position[i][0];
                int yc = position[i][1];
                Rectangle r2 = new Rectangle(xc, yc, 50, 50);
    
                // คำนวณพื้นที่ที่ทับซ้อนกัน
                int overlapX = Math.min(r1.x + r1.width, r2.x + r2.width) - Math.max(r1.x, r2.x);
                int overlapY = Math.min(r1.y + r1.height, r2.y + r2.height) - Math.max(r1.y, r2.y); 
    
                // ตรวจสอบการชน
                if (r1.intersects(r2)) {
                    // ตรวจสอบว่าควรเลื่อนในแนวแกน X หรือ Y
                    if (overlapX < overlapY) {
                        if (r1.x < r2.x) {
                            // เลื่อน r1 ไปทางซ้าย
                            data.setPosition_X(-overlapX, position_);
                            data.setPosition_X(overlapX, i); // เลื่อน r2 ไปทางขวา
                        } else {
                            // เลื่อน r1 ไปทางขวา
                            data.setPosition_X(overlapX, position_);
                            data.setPosition_X(-overlapX, i); // เลื่อน r2 ไปทางซ้าย
                        }
                        // เปลี่ยนทิศทางในแกน X ของทั้งสองวัตถุ
                        data.setChange_HIT('x', position_);
                        data.setChange_HIT('x', i);
                    } else {
                        if (r1.y < r2.y) {
                            // เลื่อน r1 ไปด้านบน
                            data.setPosition_Y(-overlapY, position_);
                            data.setPosition_Y(overlapY, i); // เลื่อน r2 ไปด้านล่าง
                        } else {
                            // เลื่อน r1 ไปด้านล่าง
                            data.setPosition_Y(overlapY, position_);
                            data.setPosition_Y(-overlapY, i); // เลื่อน r2 ไปด้านบน
                        }
                        // เปลี่ยนทิศทางในแกน Y ของทั้งสองวัตถุ
                        data.setChange_HIT('y', position_);
                        data.setChange_HIT('y', i);
                    }
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
            }else if(y<=10 || y>=setting.getWith_space()-60){
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