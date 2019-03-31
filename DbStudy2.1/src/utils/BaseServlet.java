package utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding ("utf-8");
        resp.setCharacterEncoding ("utf-8");
        String methodName = req.getParameter ("method");
        if (methodName == null || methodName.isEmpty ()) {
            methodName = "execute";
        }
        Class<? extends BaseServlet> c = this.getClass ();
        try {
            Method method = c.getMethod (methodName, HttpServletRequest.class, HttpServletResponse.class);
            String result = (String) method.invoke (this, req, resp);
            if (result != null && !result.isEmpty ()) {
                int index = result.indexOf (':');
                String fun = result.substring (0, index);
                String url = result.substring (index + 1);
                if (fun.equals ("f")) {
                    req.getRequestDispatcher (url).forward (req, resp);
                }
                if (fun.equals ("s")) {
                    resp.sendRedirect (url);
                }
            }

        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        }
    }

}
