package controllers;

import static play.data.Form.form;

import java.util.List;

import com.avaje.ebean.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.Casus;
import models.Diagnoseoverzicht;
import models.Samenhangendefactor_Diagnose;
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
    	// Get NOC/Indicator attached to diagnose
    	List<Casus> casus = Casus
    			.find
    			.where()
			    .ilike("casus_id", id.toString())
			    .findList();
        return ok(play.libs.Json.toJson(casus));
    }
}
