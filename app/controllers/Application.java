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
        Form<Diagnoseoverzicht> diagnoseForm = form(Diagnoseoverzicht.class).fill(
        		Diagnoseoverzicht.find.where()
			    .ilike("diagnose_id", id.toString())
			    .findList()
			    .get(0)
        );
        return ok(
            editForm.render(id, diagnoseForm)
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
            return badRequest(editForm.render(diagnose_id, diagnoseForm));
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
        Form<Diagnoseoverzicht> diagnoseForm = form(Diagnoseoverzicht.class).bindFromRequest();
        DynamicForm domein = Form.form().bindFromRequest();
        
        if(diagnoseForm.hasErrors()) {
            return badRequest(createForm.render(diagnoseForm));
        }
        
        Diagnose diagnose = new Diagnose();
        diagnose.save();
        Diagnosedomein diagnosedomein = new Diagnosedomein();
        diagnosedomein.diagnosedomein_domein = Long.parseLong(domein.get("diagnosedomein.diagnosedomein_domein"));
        Diagnoseversie diagnoseversie = createDiagnoseVersie(diagnoseForm.get().diagnoseoverzicht_omschrijving);
        diagnoseForm.get().diagnoseklasse = getDiagnoseKlasse(diagnoseForm.get().diagnoseklasse, diagnosedomein);
        diagnoseForm.get().diagnose = diagnose;
        diagnoseForm.get().diagnoseversie = diagnoseversie;
        diagnoseForm.get().save();
        flash("success", "Diagnose " + diagnoseForm.get().diagnose + " has been created");
        return GO_HOME;
    }
    
    /**
     * Handle diagnose deletion
     */
    public static Result delete(Long id) {
        Diagnose.find.ref(id).delete();
        flash("success", "Diagnose has been deleted");
        return GO_HOME;
    }
    
    /**
     * Upload Excel file to server
     * @return
     */
    public static Result upload() {
    	String text = "";
    	  MultipartFormData body = request().body().asMultipartFormData();
    	  FilePart excelFile = body.getFile("picture");
    	  if (excelFile != null) {
    	    String fileName = excelFile.getFilename();
    	    String contentType = excelFile.getContentType(); 
    	    File file = excelFile.getFile();
    	    System.out.println(fileName);
    	    // Read Excel file
    	    text = readExcelFile(file, fileName);
    	    //System.out.println(text);
    	    flash("success", "File has been uploaded");
    	    return GO_HOME;
    	  } else {
    	    flash("error", "Missing file");
            flash("success", "File missing");
            return GO_HOME;
    	  }
	}
    
    /**
     * Read an Excel file and return the values of the first worksheet
     * Used to import Excel files to SQL
     * Must be a Microsoft Excel 1997 - 2003 file
     * @param file
     * @return contents of first worksheet
     */
    private static String readExcelFile(File file, String fileName){
    	String text = "";
	    try {
	        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
	        HSSFWorkbook wb = new HSSFWorkbook(fs);
	        HSSFSheet sheet = wb.getSheetAt(0);
	        HSSFRow row;

	        int rows; // No of rows
	        rows = sheet.getPhysicalNumberOfRows();

	        int cols = 0; // No of columns
	        int tmp = 0;

	        // This trick ensures that we get the data properly even if it doesn't start from first few rows
	        for(int i = 0; i < 10 || i < rows; i++) {
	            row = sheet.getRow(i);
	            if(row != null) {
	                tmp = sheet.getRow(i).getPhysicalNumberOfCells();
	                if(tmp > cols) cols = tmp;
	            }
	        }
	   
	        for(int r = 0; r < rows; r++) {
	            row = sheet.getRow(r);
	            if(row != null) {
	            	text += r+" ";
	            	
	            	// Do not get first row of data
	            	if(r != 0){
	            		switch(fileName){
	            		
	            		// Gezondheidspatroon access table
	        	        case "ref_patroon.xls":
	        	        	Gezondheidspatroon gezondheidspatroon = new Gezondheidspatroon();
	        	        	int GezondheidsPatroon_ID = 0,
	        	        		GezondheidsPatroon_omschrijving = 1;
	        	        	gezondheidspatroon.gezondheidspatroon_id = (long)Long.parseLong(row.getCell(GezondheidsPatroon_ID).toString());
	        	        	gezondheidspatroon.gezondheidspatroon_omschrijving = row.getCell(GezondheidsPatroon_omschrijving).toString();
	        	        	try{
	        	        		gezondheidspatroon.save();
	    	    		    }
	    	    		    catch (PersistenceException e){
	    	    		  	    System.out.println("Gezondheidspatroon already exists");
	    	    		    }
	        	        	
        	        	break;
        	        	
	        	        // Diagnose access table
	        	        case "ref_diagnose.xls":
	    	    		  Diagnose diagnose = new Diagnose();
	    	    		  Diagnoseversie diagnoseversie;
	    	    		  Diagnoseoverzicht diagnoseoverzicht = new Diagnoseoverzicht();
	    	    		  Diagnosedomein diagnosedomein = new Diagnosedomein();
	    	    		  Diagnoseklasse diagnoseklasse = new Diagnoseklasse();
	    	    		  
	    	    		  // Diagnose excel file row numbers
	    	    		  int Diagnose_ID = 0, 
	    					  Diagnose_Code = 1, 
	    					  Diagnose_Omschrijving = 2, 
	    					  Diagnose_Definitie = 3,
	    					  Diagnose_Domein = 5,
	    					  Diagnose_Klasse = 6,
	    					  Diagnose_Patroon = 7;
	    	    		  
	    	    		  // Diagnosedomein table
	    	    			  diagnosedomein.diagnosedomein_id = (long)row.getCell(Diagnose_Domein).getNumericCellValue();
	    	    		  	  diagnosedomein.diagnosedomein_domein = (long)row.getCell(Diagnose_Domein).getNumericCellValue();
    	    		  	  try{
	    	    		  	  diagnosedomein.save();
	    	    		  }
	    	    		  catch (PersistenceException e){
	    	    			  System.out.println("Domein already exists");
	    	    		  }
	    	    		  
	    	    		  // Diagnoseklasse table
	    	    			  diagnoseklasse.diagnoseklasse_klasse = (long)row.getCell(Diagnose_Klasse).getNumericCellValue();
	    	    			  diagnoseklasse.diagnosedomein = diagnosedomein;
    	    			  try{
	    	    			  diagnoseklasse.save();
	    	    		  }
	    	    		  catch (PersistenceException e){
	    	    			  System.out.println("Klasse already exists");
	    	    		  }
	    	    		  // Diagnose table
	    	    		  diagnose.diagnose_id = (long)Long.parseLong(row.getCell(Diagnose_ID).toString());
	    	    		  System.out.println(diagnose.diagnose_id);
	    	    		  try{
	    	    		  	diagnose.save();
	    	    		  }
	    	    		  catch (PersistenceException e){
	    	    		     System.out.println("Diagnose already exists");
	    	    		  }
    	    		  	  
	    	    		  // Diagnoseversie
	    	    		  diagnoseversie = createDiagnoseVersie(row.getCell(Diagnose_Definitie).toString());
	    	    		  
	    	    		  // Diagnoseoverzicht table
	    	    		  Gezondheidspatroon gezondheidspatroon_id = Gezondheidspatroon.find.byId((long)Long.parseLong(row.getCell(Diagnose_Patroon).toString()));
	    	    		  diagnoseoverzicht.diagnose = diagnose;
	    	    		  diagnoseoverzicht.diagnoseversie = diagnoseversie;
	    	    		  diagnoseoverzicht.diagnose_code = (int)row.getCell(Diagnose_Code).getNumericCellValue();
	    	    		  diagnoseoverzicht.gezondheidspatroon = gezondheidspatroon_id;
	    	    		  diagnoseoverzicht.diagnoseklasse = diagnoseklasse;
	    	    		  diagnoseoverzicht.diagnoseoverzicht_definitie = row.getCell(Diagnose_Definitie).toString();
	    	    		  diagnoseoverzicht.diagnoseoverzicht_omschrijving = row.getCell(Diagnose_Omschrijving).toString();
	    	    		  try{
	    	    			  diagnoseoverzicht.save();
	    	    		  }
	    	    		  catch (PersistenceException e){
	    	    		     System.out.println("Diagnose already exists");
	    	    		  }
	    	    		  
	    	    		  break;
	    	    		  
		            		// Bepalendkenmerk access table
	        	        case "ref_bepalend_kenmerk.xls":
		    	    		  int Bepalendkenmerk_ID = 0, 
		    					  Bepalendkenmerk_omschrijving = 2; 
		    	    		  
		    	    		  Bepalendkenmerk bepalendkenmerk = new Bepalendkenmerk();
		    	    		  bepalendkenmerk.bepalendkenmerk_id = (long)Long.parseLong(row.getCell(Bepalendkenmerk_ID).toString());
		    	    		  bepalendkenmerk.bepalendkenmerk_omschrijving = row.getCell(Bepalendkenmerk_omschrijving).toString();
		    	    		  try{
		    	    			  bepalendkenmerk.save();
		    	    		  }
		    	    		  catch (PersistenceException e){
		    	    		     System.out.println("Bepalend kenmerk already exists");
		    	    		  }

	    	    		  break;
	    	    		  
	    	    		  // Risicofactor access table
	        	        case "ref_risico_factor.xls":
		    	    		  int Risicofactor_ID = 0, 
	    	    				  Risicofactor_omschrijving = 2; 
		    	    		  
		    	    		  Risicofactor risicofactor = new Risicofactor();
		    	    		  risicofactor.risicofactor_id = (long)Long.parseLong(row.getCell(Risicofactor_ID).toString());
		    	    		  risicofactor.risicofactor_omschrijving = row.getCell(Risicofactor_omschrijving).toString();
		    	    		  try{
		    	    			  risicofactor.save();
		    	    		  }
		    	    		  catch (PersistenceException e){
		    	    		     System.out.println("Risicofactor already exists");
		    	    		  }
	        	        	break;
	        	        	
	        	        	// Samenhangende factor access table
	        	        case "ref_samenhangende_factor.xls":
		    	    		  int Samenhangendefactor_ID = 0, 
	    	    				  Samenhangendefactor_omschrijving = 2; 
		    	    		  
		    	    		  Samenhangendefactor samenhangendefactor = new Samenhangendefactor();
		    	    		  samenhangendefactor.samenhangendefactor_id = (long)Long.parseLong(row.getCell(Samenhangendefactor_ID).toString());
		    	    		  samenhangendefactor.samenhangendefactor_omschrijving = row.getCell(Samenhangendefactor_omschrijving).toString();
		    	    		  try{
		    	    			  samenhangendefactor.save();
		    	    		  }
		    	    		  catch (PersistenceException e){
		    	    		     System.out.println("Samenhangendefactor already exists");
		    	    		  }
	        	        	break;
	        	        	
	        	        	// koppeltabel
	        	        case "koppel_diagnose_bepalend_kenmerk.xls":
		    	    		  int Bepalendkenmerk_DiagnoseID = 1, 
	    				  		  Bepalendkenmerk_BepalendkenmerkID = 2; 
		    	    		  
		    	    		  Diagnoseoverzicht bepalendkenmerkdiagnose = Diagnoseoverzicht.find.where()
		    	    				    .ilike("diagnose_id", row.getCell(Bepalendkenmerk_DiagnoseID).toString())
		    	    				    .findList()
		    	    				    .get(0);
		    	    		  Bepalendkenmerk bepalendkenmerkdiagnoseversie = Bepalendkenmerk.find.byId((long)Long.parseLong(row.getCell(Bepalendkenmerk_BepalendkenmerkID).toString()));
		    	    		  
		    	    		  Diagnoseversie_Bepalendkenmerk diagnoseversie_bepalendkenmerk = new Diagnoseversie_Bepalendkenmerk();
		    	    		  
		    	    		  diagnoseversie_bepalendkenmerk.bepalendkenmerk_id = bepalendkenmerkdiagnoseversie;
		    	    		  diagnoseversie_bepalendkenmerk.diagnoseversie_id = bepalendkenmerkdiagnose.diagnoseversie;
		    	    		  
		    	    		  try{
		    	    			  diagnoseversie_bepalendkenmerk.save();
		    	    		  }
		    	    		  catch (PersistenceException e){
		    	    		     System.out.println("Samenhangendefactor already exists");
		    	    		  }
	        	        	break;
	        	        	
	        	        	// koppeltabel
	        	        case "koppel_diagnose_risico_factor.xls":
		    	    		  int Risicofactor_DiagnoseID = 1, 
    	    				      Risicofactor_RisicofactorID = 2; 
		    	    		  
		    	    		  Diagnoseoverzicht risicofactordiagnose = Diagnoseoverzicht.find.where()
		    	    				    .ilike("diagnose_id", row.getCell(Risicofactor_DiagnoseID).toString())
		    	    				    .findList()
		    	    				    .get(0);

		    	    		  Risicofactor risicofactordiagnoseversie = Risicofactor.find.byId((long)Long.parseLong(row.getCell(Risicofactor_RisicofactorID).toString()));
		    	    		  Diagnoseversie_Risicofactor diagnoseversie_risicofactor = new Diagnoseversie_Risicofactor();
		    	    		  
		    	    		  diagnoseversie_risicofactor.risicofactor_id= risicofactordiagnoseversie;
		    	    		  diagnoseversie_risicofactor.diagnoseversie_id = risicofactordiagnose.diagnoseversie;
		    	    		  
		    	    		  try{
		    	    			  diagnoseversie_risicofactor.save();
		    	    		  }
		    	    		  catch (PersistenceException e){
		    	    		     System.out.println("Samenhangendefactor already exists");
		    	    		  }
		    	    		  
	        	        	break;
	        	        	
	        	        	// koppeltabel
	        	        case "koppel_diagnose_samenhangende_factor.xls":
		    	    		  int Samenhangendefactor_DiagnoseID = 1, 
    	    				      Samenhangendefactor_SamenhangendefactorID = 2; 
		    	    		  
		    	    		  Diagnoseoverzicht samenhangendefactordiagnose = Diagnoseoverzicht.find.where()
		    	    				    .ilike("diagnose_id", row.getCell(Samenhangendefactor_DiagnoseID).toString())
		    	    				    .findList()
		    	    				    .get(0);
		    	    		  Samenhangendefactor samenhangendefactordiagnoseversie = Samenhangendefactor.find.byId((long)Long.parseLong(row.getCell(Samenhangendefactor_SamenhangendefactorID).toString()));
		    	    		  
		    	    		  Diagnoseversie_Samenhangendefactor diagnoseversie_samenhangendefactor = new Diagnoseversie_Samenhangendefactor();
		    	    		  
		    	    		  diagnoseversie_samenhangendefactor.samenhangendefactor_id = samenhangendefactordiagnoseversie;
		    	    		  diagnoseversie_samenhangendefactor.diagnoseversie_id = samenhangendefactordiagnose.diagnoseversie;
		    	    		  
		    	    		  try{
		    	    			  diagnoseversie_samenhangendefactor.save();
		    	    		  }
		    	    		  catch (PersistenceException e){
		    	    		     System.out.println("Samenhangendefactor already exists");
		    	    		  }
		    	    		  
	        	        	break;
	        	        	
	        	        	case "ref_indicator.xls":
	        	        		
        	        		break;
        	        		
	        	        	case "ref_score_schaal.xls":
	        	        		
        	        		break;
        	        		
	        	        	case "ref_score_values.xls":
	        	        		
        	        		break;
        	        		
	        	        	case "ref_zorgresultaat.xls":
	        	        		
        	        		break;
        	        		
	        	        	case "koppel_diagnose_zorgresultaat_indicator.xls":
	        	        		
        	        		break;
	            		}
	            	}
	                text += "\n";
	            }
	        }
	    } catch(Exception ioe) {
	        ioe.printStackTrace();
	    }	
	    return text;
    }
    
    /**
     * Function for adding various Diagnoseversie rows
     * @param omschrijving
     * @return diagnoseversie object
     */
    private static Diagnoseversie createDiagnoseVersie(String omschrijving){
		  // Diagnoseversie table
		  Diagnoseversie diagnoseversie = new Diagnoseversie();
		  Diagnoseversie_Releasestatus diagnoseversie_releasestatus = new Diagnoseversie_Releasestatus();
		  Calendar cal = Calendar.getInstance();
		  diagnoseversie.diagnoseversie_begindatum = cal.getTime();
		  diagnoseversie.diagnoseversie_einddatum = cal.getTime();
		  try{
			  diagnoseversie.save();
		  }
		  catch (PersistenceException e){
		     System.out.println("Diagnoseversie already exists");
		  }
		  
		  // Diagnoseversie_releasestatus table
		  diagnoseversie_releasestatus.diagnoseversie = diagnoseversie;
		  diagnoseversie_releasestatus.diagnoseversie_releasestatus_datum = cal.getTime();
		  diagnoseversie_releasestatus.diagnoseversie_releasestatus_omschrijving = omschrijving;
		  try{
			  diagnoseversie_releasestatus.save();
		  }
		  catch (PersistenceException e){
		     System.out.println("Diagnose already exists");
		  }
		return diagnoseversie;
    }
    
    /**
     * Function for finding the right id using Diagnoseklasse/domein combination
     * @param klasse
     * @param domein
     * @return
     */
    private static Diagnoseklasse getDiagnoseKlasse(Diagnoseklasse klasse, Diagnosedomein domein){
   	
		Diagnoseklasse diagnoseklasse = Diagnoseklasse.find.where()
				    .ilike("diagnosedomein.diagnosedomein_domein", domein.diagnosedomein_domein.toString())
				    .where()
				    .ilike("diagnoseklasse_klasse", klasse.diagnoseklasse_klasse.toString())
				    .findList()
				    .get(0);
		  
		  return diagnoseklasse;
    }
}
            
