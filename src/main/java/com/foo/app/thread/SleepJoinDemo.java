package com.foo.app.thread;

/**

 * User: zongjh
 * Date: 13-6-8
 * Time: 下午4:07
 * *sleep()是静态方法，是属于类的，作用是让当前线程阻塞
 *join()是使线程同步，如在某个线程里调用t.join()表示t线程执行完再执行当前线程
 *interrupt()给线程设定一个标志表示该线程已被中断，但在异常捕获时将清理这个标志
 *所以在catch子句中，该标志为false
 */
public class SleepJoinDemo {

    public static void main(String[] args) {
        Sleeper sleep1 = new Sleeper("sleep1", 1500);
        Sleeper sleep2 = new Sleeper("sleep2", 1500);
        Joiner join1 = new Joiner("join1", sleep1);
        Joiner join2 = new Joiner("join2", sleep1);
        sleep2.interrupt();
    }
}

class Sleeper extends Thread{
    private int sleepTime;

    public Sleeper(String name,int sleepTime){
        super(name);
        this.sleepTime = sleepTime;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted."
                    + "isInterrupted():" + isInterrupted());
            return;
        }
        System.out.println(getName() + " has awakened");
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run() {
        try {
            sleeper.join();//合并，异步变同步
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
        System.out.println(getName() + " join completed");
    }
}
