package com.sleightholme.jeetut.json;

import com.sleightholme.jeetut.util.ExtendedServlet;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JsonBlob
 */


@WebServlet("/Json/Blob")
public class JsonBlob extends ExtendedServlet {

    private static final long serialVersionUID = 1L;

    private String title = "JSON Reader Example";

    JsonArray obj;

    /**
     * @see ExtendedServlet#ExtendedServlet()
     */
    public JsonBlob() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        header();

        readData("/test.json");
        displayObject(obj);
        String filePath = request.getServletContext().getRealPath("/");
        out.println(filePath);
        writeObject(obj, filePath + "out.json");
        footer();
    }

    private void readData(String filepath) {
        JsonReader read;
        try {
            read = Json.createReader(getServletContext().getResourceAsStream(filepath));

            obj = read.readArray();
        } catch (NullPointerException e) {
            out.println("File not found");
            e.printStackTrace();
        }

    }

    private void displayObject(JsonArray obj) {
        out.println(obj.toString());

    }

    private void writeObject(JsonArray obj, String filepath) {

        
        try (BufferedWriter write = new BufferedWriter(new FileWriter(filepath))) {
            write.write(obj.toString());
            out.println("<p>Successfully written to file</p>");
        } catch (FileNotFoundException e) {
            out.println("<p>Error writing to file...</p>");
            e.printStackTrace();
        } catch (IOException e1) {
            out.println("<p>Error writing to file...</p>");
            e1.printStackTrace();
        }

    }

}
