package com.pengchen;

import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by CP on 12/11/2017.
 */
public class test extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("/Users/CP/Documents/Study/code/intellij/MobileComputing/server/src/python");
        PyFunction pyFunction = interpreter.get("hello", PyFunction.class); // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyObject pyObject = pyFunction.__call__(); // 调用函数

        PrintWriter out = response.getWriter();
        out.println(pyObject);




    }
}
