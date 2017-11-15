package com.pengchen;

import org.json.JSONObject;

import javax.servlet.http.Part;
import java.io.*;

/**
 * Created by CP on 12/11/2017.
 */
public class Upload extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        request.setCharacterEncoding("UTF-8");
        //get part object
        Part file = request.getPart("file");
        String fileName = getSubmittedFileName(file);
        //save file
        //read from stream
        InputStream inputStream = file.getInputStream();
        byte[] bytes = new byte[(int) file.getSize()];
        inputStream.read(bytes);
        inputStream.close();
        String filePath = getServletContext().getRealPath("/UploadField/file.png");
        File newFile = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
        //write into file
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();
        System.out.println(file.getSize() + "  " + filePath);

        //response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        DigitClassifier dc = new DigitClassifier();
        String result = dc.GetRecResult(filePath);
        JSONObject o1 = new JSONObject();
        o1.put("mes", result);
        PrintWriter out = response.getWriter();
        out.print(o1);
        out.flush();

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
