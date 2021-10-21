public class ex_31 {
    public static void main(String[] args) {
        deposit dep1 = new deposit("dep1");
        withdraw wth1 = new withdraw("wth1");

        System.out.println(deposit.getName());
        System.out.println(withdraw.getName());

        dep1.use(78.49);
        wth1.use(12.5);

        System.out.println(Card.account);
    }
}

class Card {
    static double account = 0;

    public void use(double money) {
        if (this.getClass().toString().equals("class deposit")){
            Card.account += money;
            System.out.println(String.format("class_despoit: account + %.2f",money));
        } else if (this.getClass().toString().equals("class withdraw")){
            Card.account -= money;
            System.out.println(String.format("class_withhdraw: account - %.2f",money));
        }
    }
}

class deposit extends Card {
    static String name;
    final String className = "deposit";

    public deposit(String name) {
        deposit.name = name;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        deposit.name = name;
    }

}

class withdraw extends Card {
    static String name;
    final String className = "withdrow";

    public withdraw(String name) {
        withdraw.name = name;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        withdraw.name = name;
    }

}
