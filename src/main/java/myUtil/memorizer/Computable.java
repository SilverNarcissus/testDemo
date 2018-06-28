package myUtil.memorizer;

/**
 * Created by SilverNarcissus on 2018/6/28.
 */
public interface Computable<I, V> {
    V compute(I input);
}
