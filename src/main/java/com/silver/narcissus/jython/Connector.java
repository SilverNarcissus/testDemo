package com.silver.narcissus.jython;

import org.python.core.*;
import org.python.util.PythonInterpreter;

/**
 * Created by SilverNarcissus on 2017/2/27.
 */
public class Connector {
    PythonInterpreter interpreter;

    public Connector() {
        interpreter = new PythonInterpreter();

        PySystemState sys = Py.getSystemState();
        System.out.println(sys.path.toString());    // previous
        sys.path.add("/Library/Frameworks/Python.framework/Versions/3.5/lib/python3.5");
        sys.path.add("/Library/Frameworks/Python.framework/Versions/3.5/lib/python3.5/site-packages");
        sys.path.add("/Library/Frameworks/Python.framework/Versions/3.5/lib/python3.5/site-packages/requests");
        System.out.println(sys.path.toString());   // later
    }

    /**
     * 执行一行python命令
     *
     * @param pythonCommand python语句
     */
    public void executeCommand(String pythonCommand) {
        interpreter.exec(pythonCommand);
    }

    /**
     * 执行一个python文件
     *
     * @param filePath python文件名
     */
    public void executeFile(String filePath) {
        interpreter.execfile(filePath);

    }

    /**
     * 执行一个python函数
     *
     * @param filePath     python函数所在文件名
     * @param functionName 函数名
     * @return 函数返回的结果的字符串表示形式
     */
    public String executeFunction(String filePath, String functionName) {
        interpreter.execfile(filePath);

        PyFunction func = (PyFunction) interpreter.get(functionName, PyFunction.class);
        PyObject pyObject = func.__call__();
        return pyObject.toString();
    }
}
