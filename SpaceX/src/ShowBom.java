public class ShowBom extends Thread{
    private Container data;
    private int position;
    ShowBom(Container data,int position){
        this.data = data;
        this.position = position;
    }
    @Override
    public void run() {
        try {
            data.setBomb(true,position);
            Thread.sleep(800);
            data.setBomb(false,position);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}