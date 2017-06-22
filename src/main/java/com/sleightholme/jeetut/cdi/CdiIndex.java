package com.sleightholme.jeetut.cdi;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/cdi/", "/cdi/Index", "/cdi"})
public class CdiIndex extends ExtendedServlet {

    private static final long serialVersionUID = 9L;

    @Inject
    private ApplicationStore longStore;

    @Inject
    private SessionStore sessionsStore;

    @Inject
    private ConversationStore convsStore;

    @Inject
    private RequestStore requestStore;

    public CdiIndex() {
        super();
        title = "CDI Test";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        header();
        out.println("Served at the index of:" + request.getContextPath());
        printData(longStore);
        printData(sessionsStore);
        printData(convsStore);
        printData(requestStore);
        out.println("<p>Hello, World</p>");
        out.println("Version is " + serialVersionUID);
        printForm();

        footer();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        header();
        out.println("Served at the index of:" + request.getContextPath());
        if (request.getParameter("adata") != null) {
            String data = request.getParameter("adata");
            out.println("<p>Added data of " + data + "</p>");
            longStore.addData(data);
        }
        if (request.getParameter("sdata") != null) {
            String data = request.getParameter("sdata");
            out.println("<p>Added data of " + data + "</p>");
            sessionsStore.addData(data);
        }
        if (request.getParameter("cdata") != null) {
            String data = request.getParameter("cdata");
            out.println("<p>Added data of " + data + "</p>");
            convsStore.addData(data);
        }
        if (request.getParameter("rdata") != null) {
            String data = request.getParameter("rdata");
            out.println("<p>Added data of " + data + "</p>");
            requestStore.addData(data);
        }
        if (request.getParameter("resetConv") != null) {
            out.println("<p>Reset conversation");
            convsStore.endConversation();
            convsStore = new ConversationStore();
        }
        printData(longStore);
        printData(sessionsStore);
        printData(convsStore);
        printData(requestStore);
        printForm();
        footer();
    }

    public void printData(Store store) throws ServletException, IOException {
        ArrayList<String> data = store.getStrings();
        if (data.isEmpty()) {
            if (store instanceof ApplicationStore) {
                out.println("There is no data stored in the application scope");
            } else if (store instanceof SessionStore) {
                out.println("There is no data stored in the session scope");
            } else if (store instanceof RequestStore) {
                out.println("There is no data stored in the request scope");
            } else if (store instanceof ConversationStore) {
                out.println("There is no data stored in the conversation scope");
            }
        }

        out.println("<p>The data stored in the " + store.getName() + " scope is:</p>");
        for (String bit : data) {
            out.println(bit + "</br>");
        }
    }

    public void printForm() throws ServletException, IOException {
        out.println("<p>Store data forever in this form</p>");
        out.println("<form action=\"\" method = 'post'>");
        out.println("<input type=\"text\" name=\"adata\">");
        out.println("<input type=\"submit\" value=\"send\"></form>");

        out.println("<p>Store data for this session in this form</p>");
        out.println("<form action=\"\" method = 'post'>");
        out.println("<input type=\"text\" name=\"sdata\">");
        out.println("<input type=\"submit\" value=\"send\"></form>");

        out.println("<p>Store data for this conversation in this form</p>");
        out.println("<form action=\"\" method = 'post'>");
        out.println("<input type=\"text\" name=\"cdata\">");
        out.println("<input type=\"submit\" value=\"send\"></form>");
        out.println("<form action=\"\" method = 'post'>");
        out.println("<input type='hidden' value='resetConv'>");
        out.println("<input type='submit' value='Reset Conversation'></form>");

        out.println("<p>Store data for this request in this form</p>");
        out.println("<form action=\"\" method = 'post'>");
        out.println("<input type=\"text\" name=\"rdata\">");
        out.println("<input type=\"submit\" value=\"send\"></form>");

    }

}
