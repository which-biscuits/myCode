/**
 * @author 11412
 */
public class Test3 {
    public static void main(String[] args) {
        //     1）创建类，驾驶员
        //    驾驶员的属性：级别（“新手”或“老手”）
        //    驾驶员的行为：返回级别
        Driver driver1 = new Driver("老手");
        System.out.println(driver1.getGrade());

        //    2）汽车类的子类
        //    添加汽车属性：
        //    1）驾驶员（2）最高速度
        Car car1 = new Car(45,45,45,driver1);

        //    添加汽车行为：
        //    1）设定最高速度：如果驾驶员是“新手”，最高速度赋值30；如果是“老手”，最高速度赋值60
        //    2）变速提醒：如“新手”速度大于30，显示“请放慢！”；如“老手”速度大于60，显示“违规！”
        System.out.println(car1.getMaxSpeed());
        car1.setMaxSpeed(70);
        car1.setMaxSpeed(56);
        System.out.println(car1.getMaxSpeed());
    }


}

class Vehicle {
    private double speed;
    private double dist;
    private double time;

    public Vehicle (double speed, double dist,double time) {
        this.speed = speed;
        this.dist = dist;
        this.time = time;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public double getSpeed() { return speed;}

    public double getDist() { return dist;}

    public double getTime() { return time;}

    public void setTime(double time) {
        this.time = time;
    }

    public void showInfo() {
        System.out.println("此辆车行驶了" + time + "小时");
    }
}

class Driver {
    // 驾驶员等级
    private String grade;

    Driver(String grade) {
        if ("新手".equals(grade) || "老手".equals(grade)) {
            this.grade = grade;
        } else {
            System.out.println("驾驶员等级输入错误");
        }
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        if ("新手".equals(grade) || "老手".equals(grade)) {
            this.grade = grade;
        } else {
            System.out.println("驾驶员等级输入错误");
        }
}
}

class Car extends Vehicle {
    private Driver myDriver;
    private int maxSpeed;

    public Car(double speed, double dist, double time, Driver myDriver) {
        super(speed, dist, time);
        this.myDriver = myDriver;
        if (myDriver.getGrade().equals("新手")) {
            this.maxSpeed = 30;
        } else if (myDriver.getGrade().equals("老手")) {
            this.maxSpeed = 60;
        }
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        if ("新手".equals(myDriver.getGrade()) && maxSpeed > 30) {
            System.out.println("请放慢你的速度");
        } else if ("老手".equals(myDriver.getGrade()) && maxSpeed > 60) {
            System.out.println("您已违规");
        } else {
            this.maxSpeed = maxSpeed;
        }
    }

    public Driver getMyDriver() {
        return myDriver;
    }

    public void setMyDriver(Driver myDriver) {
        this.myDriver = myDriver;
    }
}

