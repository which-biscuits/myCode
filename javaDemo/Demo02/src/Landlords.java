import java.util.*;

/**
 * @author 11412
 目标：斗地主游戏的案例开发。

 业务需求分析:
 斗地主的做牌，洗牌，发牌,排序（拓展知识）, 看牌
 业务:总共有54张牌。
 点数: "3","4","5","6","7","8","9","10","J","Q","K","A","2"
 花色: "♠", "♥", "♣", "♦"
 大小王: "👲" , ""
 点数分别要组合4种花色，大小王各一张。
 斗地主：发出51张牌，剩下3张作为底牌。

 功能：
 1.做牌。
 2.洗牌
 3.定义3个玩家。
 4.发牌。
 5.排序（拓展，了解）
 6.看牌。

 用面向对象设计案例：
 a.定义一个牌类，代表牌对象。 一个牌对象代表一张牌。
 b.定义一个集合存储54张牌，集合只需要一个(因为牌只需要一副)
 */
public class Landlords {
    /* 定义一个存储 54 张牌的集合 */
    public static final List<Card> CARDS = new ArrayList<>();
    public static final String[] NUMBERS = {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
    public static final String[] COLORS = {"♠", "♥", "♣", "♦"};
    public static final String[] KINGS = {"小王","大王"};
    static {
        for (String number : NUMBERS) {
            for (String color : COLORS) {
                CARDS.add(new Card(number, color));
            }
        }
        Collections.addAll(CARDS, new Card("", KINGS[0]), new Card("", KINGS[1]));
    }

    public static void main(String[] args) {
        // 洗牌，打乱顺序
        Collections.shuffle(CARDS);
        System.out.println("洗牌后：" + CARDS);
        // 三个玩家
        List<Card> user1 = new ArrayList<>();
        List<Card> user2 = new ArrayList<>();
        List<Card> user3 = new ArrayList<>();
        List<Card> land = new ArrayList<>();
        // 发牌
        for (int i = 0; i < (CARDS.size() - 3) / 3; i++) {
            user1.add(CARDS.get(i * 3));
            user2.add(CARDS.get(i * 3 + 1));
            user3.add(CARDS.get(i * 3 + 2));
        }
        Collections.addAll(land,CARDS.subList(CARDS.size() - 3, CARDS.size()).toArray(new Card[0]));
        Collections.sort(user1);
        Collections.sort(user2);
        Collections.sort(user3);
        System.out.println("user1：" + user1);
        System.out.println("user2：" + user2);
        System.out.println("user3：" + user3);
        System.out.println("lang:" + land);
    }
}

class Card implements Comparable<Card>{
    private String number;
    private String color;
    public Card(String number, String color) {
        this.number = number;
        this.color = color;
    }

    public Card() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Card{ " + number + color + " }";
    }

    @Override
    public int compareTo(Card o) {
        if (Arrays.toString(Landlords.KINGS).contains(this.color) || Arrays.toString(Landlords.KINGS).contains(o.color)) {
            return Arrays.toString(Landlords.KINGS).indexOf(this.color) - Arrays.toString(Landlords.KINGS).indexOf(o.color);
        }
        int ans = Arrays.toString(Landlords.NUMBERS).indexOf(this.number) - Arrays.toString(Landlords.NUMBERS).indexOf(o.number);
        if (ans != 0) {
            return ans;
        }
        ans = Arrays.toString(Landlords.COLORS).indexOf(this.color) - Arrays.toString(Landlords.COLORS).indexOf(o.color);
        return ans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(number, card.number) && Objects.equals(color, card.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, color);
    }
}
