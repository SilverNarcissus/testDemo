package myUtil;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Created by SilverNarcissus on 2017/3/2.
 */
public class MyFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
//        StringBuffer stringBuffer=new StringBuffer();
//        stringBuffer.append(new Date()+"--");
//        stringBuffer.append(record.getLevel()+":");
//        stringBuffer.append(record.getMessage()+System.lineSeparator());
//        return stringBuffer.toString();
        return "annotation!";
    }
}
