public class test1 extends Thread {
    public int aux;

    public test1() {
        aux = 0;
        this.run();
    }

    public void setAux(int aux) {
        this.aux = aux;
    }

    public synchronized void run() {
        while(true) {
            System.out.println(this.aux);
            try {
                this.wait(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
