package com.ibm.upd.report.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/report",name = "ReportServlet")
public class ReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String templateFileName = "upd_report_template.xls";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(templateFileName);

        // modifies response
        response.setContentType("application/octet-stream");

        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=\"upd-report.xls\"";
        response.setHeader(headerKey, headerValue);

        // obtains response's output stream
        OutputStream out = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        in.close();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
