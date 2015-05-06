package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        routes.Application.list(0, "name", "asc", "")
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
                Diagnose.page(page, 10, sortBy, order, filter),
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
        Form<Diagnose> diagnoseForm = form(Diagnose.class).fill(
            Diagnose.find.byId(id)
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
    public static Result update(Long id) {
        Form<Diagnose> diagnoseForm = form(Diagnose.class).bindFromRequest();
        if(diagnoseForm.hasErrors()) {
            return badRequest(editForm.render(id, diagnoseForm));
        }
        diagnoseForm.get().update(id);
        flash("success", "Diagnose " + diagnoseForm.get().name + " has been updated");
        return GO_HOME;
    }
    
    /**
     * Display the 'new diagnose form'.
     */
    public static Result create() {
        Form<Diagnose> diagnoseForm = form(Diagnose.class);
        return ok(
            createForm.render(diagnoseForm)
        );
    }
    
    /**
     * Handle the 'new diagnose form' submission 
     */
    public static Result save() {
        Form<Diagnose> diagnoseForm = form(Diagnose.class).bindFromRequest();
        if(diagnoseForm.hasErrors()) {
            return badRequest(createForm.render(diagnoseForm));
        }
        diagnoseForm.get().save();
        flash("success", "Diagnose " + diagnoseForm.get().diagnose_id + " has been created");
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
	            	/*if(r == 0){
		                for(int c = 0; c < cols; c++) {
		                    cell = row.getCell(c);
		                    if(cell != null) {
		                    	if(cell.toString().equals("id")){
		                    		id = c;
		                    	}
		                    	if(cell.toString().equals("omschrijving")){
		                    		name = c;
		                    	}
		                    	comp.name = cell.toString();
		                    	text += cell.toString()+" ";
		                        
		                    }
		                }
	            	}*/
	            	// Do not get first row of data
	            	if(r != 0){
	            		switch(fileName){
	        	        case "ref_patroon.xls":
	        	        	Gezondheidspatroon gezondheidspatroon = new Gezondheidspatroon();
	        	        	int GezondheidsPatroon_ID = 0,
	        	        		GezondheidsPatroon_omschrijving = 1;
	        	        	gezondheidspatroon.gezondheidspatroon_id = (long)row.getCell(GezondheidsPatroon_ID).getNumericCellValue();
	        	        	gezondheidspatroon.gezondheidspatroon_omschrijving = row.getCell(GezondheidsPatroon_omschrijving).toString();
	        	        	try{
	        	        		gezondheidspatroon.save();
	    	    		    }
	    	    		    catch (PersistenceException e){
	    	    		  	    System.out.println("Gezondheidspatroon already exists");
	    	    		    }
	        	        	
	        	        case "ref_diagnose.xls":
	    	    		  Diagnose diagnose = new Diagnose();
	    	    		  Diagnoseversie diagnoseversie = new Diagnoseversie();
	    	    		  Diagnoseoverzicht diagnoseoverzicht = new Diagnoseoverzicht();
	    	    		  Diagnosedomein diagnosedomein = new Diagnosedomein();
	    	    		  Diagnoseklasse diagnoseklasse = new Diagnoseklasse();
	    	    		  Diagnoseversie_Releasestatus diagnoseversie_releasestatus = new Diagnoseversie_Releasestatus();
	    	    		  
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
	    	    		  diagnose.diagnose_id = (long)Long.parseLong(row.getCell(Diagnose_ID).toString().substring(1));
	    	    		  System.out.println(diagnose.diagnose_id);
	    	    		  try{
	    	    		  	diagnose.save();
	    	    		  }
	    	    		  catch (PersistenceException e){
	    	    		     System.out.println("Diagnose already exists");
	    	    		  }
	    	    		  // Diagnoseversie table
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
	    	    		  diagnoseversie_releasestatus.diagnoseversie_releasestatus_omschrijving = row.getCell(Diagnose_Definitie).toString();
	    	    		  try{
	    	    			  diagnoseversie_releasestatus.save();
	    	    		  }
	    	    		  catch (PersistenceException e){
	    	    		     System.out.println("Diagnose already exists");
	    	    		  }
	    	    		  
	    	    		  // Diagnoseoverzicht table
	    	    		  Gezondheidspatroon gezondheidspatroon_id = Gezondheidspatroon.find.byId((long)Long.parseLong(row.getCell(Diagnose_Patroon).toString().substring(1)));
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
}
            
