package wzx.model;
import java.io.Serializable;

public class CounterBean implements Serializable{
    private int count = 0;
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public CounterBean() {}
}
