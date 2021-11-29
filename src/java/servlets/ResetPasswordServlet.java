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
                String resetParam = request.getParameter("uuid");
                
                if(resetParam != null){
                request.setAttribute("uuidParam", resetParam);
                getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request,response);
                return;
                }
                getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
                
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getParameter("resetPassword");
         AccountService as = new AccountService();
         
         if(action.equals("sendEmail")){
        String email = request.getParameter("emailAddress");
        String url = request.getRequestURL().toString();
        String path = getServletContext().getRealPath("/WEB-INF");
        as.resetPassword(email, path, url);
        request.setAttribute("message","If the email entered is part of the Note App, you will receive an email to reset your password shortly.");
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
         }
         else if (action.equals("newPassword")){
         String password = request.getParameter("newPassword");
         String uuid = request.getParameter("UUID");
         boolean validation = as.changePassword(uuid, password);
         if(validation){
             request.setAttribute("message","Password has been reset!");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
            }
         else{
             String resetParam = request.getParameter("uuid");
             request.setAttribute("uuidParam", resetParam);
             request.setAttribute("message", "An error occured while updating the password, please try again.");
             getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
         }
         }
    }
}
