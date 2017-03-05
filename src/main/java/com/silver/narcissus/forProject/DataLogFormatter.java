package com.silver.narcissus.forProject;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Created by SilverNarcissus on 2017/3/4.
 */
public class DataLogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new Date() + "--");
        stringBuffer.append(record.getLevel() + ":");
        stringBuffer.append(record.getMessage() + System.lineSeparator());
        return stringBuffer.toString();
    }
}
