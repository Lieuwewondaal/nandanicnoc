package controllers;

import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.PersistenceException;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.FetchConfig;
import com.avaje.ebean.Page;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.Casus;
import models.Casus_Diagnose;
import models.Casus_Nic;
import models.Casus_Noc;
import models.Casusopmerkingen;
import models.Diagnose;
import models.Diagnoseoverzicht;
import models.Indicator;
import models.Nic;
import models.Nic_Nicactiviteit;
import models.Nicactiviteit;
import models.Noc;
import models.Noc_Indicator;
import models.Nocoverzicht;
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
    
    /**
     * 
     * @param id
     * @return
     */
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
    
    /**
     * 
     * @param id
     * @return
     */
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
    
    /**
     * 
     * @param id
     * @return
     */
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
    
    /**
     * 
     * @param id
     * @return
     */
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
    
    /**
     * 
     * @param id
     * @return
     */
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
    
    /**
     * 
     * @param id
     * @return
     */
    @Security.Authenticated(Secured.class)
    public static Result deleteCasusDiagnose(Long id) {
    	Casus_Diagnose casus_diagnose, alternateCasus_diagnose;
    	List<Casus_Diagnose> cd = Casus_Diagnose
    			.find
    			.where()
    			.ilike("casus_diagnose_id", id.toString())
		    	.ilike("user_id", session("userid"))
    		    .setMaxRows(1)
    		    .findList();
    	if(cd.size() > 0){
    		casus_diagnose = cd.get(0);
    	}
    	else{
    		return ok();
    	}
    	
    	// Check if alternate diagnose exists
 
		List<Casus_Diagnose> acd = Casus_Diagnose
    			.find
    			.where()
    			.not(com.avaje.ebean.Expr.like("casus_diagnose_id", id.toString()))
		    	.ilike("user_id", session("userid"))
    		    .setMaxRows(1)
    		    .findList();
		if(acd.size() > 0){
			alternateCasus_diagnose = acd.get(0);
    		
        	
        	// Update other values connected to this diagnose
        	try{
        		List<Casus_Nic> casus_nic = Casus_Nic
        				.find
        				.where()
        				.ilike("casus_diagnose.casus_diagnose_id", id.toString())
        				.findList();
        		for (int i = 0; i < casus_nic.size(); i++) {
        			casus_nic.get(i).casus_diagnose = alternateCasus_diagnose;
        			casus_nic.get(i).update();
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
        			casus_noc.get(i).update();
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
        			casusopmerkingen.get(i).update();
        		}
        	}
        	catch(PersistenceException e){
        		
        	}
        	casus_diagnose.delete();
    	}
    	
    	// Update current one to null if no other diagnoses exist
		else{
    		casus_diagnose.diagnose = null;
    		casus_diagnose.update();
    	}
    	return ok();
    }
    
    /**
     * 
     * @param id
     * @return
     */
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
    
    /**
     * 
     * @param id
     * @return
     */
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
    
    /**
     * 
     * @param id
     * @return
     */
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
    
    /**
     * Get samenhangendefactor_diagnose from diagnose_id in JSON format
     * @param id
     * @return
     */
	@Security.Authenticated(Secured.class)
    public static Result getDiagnoseSearchListCasusVerpleegkundigeJSON(int page, int pageSize, String sortBy, String order, String filter){
		String sql = "select distinct t0.diagnose_code c0, t0.diagnoseoverzicht_omschrijving c1, t0.diagnoseoverzicht_definitie c2, t0.diagnoseversie_id c3, t0.gezondheidspatroon_id c4, t0.diagnoseklasse_id c5, t1.diagnose_id c6,	t2.gezondheidspatroon_omschrijving from diagnoseoverzicht t0 left outer join diagnose t1 on t1.diagnose_id = t0.diagnose_id left outer join gezondheidspatroon t2 on t2.gezondheidspatroon_id = t0.gezondheidspatroon_id";
		sql += tokenizeQuery(filter, 0);
		
		RawSql rawSql =   
			    RawSqlBuilder  
			        .parse(sql)  
			        .columnMapping("t0.diagnose_code",  "diagnose_code")  
			        .columnMapping("t0.diagnoseoverzicht_omschrijving",  "diagnoseoverzicht_omschrijving")  
			        .columnMapping("t0.diagnoseoverzicht_definitie",  "diagnoseoverzicht_definitie")  
			        .columnMapping("t0.diagnoseversie_id",  "diagnoseversie.diagnoseversie_id")  
			        .columnMapping("t0.gezondheidspatroon_id", "gezondheidspatroon.gezondheidspatroon_id")
			        .columnMapping("t0.diagnoseklasse_id", "diagnoseklasse.diagnoseklasse_id")
			        .columnMapping("t1.diagnose_id", "diagnose.diagnose_id")
			        .columnMapping("t2.gezondheidspatroon_omschrijving", "gezondheidspatroon.gezondheidspatroon_omschrijving")
			        .create();  
		
		Page<Diagnoseoverzicht> diagnose = Diagnoseoverzicht
    			.find
    			.fetch("diagnose")
    			.setRawSql(rawSql)
    			.where()
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
		
		ObjectNode result = Json.newObject();
    	result.put("diagnose", Json.toJson(diagnose.getList()));
    	if(diagnose.getList().size() > 0){
	    	result.put("pages", diagnose.getTotalPageCount());
	    	result.put("rows", diagnose.getTotalRowCount());
	    	result.put("index", diagnose.getPageIndex());
	    	result.put("XtoYofZ", diagnose.getDisplayXtoYofZ(";", ";"));
	    	result.put("hasNext", diagnose.hasNext());
	    	result.put("hasPrev", diagnose.hasPrev());
    	}
    	
    	return ok(result);
    }
	
    /**
     * 
     * @param id
     * @return
     */
	@Security.Authenticated(Secured.class)
    public static Result getNicSearchListCasusVerpleegkundigeJSON(int page, int pageSize, String sortBy, String order, String filter){

		/*
		 * select distinct t0.nic_nicactiviteit_releasestatus_datum c0, t0.nic_nicactiviteit_releasestatus_omschrijving c1,		t1.nicactiviteit_id c2,        t1.nicactiviteit_omschrijving c3,		t2.nic_id c4		from nic_nicactiviteit t0 left outer join nic t2 on t2.nic_id = t0.nic_id join nic u1 on u1.nic_id = t0.nic_id join nicoverzicht u2 on u2.nic_id = u1.nic_id left outer join nicactiviteit t1 on t1.nicactiviteit_id = t0.nicactiviteit_id join nicactiviteit u3 on u3.nicactiviteit_id = t0.nicactiviteit_id where MATCH (u3.nicactiviteit_omschrijving) AGAINST ("verzorgen") OR MATCH (u2.nicoverzicht_definitie,u2.nicoverzicht_omschrijving) AGAINST ("verzorgen") * 
		 */  
		String sql = "select distinct t0.nic_nicactiviteit_releasestatus_datum c0, t0.nic_nicactiviteit_releasestatus_omschrijving c1, t1.nicactiviteit_id c2, t1.nicactiviteit_omschrijving c3, t2.nic_id c4, t3.nicoverzicht_omschrijving c5,	t3.nicoverzicht_definitie c6 from nic_nicactiviteit t0 left outer join nic t2 on t2.nic_id = t0.nic_id join nic u1 on u1.nic_id = t0.nic_id	left outer join	nicoverzicht t3 on t3.nic_id = t2.nic_id left outer join nicactiviteit t1 on t1.nicactiviteit_id = t0.nicactiviteit_id join nicactiviteit u3 on u3.nicactiviteit_id = t0.nicactiviteit_id";  
		sql += tokenizeQuery(filter, 1);
		
		RawSql rawSql =   
		    RawSqlBuilder  
		        .parse(sql)  
		        .columnMapping("t0.nic_nicactiviteit_releasestatus_datum",  "nic_nicactiviteit_releasestatus_datum")  
		        .columnMapping("t0.nic_nicactiviteit_releasestatus_omschrijving",  "nic_nicactiviteit_releasestatus_omschrijving")  
		        .columnMapping("t1.nicactiviteit_id",  "nicactiviteit.nicactiviteit_id")  
		        .columnMapping("t1.nicactiviteit_omschrijving",  "nicactiviteit.nicactiviteit_omschrijving")  
		        .columnMapping("t2.nic_id", "nic.nic_id")
		        .columnMapping("t3.nicoverzicht_definitie", "nic.nicoverzicht.nicoverzicht_definitie")
		        .columnMapping("t3.nicoverzicht_omschrijving", "nic.nicoverzicht.nicoverzicht_omschrijving")
		        .create();  
		  
		  
		Page<Nic_Nicactiviteit> nic_nicactiviteit = Nic_Nicactiviteit
			.find
		    // get ebean to fetch parts of the order and customer   
		    // after the raw SQL query is executed  
		    .fetch("nic")  
		    .fetch("nic.nicoverzicht")
		    .fetch("nicactiviteit")
			.orderBy(sortBy + " " + order)
			.setRawSql(rawSql)
			.where()
			.findPagingList(pageSize)
			.setFetchAhead(false)
			.getPage(page);  
		
		ObjectNode result = Json.newObject();
    	result.put("nic", Json.toJson(nic_nicactiviteit.getList()));
    	if(nic_nicactiviteit.getList().size() > 0){
	    	result.put("pages", nic_nicactiviteit.getTotalPageCount());
	    	result.put("rows", nic_nicactiviteit.getTotalRowCount());
	    	result.put("index", nic_nicactiviteit.getPageIndex());
	    	result.put("XtoYofZ", nic_nicactiviteit.getDisplayXtoYofZ(";", ";"));
	    	result.put("hasNext", nic_nicactiviteit.hasNext());
	    	result.put("hasPrev", nic_nicactiviteit.hasPrev());
		}
    	return ok(result);
    }
	
    /**
     * 
     * @param id
     * @return
     */
	@Security.Authenticated(Secured.class)
    public static Result getNocSearchListCasusVerpleegkundigeJSON(int page, int pageSize, String sortBy, String order, String filter){

		String sql = "select distinct t0.noc_indicator_releasestatus_datum c0, t0.noc_indicator_releasestatus_omschrijving c1, t1.indicator_id c2, t1.indicator_omschrijving c3, t2.noc_id c4, t3.nocoverzicht_omschrijving c5, t3.nocoverzicht_definitie c6 from noc_indicator t0 left outer join noc t2 on t2.noc_id = t0.noc_id join noc u1 on u1.noc_id = t0.noc_id left outer join nocoverzicht t3 on t3.noc_id = t2.noc_id left outer join indicator t1 on t1.indicator_id = t0.indicator_id join indicator u3 on u3.indicator_id = t0.indicator_id";
		sql += tokenizeQuery(filter, 2);
		
		RawSql rawSql =   
		    RawSqlBuilder  
		        .parse(sql)  
		        .columnMapping("t0.noc_indicator_releasestatus_datum",  "noc_indicator_releasestatus_datum")  
		        .columnMapping("t0.noc_indicator_releasestatus_omschrijving",  "noc_indicator_releasestatus_omschrijving")  
		        .columnMapping("t1.indicator_id",  "indicator.indicator_id")  
		        .columnMapping("t1.indicator_omschrijving",  "indicator.indicator_omschrijving")  
		        .columnMapping("t2.noc_id", "noc.noc_id")
		        .columnMapping("t3.nocoverzicht_omschrijving", "noc.nocoverzicht.nocoverzicht_omschrijving")
		        .columnMapping("t3.nocoverzicht_definitie", "noc.nocoverzicht.nocoverzicht_definitie")
		        .create();  
		
    	Page<Noc_Indicator> noc_indicator = Noc_Indicator
    			.find
    			.fetch("noc", new FetchConfig().query())
    			.fetch("noc.nocoverzicht")
    			.setRawSql(rawSql)
    			.where()
    			.orderBy(sortBy + " " + order)
    			.findPagingList(pageSize)
    			.setFetchAhead(false)
    			.getPage(page);
    	
    	ObjectNode result = Json.newObject();
    	result.put("noc", Json.toJson(noc_indicator.getList()));
    	if(noc_indicator.getList().size() > 0){
	    	result.put("pages", noc_indicator.getTotalPageCount());
	    	result.put("rows", noc_indicator.getTotalRowCount());
	    	result.put("index", noc_indicator.getPageIndex());
	    	result.put("XtoYofZ", noc_indicator.getDisplayXtoYofZ(";", ";"));
	    	result.put("hasNext", noc_indicator.hasNext());
	    	result.put("hasPrev", noc_indicator.hasPrev());
    	}
    	
    	return ok(result);
    }
	
    /**
     * 
     * @param id
     * @return
     */
    @Security.Authenticated(Secured.class)
    public static Result saveCasusDiagnose(Long casus_id, Long diagnose_id) {
    	Diagnose diagnose = Diagnose
    			.find
    			.where()
    			.eq("diagnose_id", diagnose_id)
    			.findUnique();
    	
    	Casus_Diagnose casus_diagnose;
    	
    	List<Casus_Diagnose> cd = Casus_Diagnose
    			.find
    			.where()
    		    .ilike("user_id", session("userid"))
    		    .ilike("casus_id", casus_id.toString())
    		    .isNull("diagnose_id")
    		    .setMaxRows(1)
    		    .findList();
    	
    	if(cd.size() > 0){
    		casus_diagnose = cd.get(0);
    	}
    	else{
    		casus_diagnose = createEmptyCasusDiagnose(casus_id);	
    	}
    	
		casus_diagnose.diagnose = diagnose;
		casus_diagnose.update();   	
   	
    	return ok();
    }
	
    /**
     * 
     * @param id
     * @return
     */
    @Security.Authenticated(Secured.class)
    public static Result saveCasusNic(Long casus_id, Long nic_id, Long activiteit_id) {

    	Casus_Diagnose casus_diagnose;
    	List<Casus_Diagnose> cd = Casus_Diagnose
    			.find
    			.where()
    			.ilike("user_id", session("userid"))
		    	.ilike("casus_id", casus_id.toString())
    		    .setMaxRows(1)
    		    .findList();
    	if(cd.size() > 0){
    		casus_diagnose = cd.get(0);
    	}
    	else{
    		casus_diagnose = createEmptyCasusDiagnose(casus_id);
    	}
    	
    	Nic noc = Nic
    			.find
    			.where()
    			.ilike("nic_id", nic_id.toString())
    			.findUnique();
    	
    	Nicactiviteit nicactiviteit = Nicactiviteit
    			.find
    			.where()
    			.ilike("nicactiviteit_id", activiteit_id.toString())
    			.findUnique();
    	Casus_Nic casus_nic = new Casus_Nic();
    	casus_nic.casus_diagnose = casus_diagnose;
    	casus_nic.nic = noc;
    	casus_nic.nicactiviteit = nicactiviteit;
    	casus_nic.save();
    	
    	return ok();
    }
	
    /**
     * 
     * @param id
     * @return
     */
    @Security.Authenticated(Secured.class)
    public static Result saveCasusNoc(Long casus_id, Long noc_id, Long indicator_id) {

    	Casus_Diagnose casus_diagnose;
    	List<Casus_Diagnose> cd = Casus_Diagnose
    			.find
    			.where()
    			.ilike("user_id", session("userid"))
		    	.ilike("casus_id", casus_id.toString())
    		    .setMaxRows(1)
    		    .findList();
    	if(cd.size() > 0){
    		casus_diagnose = cd.get(0);
    	}
    	else{
    		casus_diagnose = createEmptyCasusDiagnose(casus_id);
    	}
    	
    	Noc noc = Noc
    			.find
    			.where()
    			.ilike("noc_id", noc_id.toString())
    			.findUnique();
    	
    	Indicator indicator = Indicator
    			.find
    			.where()
    			.ilike("indicator_id", indicator_id.toString())
    			.findUnique();
    	Casus_Noc casus_noc = new Casus_Noc();
    	casus_noc.casus_diagnose = casus_diagnose;
    	casus_noc.noc = noc;
    	casus_noc.indicator = indicator;
    	casus_noc.save();
    	
    	return ok();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    @Security.Authenticated(Secured.class)
    public static Result saveCasusOpmerking(Long id) {
    	DynamicForm requestData = Form.form().bindFromRequest();
    	Casus_Diagnose casus_diagnose;

    	List<Casus_Diagnose> cd = Casus_Diagnose
    			.find
    			.where()
    			.ilike("casus.casus_id", id.toString())
    			.ilike("user_id", session("userid"))
    			.setMaxRows(1)
    			.findList();
    	
    	if(cd.size() > 0){
    		casus_diagnose = cd.get(0);
    	}
    	else{
    		casus_diagnose = createEmptyCasusDiagnose(id);	
    	}
    	
    	Casusopmerkingen casusopmerkingen = new Casusopmerkingen();
    	casusopmerkingen.casus_diagnose = casus_diagnose;
    	casusopmerkingen.casusopmerking = requestData.get("opmerking");
		casusopmerkingen.casusopmerkingdatum = Calendar.getInstance().getTime();
		casusopmerkingen.save();

    	return ok();
    }
    
    public static String tokenizeQuery(String filter, int type){
    	String sql = "";
		StringTokenizer st = new StringTokenizer(filter);
		// AndOr is used to 
		boolean AndOr = false;
		String element = "";
		int i = 0;
		while (st.hasMoreElements()) {
			element = st.nextElement().toString();
			if(i == 0){
				if(!element.equals("AND") && !element.equals("OR"))
				{
					sql += " where ";
					i++;
				}
			}
			
			switch(element){
				case "OR":
					if(AndOr){
						sql += " OR ";
						AndOr = false;
					}
					break;
				case "AND":
					if(AndOr){
						sql += " AND ";
						AndOr = false;
					}
					break;
				default:
					if(AndOr){
						sql += " AND ";
						AndOr = false;
					}
					
					// Diagnose
					if(type == 0){
						sql += "(MATCH (t0.diagnoseoverzicht_omschrijving,t0.diagnoseoverzicht_definitie) AGAINST ('"+element+"') OR MATCH (t2.gezondheidspatroon_omschrijving) AGAINST ('"+element+"'))";
					}
					
					// NIC
					if(type == 1){
						sql += "(MATCH (u3.nicactiviteit_omschrijving) AGAINST ('"+element+"') OR MATCH (t3.nicoverzicht_definitie,t3.nicoverzicht_omschrijving) AGAINST ('"+element+"'))";
					}
					
					// NOC
					if(type == 2){
						sql += "(MATCH (u3.indicator_omschrijving) AGAINST ('"+element+"') OR MATCH (t3.nocoverzicht_definitie,t3.nocoverzicht_omschrijving) AGAINST ('"+element+"'))";
					}
					AndOr = true;
					break;
			}
		}
    	return sql;
    }
}
