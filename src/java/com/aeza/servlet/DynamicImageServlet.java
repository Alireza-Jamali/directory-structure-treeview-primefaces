package com.aeza.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DynamicImageServlet extends HttpServlet implements Serializable {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            

        try {

            // Get image file.
            request.setCharacterEncoding("UTF-8");
            String file = request.getParameter("file");

            checker(new File(file), response);
            
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

            // Get image contents.
            byte[] bytes = new byte[in.available()];

            in.read(bytes);
            in.close();

            // Write image contents to response.
            response.getOutputStream().write(bytes);
            
        } catch (IOException e) {

            System.out.println("EXCEPTION INSIDE DYNAMIC SERVLET");
            e.printStackTrace();

        }
    }
    
    private void checker(File file, HttpServletResponse response) {

    
        if (!file.exists()) {
            System.out.println("Error: this path '" + file + "' doesn't exist apparantly!");
            return;
        }

        if (file.getParent() == null) {
            System.out.println("Error: parent Directory can't be Null, choose another folder");
            return;
        }

        if (!file.isDirectory() && !file.isFile()) {
            System.out.println("system can't recoginze ur file as directory nor a file");
        }

        
    }
}