package services;

import dataaccess.UserDB;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {
    
    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
              Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);
              
              //simple, plain text email
              //GmailService.sendMail(email, "New Login to Notes App", "User has logged in", false);
              

                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                GmailService.sendMail(to, subject, template, tags);

                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    public void resetPassword(String email, String path, String url){
        String uuid = UUID.randomUUID().toString();
        String link = url + "?uuid=" + uuid;
        UserDB userDB = new UserDB();
        //now we generate the email
         User user = userDB.get(email);
         user.setResetPasswordUuid(uuid);
         userDB.update(user);
         String to = user.getEmail();
         String subject = "Notes App Reset Password";
         String template = path + "/emailtemplates/reset.html";
         
         HashMap<String, String> tags = new HashMap<>();
         tags.put("firstname", user.getFirstName());
         tags.put("lastname", user.getLastName());
         tags.put("link", link);
         // send email
        try {
            GmailService.sendMail(to, subject, template, tags);
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
          } 
    
    public boolean changePassword(String uuid, String password) {
       UserDB userDB = new UserDB();
        try {
            User user = userDB.getByUUID(uuid);
            user.setPassword(password);
            user.setResetPasswordUuid(null);
            userDB.update(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
