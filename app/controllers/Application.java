package controllers;

import java.util.List;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;
import models.*;
import models.nanda.Bepalendkenmerk_Diagnose;
import models.nanda.Diagnoseoverzicht;
import models.nanda.Risicofactor_Diagnose;
import models.nanda.Samenhangendefactor_Diagnose;
import models.nic.Nic_Diagnose;
import models.noc.Noc_Indicator_Diagnose;

/**
 * Functionality for diagnoses and general
 * @author Vincent van Deemter
 */
public class Application extends Controller {
    
    /**
     *This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
        routes.VerpleegkundigeApplication.listCasusVerpleegkundige(0, "casus_omschrijving", "asc", "")
    );
    
    /**
     * Handle default path requests, redirect to diagnoses list
     */
    @Security.Authenticated(Secured.class)
    public static Result index() {
        return GO_HOME;
    }
    
    /**
     * Get nic_diagnose from diagnose_id in JSON format
     * @param id
     * @return
     */
    @Security.Authenticated(Secured.class)
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
    @Security.Authenticated(Secured.class)
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
    @Security.Authenticated(Secured.class)
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
    @Security.Authenticated(Secured.class)
    public static Result getSamenhangendeFactor(Long id){
    	List<Samenhangendefactor_Diagnose> samenhangendefactor = Samenhangendefactor_Diagnose
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
    @Security.Authenticated(Secured.class)
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
    
    @Security.Authenticated(Secured.class)
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
        return ok(
            editForm.render(id)
        );
    } 
}
            
