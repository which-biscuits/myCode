public class Initsequence {
    static Tools ts2 = new Tools();
    Tools ts1 = new Tools();
    {
        System.out.println("Hello");
    }
    Initsequence(){
        System.out.println("Initsequence");
    }

    public static void main(String[] args) {
        System.out.println("calll Tools.t4.f(4) in main()");
        Tools.t4.f(4);
        System.out.println("Creating new Tools() in main()");
        new Tools();
        System.out.println("Creating new InitSquence() in main()");
        new Initsequence();
    }
}

class Tools{
    static Tool t3 = new Tool(3);
    static Tool t4 = new Tool(4);
    static{
        System.out.println("进入静态初始化块");
        t3 = new Tool(33);
        t4 = new Tool(44);
        System.out.println("退出静态初始化块");
    }
    {
        System.out.println("进入实例初始化块");
        t1 = new Tool(11);
        t2 = new Tool(22);
        System.out.println("退出实例初始化块");
    }
    Tool t1 = new Tool(1);
    Tool t2 = new Tool(2);
    Tools(){
        System.out.println("Tools()");
        t2 = new Tool(222);
    }
}

class Tool{
    Tool(int i){
        System.out.println("Tool(" + i + ")");
    }
    void f(int i){
        System.out.println("f(" + i + ")");
    }
}
