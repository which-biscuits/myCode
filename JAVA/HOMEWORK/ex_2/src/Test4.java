import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class Test4 {
    public static void main(String[] args) {
        //     1）创建类，驾驶员
        //    驾驶员的属性：级别（“新手”或“老手”）
        //    驾驶员的行为：返回级别
        driver driver1 = new driver("老手");
        System.out.println(driver1.getGrade());

        //    2）汽车类的子类
        //    添加汽车属性：
        //    1）驾驶员（2）最高速度
        car car1 = new car(45,45,45,driver1);

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

class driver {
    private String grade;   // 驾驶员等级

    driver(String grade) {
        if (grade.equals("新手") || grade.equals("老手"))
            this.grade = grade;
        else
            System.out.println("驾驶员等级输入错误");
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        if (grade.equals("新手") || grade.equals("老手"))
            this.grade = grade;
        else
            System.out.println("驾驶员等级输入错误");
}
}

class car extends Vehicle {
    private driver myDriver;
    private int maxSpeed;

    public car(double speed, double dist, double time, driver myDriver) {
        super(speed, dist, time);
        this.myDriver = myDriver;
        if (myDriver.getGrade().equals("新手"))
            this.maxSpeed = 30;
        else if (myDriver.getGrade().equals("老手"))
            this.maxSpeed = 60;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        if (myDriver.getGrade().equals("新手") && maxSpeed > 30)
            System.out.println("请放慢你的速度");
        else if (myDriver.getGrade().equals("老手") && maxSpeed > 60)
            System.out.println("您已违规");
        else
            this.maxSpeed = maxSpeed;
    }

    public driver getMyDriver() {
        return myDriver;
    }

    public void setMyDriver(driver myDriver) {
        this.myDriver = myDriver;
    }
}

