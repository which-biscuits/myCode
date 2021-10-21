import ch05.B;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class C {
    public static void main(String[] args) {
        B b = new B();
        B c = new B();
        // b.i = 11;    // 默认访问权限, 只可在所属包中访问
        // b.j = 22;    // protected 只可在所属包 及 所属类的子类中访问
        b.k = 33;       // 公有成员可在包外访问
    }
}
