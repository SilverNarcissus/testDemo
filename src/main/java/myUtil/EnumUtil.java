package myUtil;

import java.util.EnumSet;
import java.util.HashMap;

/**
 * Created by SilverNarcissus on 2017/1/22.
 * Version 1.0 on 2017/1/22.
 */
public enum EnumUtil {
    TEST("测试") {
        @Override
        void doSomething() {
            //Do some unique thing using specific enum value
        }
    };

    /**
     * This map is used to bound unique string from toString method and enum value
     */
    private static final HashMap<String, EnumUtil> stringToEnum = new HashMap<String, EnumUtil>();

    //bound unique string from toString method and enum value
    static {
        for (EnumUtil enumUtil : EnumUtil.values()) {
            stringToEnum.put(enumUtil.toString(), enumUtil);
        }
    }

    /**
     * True name of the enum value
     */
    private final String name;

    EnumUtil(String name) {
        this.name = name;
    }

    /**
     * To return the true name of enum
     *
     * @return true name
     */
    @Override
    public String toString() {
        return name;
    }


    /**
     * Use true name to get the enum value
     * @param name True name
     * @return Enum value or null if the true name isn't associate with an enum value
     */
    public EnumUtil fromString(String name){
        return stringToEnum.get(name);
    }

    /**
     * Constant-specific method implementation
     */
    abstract void doSomething();
}
