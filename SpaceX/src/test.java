import java.io.File;

public class test {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir")+File.separator+"image"+File.separator+1+".png");
    }
}



// protected void paintComponent(Graphics g) {
//     for(int i=0;i<2;i++){
//         Image Path  = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir")+File.separator+"image"+File.separator+data.getNum()[i]+".png");
//         g.drawImage(Path, data.getPosition_M(i, 1), data.getPosition_M(i, 2),50,50,this);
//     }
// }