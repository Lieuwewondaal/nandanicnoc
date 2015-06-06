package controllers;

import java.util.List;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;
import models.*;

/**
 * Manage a database of diagnoses
 */
public class Application extends Controller {
    
    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
        routes.CasusApplication.listCasus(0, "casus_omschrijving", "asc", "")
    );
    
    /**
     * Handle default path requests, redirect to diagnoses list
     */
    @Security.Authenticated(Secured.class)
    public static Result index() {
        return GO_HOME;
    }
<<<<<<< HEAD
    
    public static Result login() {
        return ok(
            login.render(form(Login.class))
        );
    }
    
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
            routes.Application.login()
        );
    }
    
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("username", loginForm.get().username);
            return redirect(
                routes.Application.index()
            );
        }
    }

    /**
     * JSON test
     */
    public static Result getGezondheidspatroon() {
        java.util.List<models.Gezondheidspatroon> tasks = new play.db.ebean.Model.Finder(String.class, models.Gezondheidspatroon.class).all();
        return ok(play.libs.Json.toJson(tasks));
    }
=======
>>>>>>> 3e89c30aab80e6d4a82eaac0ed400959f9c17f49
    
    /**
     * Get nic_diagnose from diagnose_id in JSON format
     * @param id
     * @return
     */
    public static Result getNicDiagnose(Long id) {
    	List<Nic_Diagnose> nic = Nic_Diagnose
    			.find
    			.where()
    			.like("diagnose", id.toString())
                .findList();
        return ok(play.libs.Json.toJson(nic));
    }
    
    /**
     * Get bepalendkenmerk_diagnose from diagnose_id in JSON format
     * @param id
     * @return
     */
<<<<<<< HEAD
    @Security.Authenticated(Secured.class)
    public static Result getNicDiagnose(Long id) {
    	List<Nic_Diagnose> nic = Nic_Diagnose
=======
    public static Result getBepalendKenmerk(Long id){
    	List<Bepalendkenmerk_Diagnose> bepalendkenmerk = Bepalendkenmerk_Diagnose
    			.find
    			.where().
                ilike("diagnose_id", id.toString())
                .findList();
    	return ok(play.libs.Json.toJson(bepalendkenmerk));
    }
    
    /**
     * Get risicofactor_diagnose from diagnose_id in JSON format
     * @param id
     * @return
     */
    public static Result getRisicoFactor(Long id){
    	List<Risicofactor_Diagnose> risicofactor = Risicofactor_Diagnose
    			.find
    			.where().
                ilike("diagnose_id", id.toString())
                .findList();
    	return ok(play.libs.Json.toJson(risicofactor));
    }
    
    
    /**
     * Get samenhangendefactor_diagnose from diagnose_id in JSON format
     * @param id
     * @return
     */
    public static Result getSamenhangendeFactor(Long id){
    	List<Samenhangendefactor_Diagnose> samenhangendefactor = Samenhangendefactor_Diagnose
>>>>>>> 3e89c30aab80e6d4a82eaac0ed400959f9c17f49
    			.find
    			.where().
                ilike("diagnose_id", id.toString())
                .findList();
    	return ok(play.libs.Json.toJson(samenhangendefactor));
    }
    
    /**
     * Get noc_indicator_diagnose from diagnose_id in JSON format
     * @param id
     * @return
     */
    public static Result getNocDiagnose(Long id) {
    	// Get NOC/Indicator attached to diagnose
    	List<Noc_Indicator_Diagnose> noc = Noc_Indicator_Diagnose
    			.find
    			.fetch("noc")
    			.fetch("noc.nocoverzicht")
    			.where()
    			.like("diagnose", id.toString())
                .findList();
        return ok(play.libs.Json.toJson(noc));
    }
    
    public static Result getDiagnoseOverzicht(Long id) {
    	// Get NOC/Indicator attached to diagnose
    	List<Diagnoseoverzicht> diagnoseoverzicht = Diagnoseoverzicht
    			.find
    			.where()
			    .ilike("diagnose_id", id.toString())
			    .findList();
        return ok(play.libs.Json.toJson(diagnoseoverzicht));
    }
    
    /**
     * Display the paginated list of diagnoses.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on diagnose names
     */
    @Security.Authenticated(Secured.class)
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Diagnoseoverzicht.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Diagnose.
     *
     * @param id Id of the diagnose to edit
     */
    @Security.Authenticated(Secured.class)
    public static Result edit(Long id) {
    	// Get Diagnoseoverzicht values
        /*Form<Diagnoseoverzicht> diagnoseForm = form(Diagnoseoverzicht.class).fill(
        		Diagnoseoverzicht.find.where()
			    .ilike("diagnose_id", id.toString())
			    .findList()
			    .get(0)
        );*/
    	
        return ok(
            editForm.render(id)
        );
<<<<<<< HEAD
    }
    
    @Security.Authenticated(Secured.class)
    public static Result getBepalendeKenmerken(Long id){
    	List<Bepalendkenmerk_Diagnose> d = Bepalendkenmerk_Diagnose
    			.find
    			.where().
                ilike("diagnose_id", id.toString())
                .findList();
        return ok(
        		bepalendkenmerkdiagnose.render(d)
            );
    }
    
    
    /**
     * Get Bepalend kenmerk pagina
     * @param page
     * @param sortBy
     * @param order
     * @param filter
     * @return
     */
    @Security.Authenticated(Secured.class)
    public static Result getBepalendkenmerk(int page, String sortBy, String order, String filter) {
        return ok(
        		bepalendkenmerk.render(
                    Bepalendkenmerk_Diagnose.page(page, 100, sortBy, order, filter),
                    sortBy, order, filter
                )
            );
    }
    
    /**
     * Get Risicofactor pagina
     * @param page
     * @param sortBy
     * @param order
     * @param filter
     * @return
     */
    @Security.Authenticated(Secured.class)
    public static Result getRisicofactor(int page, String sortBy, String order, String filter) {
        return ok(
        		risicofactor.render(
    				Risicofactor_Diagnose.page(page, 100, sortBy, order, filter),
                    sortBy, order, filter
                )
            );
    }
    
    /**
     * Get Samenhangendefactor pagina
     * @param page
     * @param sortBy
     * @param order
     * @param filter
     * @return
     */
    @Security.Authenticated(Secured.class)
    public static Result getSamenhangendefactor(int page, String sortBy, String order, String filter) {
        return ok(
        		samenhangendefactor.render(
    				Samenhangendefactor_Diagnose.page(page, 100, sortBy, order, filter),
                    sortBy, order, filter
                )
            );
    }
    
    /**
     * Get NIC pagina
     * @param page
     * @param sortBy
     * @param order
     * @param filter
     * @return
     */
    @Security.Authenticated(Secured.class)
    public static Result getNicActiviteit(int page, String sortBy, String order, String filter) {
        return ok(
        		nic_diagnose.render(
    				Nic_Diagnose.page(page, 100, sortBy, order, filter),
                    sortBy, order, filter
                )
            );
    }
=======
    } 
>>>>>>> 3e89c30aab80e6d4a82eaac0ed400959f9c17f49
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the diagnose to edit
     */
    @Security.Authenticated(Secured.class)
    public static Result update(Long diagnose_id) {
        //Form<Diagnoseoverzicht> diagnoseForm = form(Diagnoseoverzicht.class).bindFromRequest();
        /*if(diagnoseForm.hasErrors()) {	
            return badRequest(editForm.render(diagnose_id, diagnoseForm));
        }*/
        //diagnoseForm.get().update(diagnose_id);
        //flash("success", "Diagnose " + diagnoseForm.get().name + " has been updated");
        return GO_HOME;
    }
    
    /**
     * Display the 'new diagnose form'.
     */
    @Security.Authenticated(Secured.class)
    public static Result create() {
        Form<Diagnoseoverzicht> diagnoseForm = form(Diagnoseoverzicht.class);
        return ok(
            createForm.render(diagnoseForm)
        );
    }
    
    /**
     * Handle the 'new diagnose form' submission 
     */
    @Security.Authenticated(Secured.class)
    public static Result save() {
        /*Form<Diagnoseoverzicht> diagnoseForm = form(Diagnoseoverzicht.class).bindFromRequest();
        DynamicForm domein = Form.form().bindFromRequest();
        
        if(diagnoseForm.hasErrors()) {
            return badRequest(createForm.render(diagnoseForm));
        }
        
        Diagnose diagnose = new Diagnose();
        diagnose.save();
        Diagnosedomein diagnosedomein = new Diagnosedomein();
        diagnosedomein.diagnosedomein_code = Long.parseLong(domein.get("diagnosedomein.diagnosedomein_domein"));
        Diagnoseversie diagnoseversie = createDiagnoseVersie(diagnoseForm.get().diagnoseoverzicht_omschrijving);
        diagnoseForm.get().diagnoseklasse = getDiagnoseKlasse(diagnoseForm.get().diagnoseklasse, diagnosedomein);
        diagnoseForm.get().diagnose = diagnose;
        diagnoseForm.get().diagnoseversie = diagnoseversie;
        diagnoseForm.get().save();
        flash("success", "Diagnose " + diagnoseForm.get().diagnose + " has been created");*/
        return GO_HOME;
    }
    
    /**
     * Handle diagnose deletion
     */
    @Security.Authenticated(Secured.class)
    public static Result delete(Long id) {
        /*Diagnose.find.ref(id).delete();
        flash("success", "Diagnose has been deleted");*/
        return GO_HOME;
    }
    
    public static class Login {

        public String username;
        public String password;
        
        public String validate() {
            if (Gebruiker.authenticate(username, password) == null) {
              return "Invalid user or password";
            }
            return null;
        }

    }
}
            
