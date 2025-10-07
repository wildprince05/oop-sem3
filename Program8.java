class Racer extends Thread {
    private String name;
    private int sleepTime; 
    public Racer(String name, int sleepTime) {
        this.name = name;
        this.sleepTime = sleepTime;
    }
    @Override
    public void run() {
        for (int step = 1; step <= 10; step++) {
            System.out.println(name + " is at step " + step);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println(name + " was interrupted.");
            }
        }
        System.out.println(name + " finished the race!");
    }
}
public class Program8 {
    public static void main(String[] args) {
        Racer racer1 = new Racer("Thread A", 500);
        Racer racer2 = new Racer("Thread B", 500);
        Racer racer3 = new Racer("Thread C", 500);
        racer1.start();
        racer2.start();
        racer3.start();
        try {
            racer1.join();
            racer2.join();
            racer3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Race Finished!");
    }
}