package controllers;

import static play.data.Form.form;
import models.Gebruiker;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

/**
 * Login functionality/screens
 * @author Jorian Plat
 *
 */
public class LoginApplication extends Controller{
    /**
     * Render login form
     * @return
     */
    public static Result login() {
        return ok(
            login.render(form(Login.class))
        );
    }
    
    /**
     * remove login session and redirect
     * @return
     */
    public static Result logout() {
        session().clear();
        flash("success", "U bent uitgelogd.");
        return redirect(
            routes.LoginApplication.login()
        );
    }
    
    /**
     * Validate login information
     * @return
     */
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("username", loginForm.get().username);
            
        	Gebruiker userid = Gebruiker
        			.find
        			.where()
        			.like("gebruiker_naam", loginForm.get().username)
                    .findUnique();
        	session("userid", userid.gebruiker_id.toString());
            
            return redirect(
                routes.Application.index()
            );
        }
    }
    
    public static class Login {

        public String username;
        public String password;
        
        public String validate() {
            if (Gebruiker.authenticate(username, password) == null) {
              return "Ongeldige gebruikersnaam of wachtwoord.";
            }
            return null;
        }
    }
}
