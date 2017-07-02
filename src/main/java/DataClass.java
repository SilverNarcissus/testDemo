import java.io.Serializable;

/**
 * Created by SilverNarcissus on 2017/3/30.
 */
public class DataClass implements Serializable{
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;

    public DataClass(String data) {
        this.data = data;
    }
}
