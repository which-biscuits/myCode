public class instanceof_object {
    public static void main(String[] args) {
        Airplane airplane1 = new Warplane();
        Airplane airplane2 = new Fighter();
        Warplane warplane1,warplane2;
        Fighter fight1,fight2;

        airplane2.setSpeed(600);

        if(airplane1 instanceof Warplane) {
            System.out.println("airplane1 引用的是一个Warplane对象");
            warplane1 = (Warplane) airplane1;
            System.out.println("warplane1.speed = " + warplane1.getSpeed() +
                    "\n warplane1.missileNumber = " + warplane1.getMissileNumber());
        } else {
            System.out.println("airplane1 引用的不是一个 Warplane 对象\n");
        }
        if(airplane2 instanceof Warplane) {
            System.out.println("airplane2 引用的是一个Warplane对象");
            warplane2 = (Warplane) airplane2;
            System.out.println("warplane1.speed = " + warplane2.getSpeed() +
                    "\n warplane1.missileNumber = " + warplane2.getMissileNumber());
        } else {
            System.out.println("airplane2 引用的不是一个 Warplane 对象\n");
        }
        if(airplane1 instanceof Fighter) {
            System.out.println("airplane1 引用的是一个Fighter对象");
            fight1 = (Fighter) airplane1;
            fight1.print();
            System.out.println();
        } else {
            System.out.println("airplane1 引用的不是一个Fighter对象");
        }
        if(airplane2 instanceof Fighter) {
            System.out.println("airplane2 引用的是一个Fighter对象");
            fight2 = (Fighter) airplane2;
            fight2.print();
            System.out.println();
        } else {
            System.out.println("airplane2 引用的不是一个Fighter对象");
        }
    }
}

class Fighter extends Warplane {
    public void print() {
        System.out.println("It is a fighter !");
    }
}

class Warplane extends Airplane {
    private int missileNumber = 8;

    public void setMissileNumber(int number) {
        missileNumber = number;
    }

    public int getMissileNumber() {
        return missileNumber;
    }
}

class Airplane {
    private int speed = 500;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
