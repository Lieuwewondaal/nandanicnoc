package controllers;

import java.io.File;
import java.io.FileInputStream;

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
        flash("success", "Diagnose " + diagnoseForm.get().id + " has been created");
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
    	  FilePart picture = body.getFile("picture");
    	  if (picture != null) {
    	    String fileName = picture.getFilename();
    	    String contentType = picture.getContentType(); 
    	    File file = picture.getFile();
    	    
    	    // Read Excel file
    	    text = readExcelFile(file);
    	    System.out.println(text);
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
     * @param file
     * @return contents of first worksheet
     */
    private static String readExcelFile(File file){
    	String text = "";
	    try {
	        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
	        HSSFWorkbook wb = new HSSFWorkbook(fs);
	        HSSFSheet sheet = wb.getSheetAt(0);
	        HSSFRow row;
	        HSSFCell cell;

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
	                for(int c = 0; c < cols; c++) {
	                    cell = row.getCell(c);
	                    if(cell != null) {
	                    	text += cell.toString()+" ";
	                        
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
            