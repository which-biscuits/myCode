import java.lang.Runnable;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Subject1 {
    public static void main(String[] args) throws BankException {
        Bank bank1 = new Bank();
        bank1.deposit(30.5);
        bank1.deposit(20.5);


//        bank1.withdrow(10);
//        bank1.withdrow(100);
    }
}

class Subject2 implements Runnable {
    String name;
    Bank bank;

    public Subject2(String name,Bank bank) {
        this.name = name;
        this.bank = bank;
    }

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Subject2 husband = new Subject2("husband",bank);
        Subject2 wife = new Subject2("wife",bank);

        new Thread(husband).start();
        new Thread(wife).start();

//        Thread.sleep(60*1000);
    }

    @Override
    public void run() {
        int count = 0;
        while (count < 1000) {
            count++;
            if (name.equals("husband")) {
                Random r = new Random();
                try {
                    Thread.sleep((long) (r.nextFloat()*5000));
                    bank.deposit(5000);
                    this.notifyAll();
                } catch (Exception e) {
                    System.out.println("husband休眠失败一次");
                }
            } else if (name.equals("wife")) {
                Random r = new Random();
                try {
                    Thread.sleep(1000);
                    try {
                        bank.withdrow(r.nextFloat() * 1000);
                    } catch (BankException ignored) { this.wait();}
                } catch (Exception e) {
                    System.out.println("wife休眠失败一次");
                }
            }
        }
    }
}



class Bank {
    private double account;

    public void deposit(double money) { // 存钱
        this.account += money;
        System.out.println(String.format("存入 %.2f, 剩余 %.2f",money,this.account));
    }

    public void withdrow(double money) throws BankException {   // 取钱
        if (account <= 0 || account < money) throw new BankException("余额不足");
        else if (money > 1000) throw new BankException("超出额度");
        this.account -= money;
        System.out.println(String.format("取出 %.2f, 剩余 %.2f",money,this.account));
    }
}

class BankException extends Exception {
    public BankException(String s) {
        super(s);
    }
}

/*P1．编写如下程序。
1）	创建银行(Bank)类，有余额(double account)变量和存钱(deposit(...))、取钱(withdraw(...))方法。
存取时显示“剩余***”。
2）	创建异常(BankException)类，继承构造方法BankException(String s){super(s);}。
3）	修改withdraw(...)方法，余额小于0或取钱额度超出1000时抛出BankException。
4）	测试两种异常，每次捕获时需显示不同的异常名，如“余额不足”、“超出额度”。
*/
/*P2.	编写如下程序。
1）	继续第一题的Bank。
a)	存钱时显示“存入***，余额为***”
b)	取钱时显示“取出***，余额为***”
2）	创建两个线程，丈夫(husband)、妻子(wife)，husband每0-5秒(随机)存入5000元，wife每1秒取出0-1100(随机)元。
3）	运行两个线程。
（附加题）同步线程，余额足够时才可以取出，重新运行两个线程。
*/
