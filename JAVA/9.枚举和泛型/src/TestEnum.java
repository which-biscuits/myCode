public class TestEnum {
    public static void main(String[] args) {
        Season[] seasons = Season.values();
        System.out.println("seasons = " + java.util.Arrays.toString(seasons));
        for (Season sa:seasons) {
            switch (sa) {
                case 春季: {
                    System.out.println(sa.ordinal() + " : " + sa.toString() + ", 乍暖还寒");
                    break;
                }
                case 夏季: {
                    System.out.println(sa.ordinal() + " : " + sa.toString() + ", 骄阳似火");
                    break;
                }
                case 秋季: {
                    System.out.println(sa.ordinal() + " : " + sa.toString() + ", 秋高气爽");
                    break;
                }
                case 冬季: {
                    System.out.println(sa.ordinal() + " : " + sa.toString() + ", 滴水成冰");
                    break;
                }

            }
        }
    }
}

enum Season {春季, 夏季, 秋季, 冬季}