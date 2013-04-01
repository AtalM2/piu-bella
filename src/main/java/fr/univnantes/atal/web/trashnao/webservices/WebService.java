package fr.univnantes.atal.web.trashnao.webservices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

public abstract class WebService extends HttpServlet {
    
    protected ObjectMapper mapper = new ObjectMapper();
    
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        get(request, response);
    }

    protected void get(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        error(request,
                response,
                "Unsupported method: GET",
                HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method != null) {
            switch (method) {
                case "put":
                    put(request, response);
                    break;
                case "delete":
                    delete(request, response);
                    break;
            }
        } else {
            post(request, response);
        }
    }

    protected void post(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        error(request,
                response,
                "Unsupported method: POST",
                HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * Handles the HTTP
     * <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        put(request, response);
    }

    protected void put(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        error(request,
                response,
                "Unsupported method: PUT",
                HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * Handles the HTTP
     * <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        delete(request, response);
    }

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        error(request,
                response,
                "Unsupported method: DELETE",
                HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
    
    protected void error(
            HttpServletRequest request,
            HttpServletResponse response,
            String error,
            int status)
            throws ServletException, IOException {
        Map<String, String> data = new HashMap<>();
        data.put("status", "error");
        data.put("error", error);
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(mapper.writeValueAsString(data));
        }
    }
}