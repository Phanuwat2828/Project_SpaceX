
import javax.swing.JFrame;
public class Frame_ extends JFrame {
    Frame_(Setting setting){
        int with_space = setting.getWith_space();
        int hight_space = setting.getHight_space();
        setSize(with_space,hight_space);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Container panel = new Container(setting);
        add(panel);

    
    }
}