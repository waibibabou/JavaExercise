package threadsTest.test1;

public class ThreadTest1 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread t1=new Thread(myThread);
        t1.start();

    }

}

class MThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i%2==0){
                System.out.println(i);
            }
        }
    }
}
