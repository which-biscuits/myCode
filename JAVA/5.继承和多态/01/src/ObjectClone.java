public class ObjectClone {
    public static void main(String[] args) {
        aClone a1 = new aClone(1);
        aClone a2 = (aClone) a1.clone();
        System.out.println("a2 是 a1 的克隆");
        if(a1 == a2){
            System.out.println("a1/a2 引用同一对象!");
        } else {
            System.out.println("a1/a2 引用不同对象!");    // a2 是 a1 的深度复制,产生新的内存空间
        }
        if(a1.equals(a2)) {
            System.out.println("a1/a2 中的内容相同!");    // 重写了 equals 方法,当对象中的num值相同时,即返回true
        } else {
            System.out.println("a1/a2 中的内容不相同!");
        }
        System.out.println("\n");

        bClone b1 = new bClone(1);
        bClone b2 = (bClone) b1.clone();
        System.out.println("b2 是 b1 的克隆");
        if(b1 == b2){
            System.out.println("b1/b2 引用同一对象");
        } else {
            System.out.println("b1/b2 引用不同对象");
        }
        if(b1.a == b2.a){
            System.out.println("b1.a/b2.a 引用同一对象");
        } else {
            System.out.println("b1.a/b2.a 引用不同对象");
        }
        b2.a.num = 10;
        System.out.println("b1.a.num = " + b1.a.num);
        System.out.println("b2.a.num = " + b2.a.num);
        System.out.println("\n");

        cClone c1 = new cClone(1);
        cClone c2 = (cClone) c1.clone();
        System.out.println(" c2 是 c1 的克隆");
        if(c1 == c2){
            System.out.println("c1/c2 引用同一对象");
        } else {
            System.out.println("c1/c2 引用不同对象");
        }
        if(c1.a == c2.a){
            System.out.println("c1.a/c2.a 引用同一对象");
        } else {
            System.out.println("c1.a/c2.a 引用不同对象");
        }
        c1.a.num = 10;
        System.out.println("c1.a.num = " + c1.a.num);
        System.out.println("c2.a.num = " + c2.a.num);
        System.out.println("\n");

        dClone d1 = new dClone();
        if(d1 instanceof Cloneable){
            System.out.println("类 dClone 对象可以克隆!");
        } else {
            System.out.println("类 dClone 对象不可以克隆!");
        }
    }
}

class aClone implements Cloneable {
    int num;

    public aClone(int num) {
        this.num = num;
    }

    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("类 aClone 对象不能克隆!");
        }
        return obj;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof aClone){
            return (((aClone) obj).num == num);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return num;
    }

    @Override
    public String toString(){
        return ("" + num);
    }
}

class bClone implements Cloneable {
    aClone a;

    public bClone(int num) {
        a = new aClone(num);
    }

    public Object clone(){
        Object obj = null;
        try{
            obj = super.clone();
        } catch (CloneNotSupportedException e){
            System.out.println("类 bClone 对象不能克隆");
        }
        return obj;
    }
}

class cClone implements Cloneable{
    aClone a;

    public cClone(int num){
        a = new aClone(num);
    }

    public Object clone(){
        cClone obj = null;
        try {
            obj = (cClone) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("类 cClone 对象不能克隆!");
        }
        obj.a = (aClone) obj.a.clone();
        return obj;
    }
}

class dClone {}