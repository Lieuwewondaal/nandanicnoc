package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.FetchConfig;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import com.fasterxml.jackson.databind.JsonNode;

import play.libs.Json;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
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
        routes.Application.list(0, "diagnoseoverzicht_omschrijving", "asc", "")
    );
    
    /**
     * Handle default path requests, redirect to diagnoses list
     */
    public static Result index() {
        return GO_HOME;
    }

    /**
     * Display the paginated list of diagnoses.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on diagnose names
     */
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
    public static Result edit(Long id) {
    	// Get Diagnoseoverzicht values
        Form<Diagnoseoverzicht> diagnoseForm = form(Diagnoseoverzicht.class).fill(
        		Diagnoseoverzicht.find.where()
			    .ilike("diagnose_id", id.toString())
			    .findList()
			    .get(0)
        );
        
        // Get Bepalendkenmerk values of diagnose
    	List<Bepalendkenmerk_Diagnose> bepalendkenmerk_diagnose = Bepalendkenmerk_Diagnose
    			.find
    			.where()
    			.ilike("diagnose_id", id.toString())
                .findList();
    	
    	/*String sql = "select t0.nic_diagnose_releasestatus_datum c0, t0.nic_diagnose_releasestatus_omschrijving c1,t0.nicactiviteit_id c2,t0.diagnose_id c3,t1.nic_id c4,t1.nicoverzicht_omschrijving c5 from nic_diagnose t0 left outer join nicoverzicht t1 on t1.nic_id = t0.nic_id where diagnose_id = '491322316502'";
   	 
    	SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
   	 
   	 	// execute the query returning a List of MapBean objects
   	 	List<SqlRow> list = sqlQuery.findList();
   	 	JsonNode j = Json.toJson(list);
        int i = 0;
        String x = "";
        while(i < list.size()){
        	x += list.get(i);
        	i++;
        }
        */
        
    	// Get NIC/Activiteit attached to diagnose
    	List<Nic_Diagnose> nic = Nic_Diagnose
    			.find
    			.fetch("nic")
    			.fetch("nic.nicoverzicht")
    			.fetch("diagnose")
    			.fetch("nicactiviteit")
    			.where()
    			.like("diagnose", id.toString())
                .findList();
    	
    	List<Noc_Indicator_Diagnose> noc = Noc_Indicator_Diagnose
    			.find
    			.fetch("noc")
    			.fetch("noc.nocoverzicht")
    			.fetch("diagnose")
    			.fetch("indicator")
    			.where()
    			.like("diagnose", id.toString())
                .findList();
    	
        return ok(
            editForm.render(id, diagnoseForm, bepalendkenmerk_diagnose, nic, noc)
        );
    }
    
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
    public static Result getNicActiviteit(int page, String sortBy, String order, String filter) {
        return ok(
        		nic_diagnose.render(
    				Nic_Diagnose.page(page, 100, sortBy, order, filter),
                    sortBy, order, filter
                )
            );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the diagnose to edit
     */
    public static Result update(Long diagnose_id) {
        Form<Diagnoseoverzicht> diagnoseForm = form(Diagnoseoverzicht.class).bindFromRequest();
        if(diagnoseForm.hasErrors()) {
        	List<Bepalendkenmerk_Diagnose> bepalendkenmerk_diagnose = Bepalendkenmerk_Diagnose
        			.find
        			.where().
                    ilike("diagnose_id", diagnose_id.toString())
                    .findList();
        	
        	// Get NIC of diagnose
        	List<Nic_Diagnose> nic = Nic_Diagnose
        			.find
        			.fetch("nic")
        			.fetch("nic.nicoverzicht")
        			.fetch("nic_diagnose")
        			.fetch("nicactiviteit", new FetchConfig().query())
        			.where()
        			.like("nic_diagnose.diagnose", diagnose_id.toString())
                    .findList();
        	
        	// Get NOC of diagnose
        	List<Noc_Indicator_Diagnose> noc = Noc_Indicator_Diagnose
        			.find
        			.fetch("noc")
        			.fetch("noc.nocoverzicht")
        			.fetch("diagnose")
        			.fetch("indicator")
        			.where()
        			.like("diagnose", diagnose_id.toString())
                    .findList();
        	
            return badRequest(editForm.render(diagnose_id, diagnoseForm, bepalendkenmerk_diagnose, nic, noc));
        }
        diagnoseForm.get().update(diagnose_id);
        //flash("success", "Diagnose " + diagnoseForm.get().name + " has been updated");
        return GO_HOME;
    }
    
    /**
     * Display the 'new diagnose form'.
     */
    public static Result create() {
        Form<Diagnoseoverzicht> diagnoseForm = form(Diagnoseoverzicht.class);
        return ok(
            createForm.render(diagnoseForm)
        );
    }
    
    /**
     * Handle the 'new diagnose form' submission 
     */
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
    public static Result delete(Long id) {
        /*Diagnose.find.ref(id).delete();
        flash("success", "Diagnose has been deleted");*/
        return GO_HOME;
    }
}
            
