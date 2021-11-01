package threadsTest;

public class threads {
    public static void main(String[] args) {
        String s="sss";

        System.out.println(s);
        MyThread1 myThread1=new MyThread1("111111");
        MyThread2 myThread2=new MyThread2();
        Thread.currentThread().setName("主线程");
        myThread1.setName("线程一");
        myThread2.setName("线程二");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread1.start();
        myThread2.start();



    }
}

class MyThread1 extends Thread{
    public MyThread1(String name){
        super(name);
    }

    public void run(){
        for(int i=0;i<100;i++){
            if(i%2==0)
                System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}

class MyThread2 extends Thread{
    public void run(){
        for(int i=0;i<100;i++){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i%2!=0)
                System.out.println(Thread.currentThread().getName()+":"+i);
//            if(i==20){
//                try {
//                    join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }
}
