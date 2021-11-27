package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;

public class ResetPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("emailAddress");
        String url = request.getRequestURL().toString();
        String path = getServletContext().getRealPath("/WEB-INF");
        AccountService as = new AccountService();
        
        as.resetPassword(email, path, url);
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    }
}
