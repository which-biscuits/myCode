public class ex_32 {
    public static void main(String[] args) {
        String s = "Welcome to Hohai university";

        int index = s.indexOf("Hohai");
        System.out.println(index);

        StringBuffer strBuf1 = new StringBuffer(s);
        System.out.println(strBuf1.toString());

        strBuf1.replace(index, index + "Hohai".length(), "my");
        System.out.println(strBuf1.toString());

    }
}
