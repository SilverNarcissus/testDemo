package com.silver.narcissus.jython;

import myUtil.MyFormatter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by SilverNarcissus on 2017/3/2.
 */
public class LoggerTest {
    @Test
    public void loggerTest() {
        Logger logger = Logger.getLogger("first");

        //设置handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("../TestDemo/log.txt",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileHandler.setLevel(Level.INFO);
        logger.addHandler(fileHandler);

        //设置formatter
        fileHandler.setFormatter(new MyFormatter());
        logger.warning("warn!");
        fileHandler.close();
    }
}
