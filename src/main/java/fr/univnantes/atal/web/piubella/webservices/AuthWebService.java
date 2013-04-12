package fr.univnantes.atal.web.piubella.webservices;

import fr.univnantes.atal.web.piubella.model.User;
import fr.univnantes.atal.web.piubella.security.TokenVerifier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Base class to produce a webservice.
 * 
 * Provides helpers to handle authentication.
 */
public abstract class AuthWebService extends HttpServlet {

    /**
     * Jackson mapper.
     */
    protected ObjectMapper mapper = new ObjectMapper();
    /**
     * List of allowed methods for the webservice.
     */
    protected List<Boolean> allowed_methods,
            /**
             * List of allowed authenticated methods for the webservice.
             */
            authenticated_methods;

    /**
     * Init method to set the allowed methods.
     */
    @Override
    public void init() {
        allowed_methods =
                new ArrayList(Arrays.asList(false, false, false, false));
        authenticated_methods =
                new ArrayList(Arrays.asList(false, false, false, false));
    }

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
        if (allowed_methods.get(0) || authenticated_methods.get(0)) {
            if (authenticated_methods.get(0)) {
                User user = TokenVerifier.getUser(request);
                if (user != null) {
                    auth_get(request, response, user);
                } else {
                    error(request,
                            response,
                            "Authentication failed",
                            HttpServletResponse.SC_UNAUTHORIZED);
                }
            } else {
                get(request, response);
            }
        } else {
            error(request,
                    response,
                    "Unsupported method: GET",
                    HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    /**
     * Get method when authentication is required.
     *
     * @param request
     * @param response
     * @param user
     * @throws ServletException
     * @throws IOException
     */
    protected void auth_get(
            HttpServletRequest request,
            HttpServletResponse response,
            User user)
            throws ServletException, IOException {
    }

    /**
     * Get method when authentication is not required.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void get(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
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
                    doPut(request, response);
                    return;
                case "delete":
                    doDelete(request, response);
                    return;
            }
        }
        if (allowed_methods.get(1) || authenticated_methods.get(1)) {
            if (authenticated_methods.get(1)) {
                User user = TokenVerifier.getUser(request);
                if (user != null) {
                    auth_post(request, response, user);
                } else {
                    error(request,
                            response,
                            "Authentication failed",
                            HttpServletResponse.SC_UNAUTHORIZED);
                }
            } else {
                post(request, response);
            }
        } else {
            error(request,
                    response,
                    "Unsupported method: POST",
                    HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    /**
     * Post method when authentication is required.
     *
     * @param request
     * @param response
     * @param user
     * @throws ServletException
     * @throws IOException
     */
    protected void auth_post(
            HttpServletRequest request,
            HttpServletResponse response,
            User user)
            throws ServletException, IOException {
    }

    /**
     * Post method when authentication is not required.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void post(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
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
        if (allowed_methods.get(2) || authenticated_methods.get(2)) {
            if (authenticated_methods.get(2)) {
                User user = TokenVerifier.getUser(request);
                if (user != null) {
                    auth_put(request, response, user);
                } else {
                    error(request,
                            response,
                            "Authentication failed",
                            HttpServletResponse.SC_UNAUTHORIZED);
                }
            } else {
                put(request, response);
            }
        } else {
            error(request,
                    response,
                    "Unsupported method: PUT",
                    HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    /**
     * Put method when authentication is required.
     *
     * @param request
     * @param response
     * @param user
     * @throws ServletException
     * @throws IOException
     */
    protected void auth_put(
            HttpServletRequest request,
            HttpServletResponse response,
            User user)
            throws ServletException, IOException {
    }

    /**
     * Put method when authentication is not required.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void put(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
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
        if (allowed_methods.get(3) || authenticated_methods.get(3)) {
            if (authenticated_methods.get(3)) {
                User user = TokenVerifier.getUser(request);
                if (user != null) {
                    auth_delete(request, response, user);
                } else {
                    error(request,
                            response,
                            "Authentication failed",
                            HttpServletResponse.SC_UNAUTHORIZED);
                }
            } else {
                delete(request, response);
            }
        } else {
            error(request,
                    response,
                    "Unsupported method: DELETE",
                    HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    /**
     * Delete method when authentication is required.
     *
     * @param request
     * @param response
     * @param user
     * @throws ServletException
     * @throws IOException
     */
    protected void auth_delete(
            HttpServletRequest request,
            HttpServletResponse response,
            User user)
            throws ServletException, IOException {
    }

    /**
     * Delete method when authentication is not required.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Helper method to produce an error.
     *
     * @param request
     * @param response
     * @param error
     * @param status
     * @throws ServletException
     * @throws IOException
     */
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