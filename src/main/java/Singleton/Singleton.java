package Singleton;

/**
 * Created by SilverNarcissus on 2016/11/26.
 */
public enum Singleton implements Printer{
    INSTANCE;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public void printHello(){
        System.out.println("Hello!");
    }

}
