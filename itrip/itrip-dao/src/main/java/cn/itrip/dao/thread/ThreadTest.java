package cn.itrip.dao.thread;

 class Test extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            //getId线程ID
            System.out.println(getId()+"子线程:i"+i);
        }
    }
}

public class ThreadTest{
    public static void main(String[] args) {
        Test t1=new Test();
        t1.start();
        for (int i = 0; i <10 ; i++) {
            System.out.println("住线程:i"+i);
        }
    }
}
