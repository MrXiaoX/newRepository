package cn.itrip.dao.thread;

public class ThreadTest1{
    public static void main(String[] args) {
     Thread t1= new Thread(new Runnable() {
          @Override
          public void run() {
              for (int i = 0; i <10 ; i++) {
                  System.out.println("子线程:i"+i);
              }
          }
      });
        t1.start();
        for (int i = 0; i <10 ; i++) {
            System.out.println("住线程:i"+i);
        }
    }
}
