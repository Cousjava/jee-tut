package com.sleightholme.jeetut.mysql;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * @author jonathan
 * Servlet implementation class MysqlExample
 */
@WebServlet({"/mysqlexample", "/Mysql"})
public class MysqlExample extends ExtendedServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    Queries q;

    @Resource(name = "jdbc/__default")
    DataSource ds;

    HashMap<String, Integer> data;

    @Override
    public void init() throws ServletException {
        super.init();
        title = "MySQL Connection Example";
    }
    
    
    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        header();
        if (ds == null) {
            out.println("Data source in servlet is null");
        } else {
            out.println("Data source is valid!");
            out.println(ds.toString());
            out.println();
        }
        
        try {
            q.postConstruct();
            setupData();
            for (String rname : data.keySet()) {
                q.insertData(rname, data.get(rname));
            }
            ResultSet rs = q.getData();
            if (rs == null) {
                out.println("<p>Unable to retrive data</p>");
            } else {
                printData(rs);
            }

        } catch (Exception e) {
            out.println("<p>Error with database...</p>");
            e.printStackTrace();
        }

        out.println("<p>8</p>");
        out.append("Served at: ").append(request.getContextPath());

        footer();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        run(request, response);
    }

    private void setupData() {
        data = new HashMap<String, Integer>();
        data.put("Amazon", 3889);
        data.put("Madeira", 2020);
        data.put("Japura", 1995);
        data.put("Tocantins", 1640);
        data.put("Araguia", 1632);
        data.put("Jurua", 1500);
        data.put("Rio Negro", 1400);
        data.put("Tapajos", 1238);
        data.put("Xingu", 1230);
        data.put("Ucayali", 1200);
        data.put("Guapore", 1087);

    }

    private void printData(ResultSet rs) {
        
        try {
            out.println("<table>");
            while (rs.next()) {

                int id = rs.getInt(1);
                String rname = rs.getString(2);
                int length = rs.getInt(3);
                out.print("<tr><td>" + id + "</td>");
                out.print("<td>" + rname + "</td><td>" + length + "</td></tr>\n");
            }
            out.println("</table>");
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }

}
