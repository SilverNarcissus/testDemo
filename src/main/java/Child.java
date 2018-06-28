/**
 * Created by SilverNarcissus on 2018/4/3.
 */
public class Child extends Father {

    @Override
    public void accept(TestMain testMain){
        testMain.visit(this);
    }
}
