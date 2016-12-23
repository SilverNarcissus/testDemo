package DataStructure;

/**
 * Created by SilverNarcissus on 2016/12/14.
 */
public class Vertex {
    /**
     * Number
     */
    public int id;
    /**
     * content
     */
    public Object o;
    /**
     * 入度
     */
    public int in;
    /**
     * 出度
     */
    public int out;

    public Vertex(int id, Object o) {
        this.id = id;
        this.o = o;
    }

    public Vertex(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "id: "+id;
    }
}
