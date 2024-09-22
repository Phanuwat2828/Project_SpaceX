public class Bomb extends Thread{
    private Container container ;
    Bomb(Container container){
        this.container = container;
    }
    @Override
    public void run() {

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        container.setBomb(false);
    }
}
