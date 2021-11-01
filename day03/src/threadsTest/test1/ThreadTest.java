package threadsTest.test1;

/**
 * 多线程的创建方式1
 */
class MyThread extends Thread{
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            if(i%2==0){
                System.out.println(i);
            }
        }
    }
}

public class ThreadTest {
    public static void main(String[] args) {

        MyThread myThread=new MyThread();
        myThread.start();//启动另一个线程 并调用run()方法
        for(int i=0;i<100;i++){
            if(i%2==1)
                System.out.println(i+"***main***");
        }
    }
}
