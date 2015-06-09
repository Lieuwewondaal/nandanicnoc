package controllers;

import java.util.Calendar;
import java.util.List;

import javax.persistence.PersistenceException;

import com.avaje.ebean.FetchConfig;
import com.avaje.ebean.Page;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.Casus;
import models.Casus_Diagnose;
import models.Casus_Nic;
import models.Casus_Noc;
import models.Casusopmerkingen;
import models.Diagnose;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;

public class VerpleegkundigeApplication extends Controller  {
    /**
     * Display the paginated list of cases
     * @param page
     * @param sortBy
     * @param order
     * @param filter
     * @return
     */
	@Security.Authenticated(Secured.class)
    public static Result listCasusVerpleegkundige(int page, String sortBy, String order, String filter) {
        return ok(
        		verpleegkundigeCasuslist.render(
                    Casus.page(page, 10, sortBy, order, filter),
                    sortBy, order, filter
            )
        );
    }
    
    /**
     * Get samenhangendefactor_diagnose from diagnose_id in JSON format
     * @param id
     * @return
     */
	@Security.Authenticated(Secured.class)
    public static Result getListCasusVerpleegkundigeJSON(int page, int pageSize, String sortBy, String order, String filter){
    	Page<Casus> casus = Casus
    			.find.where()
            	.or(
            			com.avaje.ebean.Expr.like("casus_definitie", "%" + filter + "%"), 
            			com.avaje.ebean.Expr.like("casus_omschrijving", "%" + filter + "%")
        			)
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(true)
                .getPage(page);
    	
    	ObjectNode result = Json.newObject();
    	result.put("casus", Json.toJson(casus.getList()));
    	result.put("pages", casus.getTotalPageCount());
    	result.put("rows", casus.getTotalRowCount());
    	result.put("index", casus.getPageIndex());
    	result.put("XtoYofZ", casus.getDisplayXtoYofZ(";", ";"));
    	result.put("hasNext", casus.hasNext());
    	result.put("hasPrev", casus.hasPrev());
    	return ok(result);
    }
    
	/**
     * Display the casus.
     */
	@Security.Authenticated(Secured.class)
    public static Result editCasusVerpleegkundige(Long id) {
        return ok(
    		verpleegkundigeCasus.render(id)
        );
    }

	/**
     * Display the casus 2.
     */
	@Security.Authenticated(Secured.class)
    public static Result editCasusVerpleegkundige2(Long id) {
        return ok(
    		verpleegkundigeCasus2.render(id)
        );
    }
    
    /**
     * 
     * @param id
     * @return
     */
	@Security.Authenticated(Secured.class)
    public static Result getCasusOverzichtJSON(Long id) {

    	List<Casus> casus = Casus
    			.find
    			.where()
			    .ilike("casus_id", id.toString())
			    .findList();
        return ok(play.libs.Json.toJson(casus));
    }
	
	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @param sortBy
	 * @param order
	 * @param filter
	 * @return
	 */
    @Security.Authenticated(Secured.class)
    public static Result getNandaNicNoc(int page, int pageSize, String sortBy, String order, String filter) {

    	Page<Diagnose> nanda = Diagnose
    			.find
    			.fetch("nic_diagnose", new FetchConfig().query())
    			.fetch("noc_indicator_diagnose", new FetchConfig().query())
    			.fetch("diagnoseoverzicht", new FetchConfig().query())
    			.where()
    			/*.or(
    					com.avaje.ebean.Expr.or(
    		        			com.avaje.ebean.Expr.like("diagnoseoverzicht.diagnoseoverzicht_omschrijving", "%" + "pijn" + "%"), 
    		        			com.avaje.ebean.Expr.like("diagnoseoverzicht.diagnoseoverzicht_definitie", "%" + "pijn" + "%")
						),
						com.avaje.ebean.Expr.or(
		            			com.avaje.ebean.Expr.like("nic_diagnose.nic.nicoverzicht.nicoverzicht_omschrijving", "%" + "pijn" + "%"),
		            			com.avaje.ebean.Expr.like("nic_diagnose.nic.nicoverzicht.nicoverzicht_definitie", "%" + "pijn" + "%")	
						)
    			)*/
            	.like("diagnose_id", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(true)
                .getPage(page);
    	
    	ObjectNode result = Json.newObject();
    	result.put("nanda", Json.toJson(nanda.getList()));
    	//result.put("pages", nanda.getTotalPageCount());
    	result.put("rows", nanda.getTotalRowCount());
    	result.put("index", nanda.getPageIndex());
    	result.put("XtoYofZ", nanda.getDisplayXtoYofZ(";", ";"));
    	result.put("hasNext", nanda.hasNext());
    	result.put("hasPrev", nanda.hasPrev());
    	return ok(result);
    }
    
	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @param sortBy
	 * @param order
	 * @param filter
	 * @return
	 */
    @Security.Authenticated(Secured.class)
    public static Result getNanda(int page, int pageSize, String sortBy, String order, String filter) {

    	Page<Diagnose> nanda = Diagnose
    			.find
    			.fetch("diagnoseoverzicht")
    			.where()
    			.or(
        			com.avaje.ebean.Expr.like("diagnoseoverzicht.diagnoseoverzicht_omschrijving", "%" + filter + "%"), 
        			com.avaje.ebean.Expr.like("diagnoseoverzicht.diagnoseoverzicht_definitie", "%" + filter + "%")
    			)
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(true)
                .getPage(page);
    	
    	ObjectNode result = Json.newObject();
    	result.put("nanda", Json.toJson(nanda.getList()));
    	//result.put("pages", nanda.getTotalPageCount());
    	result.put("rows", nanda.getTotalRowCount());
    	result.put("index", nanda.getPageIndex());
    	result.put("XtoYofZ", nanda.getDisplayXtoYofZ(";", ";"));
    	result.put("hasNext", nanda.hasNext());
    	result.put("hasPrev", nanda.hasPrev());
    	return ok(result);
    }
    
	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @param sortBy
	 * @param order
	 * @param filter
	 * @return
	 */
    @Security.Authenticated(Secured.class)
    public static Result getNic(int page, int pageSize, String sortBy, String order, String filter) {

    	Page<Diagnose> nanda = Diagnose
    			.find
    			.fetch("diagnoseoverzicht")
    			.where()
    			.or(
        			com.avaje.ebean.Expr.like("diagnoseoverzicht.diagnoseoverzicht_omschrijving", "%" + filter + "%"), 
        			com.avaje.ebean.Expr.like("diagnoseoverzicht.diagnoseoverzicht_definitie", "%" + filter + "%")
    			)
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(true)
                .getPage(page);
    	
    	ObjectNode result = Json.newObject();
    	result.put("nanda", Json.toJson(nanda.getList()));
    	//result.put("pages", nanda.getTotalPageCount());
    	result.put("rows", nanda.getTotalRowCount());
    	result.put("index", nanda.getPageIndex());
    	result.put("XtoYofZ", nanda.getDisplayXtoYofZ(";", ";"));
    	result.put("hasNext", nanda.hasNext());
    	result.put("hasPrev", nanda.hasPrev());
    	return ok(result);
    }
    
	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @param sortBy
	 * @param order
	 * @param filter
	 * @return
	 */
    @Security.Authenticated(Secured.class)
    public static Result getNoc(int page, int pageSize, String sortBy, String order, String filter) {

    	Page<Diagnose> nanda = Diagnose
    			.find
    			.fetch("diagnoseoverzicht")
    			.where()
    			.or(
        			com.avaje.ebean.Expr.like("diagnoseoverzicht.diagnoseoverzicht_omschrijving", "%" + filter + "%"), 
        			com.avaje.ebean.Expr.like("diagnoseoverzicht.diagnoseoverzicht_definitie", "%" + filter + "%")
    			)
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(true)
                .getPage(page);
    	
    	ObjectNode result = Json.newObject();
    	result.put("nanda", Json.toJson(nanda.getList()));
    	//result.put("pages", nanda.getTotalPageCount());
    	result.put("rows", nanda.getTotalRowCount());
    	result.put("index", nanda.getPageIndex());
    	result.put("XtoYofZ", nanda.getDisplayXtoYofZ(";", ";"));
    	result.put("hasNext", nanda.hasNext());
    	result.put("hasPrev", nanda.hasPrev());
    	return ok(result);
    }
    
    @Security.Authenticated(Secured.class)
    public static Result getCasusDiagnose(Long id) {

    	List<Casus_Diagnose>casus_diagnose = Casus_Diagnose
			.find
			.fetch("diagnose.diagnoseoverzicht", "diagnoseoverzicht_omschrijving, diagnoseoverzicht_definitie")
			.where()
		    .ilike("casus_id", id.toString())
		    .ilike("user_id", session("userid"))
		    .isNotNull("diagnose.diagnoseoverzicht.diagnoseoverzicht_omschrijving")
		    .findList();
    	return ok(play.libs.Json.toJson(casus_diagnose));
    }
    
    @Security.Authenticated(Secured.class)
    public static Result getCasusNic(Long id) {

    	List<Casus_Nic>casus_nic = Casus_Nic
			.find
			.fetch("nic.nicoverzicht", "nicoverzicht_omschrijving, nicoverzicht_definitie")
			.fetch("nicactiviteit")
			.where()
		    .ilike("casus_diagnose.casus.casus_id", id.toString())
		    .ilike("casus_diagnose.user_id", session("userid"))
		    .findList();
    	return ok(play.libs.Json.toJson(casus_nic));
    }
    
    @Security.Authenticated(Secured.class)
    public static Result getCasusNoc(Long id) {
    	
    	List<Casus_Noc>casus_noc = Casus_Noc
			.find
			.fetch("noc.nocoverzicht", "nocoverzicht_omschrijving, nocoverzicht_definitie")
			.fetch("indicator")
			.where()
		    .ilike("casus_diagnose.casus.casus_id", id.toString())
		    .ilike("casus_diagnose.user_id", session("userid"))
		    .findList();
    	return ok(play.libs.Json.toJson(casus_noc));
    }
    
    @Security.Authenticated(Secured.class)
    public static Result getCasusOpmerkingen(Long id) {

    	List<Casusopmerkingen>casusopmerkingen = Casusopmerkingen
			.find
			.where()
		    .ilike("casus_diagnose.casus.casus_id", id.toString())
		    .ilike("casus_diagnose.user_id", session("userid"))
		    .findList();
    	return ok(play.libs.Json.toJson(casusopmerkingen));
    }

    @Security.Authenticated(Secured.class)
    public static Result saveCasusOpmerking(Long id) {
    	DynamicForm requestData = Form.form().bindFromRequest();
    	Casus_Diagnose casus_diagnose;
    	try{
	    	casus_diagnose = Casus_Diagnose
	    			.find
	    			.where()
	    			.ilike("casus.casus_id", id.toString())
	    			.ilike("user_id", session("userid"))
	    			.findList()
	    			.get(0);
    	}
    	catch(PersistenceException e){
    		casus_diagnose = createEmptyCasusDiagnose(id);
    	}
    	
    	Casusopmerkingen casusopmerkingen = new Casusopmerkingen();
    	casusopmerkingen.casus_diagnose = casus_diagnose;
    	casusopmerkingen.casusopmerking = requestData.get("opmerking");
		casusopmerkingen.casusopmerkingdatum = Calendar.getInstance().getTime();
		casusopmerkingen.save();

    	return ok();
    }
    
    @Security.Authenticated(Secured.class)
    public static Result deleteCasusOpmerking(Long id) {

    	Casusopmerkingen casusopmerking = Casusopmerkingen
			.find
			.where()
		    .ilike("casusopmerkingen_id", id.toString())
		    .ilike("casus_diagnose.user_id", session("userid"))
		    .findUnique();
    	casusopmerking.delete();
    	return ok();
    }
    
    @Security.Authenticated(Secured.class)
    public static Result deleteCasusDiagnose(Long id) {
    	Casus_Diagnose casus_diagnose, alternateCasus_diagnose;
    	try{
    	casus_diagnose = Casus_Diagnose
			.find
			.where()
		    .ilike("casus_diagnose_id", id.toString())
		    .ilike("user_id", session("userid"))
		    .findUnique();
    	}
    	catch(PersistenceException e){
    		return ok();
    	}
    	
    	// Check if alternate diagnose exists
    	try{
        	alternateCasus_diagnose = Casus_Diagnose
				.find
				.where()
			    .not(com.avaje.ebean.Expr.like("casus_diagnose_id", id.toString()))
			    .ilike("user_id", session("userid"))
			    .findList()
			    .get(0);
        	
        	// Update other values connected to this diagnose
        	try{
        		List<Casus_Nic> casus_nic = Casus_Nic
        				.find
        				.where()
        				.ilike("casus_diagnose.casus_diagnose_id", id.toString())
        				.findList();
        		for (int i = 0; i < casus_nic.size(); i++) {
        			casus_nic.get(i).casus_diagnose = alternateCasus_diagnose;
        			casus_nic.get(i).save();
        		}
        	}
        	catch(PersistenceException e){

        	}
        	
        	try{
        		List<Casus_Noc> casus_noc = Casus_Noc
        				.find
        				.where()
        				.ilike("casus_diagnose.casus_diagnose_id", id.toString())
        				.findList();
        		for (int i = 0; i < casus_noc.size(); i++) {
        			casus_noc.get(i).casus_diagnose = alternateCasus_diagnose;
        			casus_noc.get(i).save();
        		}
        	}
        	catch(PersistenceException e){
        		
        	}
        	
        	try{
        		List<Casusopmerkingen> casusopmerkingen = Casusopmerkingen
        				.find
        				.where()
        				.ilike("casus_diagnose.casus_diagnose_id", id.toString())
        				.findList();
        		for (int i = 0; i < casusopmerkingen.size(); i++) {
        			casusopmerkingen.get(i).casus_diagnose = alternateCasus_diagnose;
        			casusopmerkingen.get(i).save();
        		}
        	}
        	catch(PersistenceException e){
        		
        	}
        	casus_diagnose.delete();
    	}
    	
    	// Update current one to null if no other diagnoses exist
    	catch(IndexOutOfBoundsException e){
    		casus_diagnose.diagnose = null;
    		casus_diagnose.update();
    	}
    	return ok();
    }
    
    @Security.Authenticated(Secured.class)
    public static Result deleteCasusNic(Long id) {

    	Casus_Nic casus_nic = Casus_Nic
			.find
			.where()
		    .ilike("casus_nic_id", id.toString())
		    .ilike("casus_diagnose.user_id", session("userid"))
		    .findUnique();
    	casus_nic.delete();
    	return ok();
    }
    
    @Security.Authenticated(Secured.class)
    public static Result deleteCasusNoc(Long id) {

    	Casus_Noc casus_noc = Casus_Noc
			.find
			.where()
		    .ilike("casus_noc_id", id.toString())
		    .ilike("casus_diagnose.user_id", session("userid"))
		    .findUnique();
    	casus_noc.delete();
    	return ok();
    }
    
    public static Casus_Diagnose createEmptyCasusDiagnose(Long id){
		Casus_Diagnose casus_diagnose = new Casus_Diagnose();
		Casus casus = Casus
				.find
				.byId(id);
		casus_diagnose.casus = casus;
		casus_diagnose.user_id = Integer.parseInt(session("userid"));
		casus_diagnose.save();
    	return casus_diagnose;
    }
}
