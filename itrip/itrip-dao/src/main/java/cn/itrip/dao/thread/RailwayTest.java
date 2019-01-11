package cn.itrip.dao.thread;


class Railway implements Runnable {

    private int count = 100;

    private Object object = new Object();

    @Override
    public void run() {
        while (count > 0) {
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sale();
        }
    }

    public void sale() {
        synchronized (object) {
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + ",出售第" + (100 - count + 1) + "票");
                count--;
            }
        }
    }
}

public class RailwayTest {
    public static void main(String[] args) {
        Railway railway = new Railway();
        Thread thread1 = new Thread(railway, "窗口1");
        Thread thread2 = new Thread(railway, "窗口2");
        thread1.start();
        thread2.start();
    }
}
