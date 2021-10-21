import java.util.*;

public class JavaHomeWork {
    public static void main(String[] args) {
        // 获取中奖号码
        System.out.println("获取中奖号码为:");
        System.out.println(Arrays.toString(lottery.getAns()));
        System.out.println("========================");

        // 自定义中奖号码
        System.out.println("自定义中奖号码:");
        lottery.setAns(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println(Arrays.toString(lottery.getAns()));
        System.out.println("========================");

        // 重置中奖号码
        lottery.reset();
        System.out.println("随机重置中奖号码:");
        System.out.println(Arrays.toString(lottery.getAns()));
        System.out.println("========================");

        // 游戏 1000 次收益
        System.out.println("开始模拟1000次彩票购买...");
        user user1 = new user();
        user1.play(1000);
        System.out.println("========================");

        // 至三等奖的花费
        int money = user1.money_3();
        System.out.println(String.format("到第一次获得三等奖共花费 %d 元",money));
        System.out.println("========================");


        // 控制台交互游戏
        System.out.println("控制台交互游戏开始:");
        System.out.println("重置中奖号码...");
        lottery.reset();
        System.out.println("请输入您的号码选择:");
        System.out.println("(1-49 七位不重复数字)");
        user.playInConsole();
        System.out.println("中奖号码为:");
        System.out.println(Arrays.toString(lottery.getAns()));



//        // 游戏一次
//        System.out.println("用户输入进行一次游戏:");
//        System.out.println("您选对了" + lottery.bonus(new int[]{3, 6, 7, 9, 14, 26, 36}) + "个数字");
    }
}

class user {
    private int money = 0;  // 用户收益
    private int[] times = new int[6];   // 用户中奖次数

    // 用户游戏
    public void play(int time){
        this.money = 0;
        System.out.println("中奖号码为:" + Arrays.toString(lottery.getAns()));
        for (int i = 0; i < time; i++) {
            int[] u_choice = choice(7);
            // 用户获奖等级
            int grade = lottery.bonus(u_choice);
            switch (grade) {
                case 2: { money += 5; times[5]++; break; }
                case 3: { money += 10; times[4]++; break; }
                case 4: { money += 100; times[3]++; break; }
                case 5: { money += 1000; times[2]++; break; }
                case 6: { money += 200000; times[1]++; break; }
                case 7: { money += 5000000; times[0]++; break; }
            }
        }
        System.out.println(String.format("中奖次数:一等奖 %d 次, 二等奖 %d 次, 三等奖 %d 次, 四等奖 %d 次, " +
                "五等奖 %d 次, 六等奖 %d 次", times[0],times[1],times[2],times[3],times[4],times[5]));
        System.out.println(String.format("用户购买 %d 张彩票后共中奖:%d 元",time,this.money));
    }
    // 产生用户选择
    public int[] choice(int num) {
        // 借助java.util.Random类来产生一个随机数发生器 使用默认时间种子
        Random r = new Random();
        int[] ans = new int[num];
        for (int i = 0; i < num; ++i) {
            int use = (int) (r.nextFloat() * 50);
            while (judge(use,ans))
                use = (int) (r.nextFloat() * 50);
            ans[i] = use;
        }
        return ans;
    }
    // 判断随机数是否在列表中,即中奖号码 不存在重复数字
    public boolean judge(int a, int[] b){
        for(int i: b){
            if (a == i)
                return true;
        }
        return false;
    }
    // 获得三等奖的花费
    public int money_3() {
        int count = 1;
        int[] u_choice = choice(7);
        while (lottery.bonus(u_choice) != 5){
            u_choice = choice(7);
            count++;
        }
        return count * 5;
    }
    // 控制台交互游戏
    public static void playInConsole() {
        Scanner scanner = new Scanner(System.in);
        int[] u_choice = new int[7];
        for (int i = 0; i < 7;i++)
            u_choice[i] = scanner.nextInt();
        int grade = lottery.bonus(u_choice);
        String gra = null;
        switch (grade) {
            case 2: { gra = "六"; break; }
            case 3: { gra = "五"; break; }
            case 4: { gra = "四"; break; }
            case 5: { gra = "三"; break; }
            case 6: { gra = "二"; break; }
            case 7: { gra = "一"; break; }
            default:
                System.out.println("很遗憾,您未能获奖!");
        }
        if (gra!=null)
            System.out.println("恭喜您获得了"+gra+"等奖!");
    }
}

class lottery {
    private static int[] ans = new int[7];     // 存放中奖号码
    // 初始化中奖号码
    static {
        lottery.ans = numbers(7);
    }
    // 获取中奖号码
    public static int[] getAns() {
        return ans;
    }
    // 自定义中奖号码
    public static void setAns(int[] ans) {
        lottery.ans = ans;
    }
    // 返回用户 中奖的等级
    public static int bonus(int[] user_choice) {
        int count = 0;
        for(int a:lottery.ans){
            for(int b:user_choice){
                if (a == b)
                    count++;
            }
        }
        return count;
    }
    // 重置中奖号码
    public static void reset() {
        lottery.ans = numbers(7);
    }
    // 产生一组随意数
    public static int[] numbers(int num) {
        // 借助java.util.Random类来产生一个随机数发生器 使用默认时间种子
        Random r = new Random();
        int[] ans = new int[num];
        for (int i = 0; i < num; ++i) {
            int use = (int) (r.nextFloat() * 50);
            while (judge(use,ans))
                use = (int) (r.nextFloat() * 50);
            ans[i] = use;
        }
        return ans;
    }
    // 判断随机数是否已经存在,即中奖号码 不存在重复数字
    public static boolean judge(int a, int[] b){
        for(int i: b){
            if (a == i)
                return true;
        }
        return false;
    }
}