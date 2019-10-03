package com.sleightholme.jeetut.contextservice;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.misc.BASE64Encoder;

/**
 *
 * @author jonathan
 */
@WebServlet("/ProxyCreatorServlet")
public class ProxyCreatorServlet extends ExtendedServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Object proxy = true;
        String result = null;
        URL url = new URL("http://" + req.getServerName() + ":"
                + req.getServerPort() + "/jee-tut/TestServlet");
        
        //TSURL ctsurl = new TSURL();
        if ("createJNDIWork".equals(action)) {
            try {
                proxy = Util.lookupDefaultContextService().createContextualProxy(
                        new TestJNDIRunnableWork(), Runnable.class, TestWorkInterface.class,
                        Serializable.class);
            } catch (NamingException e) {
                throw new ServletException(e);
            }
        }

        if ("createClassloaderWork".equals(action)) {
            try {
                proxy = Util.lookupDefaultContextService().createContextualProxy(
                        new TestClassloaderRunnableWork(), Runnable.class,
                        TestWorkInterface.class, Serializable.class);
            } catch (NamingException e) {
                throw new ServletException(e);
            }
        }

        Properties p = new Properties();
        p.setProperty("proxy", proxyToString(proxy));
        URLConnection urlConn = TestUtil.sendPostData(p, url);
        result = TestUtil.getResponse(urlConn);
        resp.getWriter().write(result);
    }

    private String proxyToString(Object proxy) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bout)) {
            out.writeObject(proxy);
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bout.toByteArray());
    }

}
