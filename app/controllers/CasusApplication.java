package controllers;

import static play.data.Form.form;

import java.util.Calendar;

import javax.persistence.PersistenceException;

import models.Casus;
import models.Diagnoseoverzicht;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class CasusApplication extends Controller  {
    
    /**
     * Display the paginated list of cases
     * @param page
     * @param sortBy
     * @param order
     * @param filter
     * @return
     */
    public static Result listCasus(int page, String sortBy, String order, String filter) {
        return ok(
                casuslist.render(
                    Casus.page(page, 10, sortBy, order, filter),
                    sortBy, order, filter
                )
            );
        }
	
	/**
     * Display the 'new casus form'.
     */
    public static Result createCasus() {
        Form<Casus> casusForm = form(Casus.class);
        return ok(
    		createCasusform.render(casusForm)
        );
    }
    
    /**
     * Handle the 'new casus form' submission 
     */
    public static Result saveCasus() {
        Form<Casus> casusForm = form(Casus.class).bindFromRequest();
        
        if(casusForm.hasErrors()) {
            return badRequest(createCasusform.render(casusForm));
        }
        
        casusForm.get().casus_begindatum = Calendar.getInstance().getTime();
        
  	    try{
  	    	casusForm.get().save();
	    }
	    catch (PersistenceException e){
	        System.out.println("Casus already exists");
	    }
        
        flash("success", "Casus " + casusForm.get().casus_omschrijving + " has been created");
        return Application.GO_HOME;
    }
    
	/**
     * Display the 'new casus form'.
     */
    public static Result editCasus(Long id) {
        Form<Casus> casusForm = form(Casus.class).fill(
        		Casus.find.where()
			    .ilike("casus_id", id.toString())
			    .findList()
			    .get(0)
        );
        return ok(
    		editCasusform.render(id, casusForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the computer to edit
     */
    public static Result updateCasus(Long id) {
        Form<Casus> casusForm = form(Casus.class).bindFromRequest();
        if(casusForm.hasErrors()) {
            return badRequest(editCasusform.render(id, casusForm));
        }
        casusForm.get().update(id);
        flash("success", "Casus " + casusForm.get().casus_omschrijving + " has been updated");
        return Application.GO_HOME;
    }
    
    /**
     * Handle casus deletion
     */
    public static Result deleteCasus(Long id) {
        Casus.find.ref(id).delete();
        flash("success", "Casus has been deleted");
        return Application.GO_HOME;
    }
}
