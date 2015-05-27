package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;

import javax.persistence.PersistenceException;

import models.Bepalendkenmerk;
import models.Bepalendkenmerk_Diagnose;
import models.Diagnose;
import models.Diagnosedomein;
import models.Diagnoseklasse;
import models.Diagnoseoverzicht;
import models.Diagnoseversie;
import models.Diagnoseversie_Bepalendkenmerk;
import models.Diagnoseversie_Releasestatus;
import models.Diagnoseversie_Risicofactor;
import models.Diagnoseversie_Samenhangendefactor;
import models.Gezondheidspatroon;
import models.Indicator;
import models.Nic;
import models.Nic_Diagnose;
import models.Nic_Nicactiviteit;
import models.Nicactiviteit;
import models.Nicoverzicht;
import models.Nicversie;
import models.Nicversie_Nicactiviteit;
import models.Nicversie_Releasestatus;
import models.Noc;
import models.Noc_Indicator;
import models.Noc_Indicator_Diagnose;
import models.Nocoverzicht;
import models.Nocversie;
import models.Nocversie_Indicator;
import models.Nocversie_Releasestatus;
import models.Risicofactor;
import models.Risicofactor_Diagnose;
import models.Samenhangendefactor;
import models.Samenhangendefactor_Diagnose;
import models.Waarde;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

public class ImportExcel extends Controller {
	
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
    	    return Application.GO_HOME;
    	  } else {
    	    flash("error", "Missing file");
            flash("success", "File missing");
            return Application.GO_HOME;
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
	        	        	importGezonheidsPatroon(row);
         	        	break;
        	        	
	        	        // Diagnose access table
	        	        case "ref_diagnose.xls":
	        	        	importDiagnose(row);
       	    		    break;
	    	    		  
		            		// Bepalendkenmerk access table
	        	        case "ref_bepalend_kenmerk.xls":
	        	        	importBepalendkenmerk(row);
    	    		    break;
	    	    		  
	    	    		  // Risicofactor access table
	        	        case "ref_risico_factor.xls":
	        	        	importRisicofactor(row);
	        	        break;
	        	        	
	        	        	// Samenhangende factor access table
	        	        case "ref_samenhangende_factor.xls":
	        	        	importSamenhangendefactor(row);
        	        	break;
	        	        	
	        	        	// koppeltabel
	        	        case "koppel_diagnose_bepalend_kenmerk.xls":
	        	        	importKoppelDiagnoseBepalendkenmerk(row);
        	        	break;
	        	        	
	        	        	// koppeltabel
	        	        case "koppel_diagnose_risico_factor.xls":
	        	        	importKoppelDiagnoseRisicofactor(row);  
	        	        break;
	        	        	
	        	        	// koppeltabel
	        	        case "koppel_diagnose_samenhangende_factor.xls":
	        	        	importDiagnoseSamenhangendefactor(row);
	        	        break;
	        	        	
        	        	case "ref_indicator.xls":
        	        		importIndicator(row);
    	        		break;
        	        		
        	        	case "ref_score_values.xls":
        	        		importScoreValues(row);
    	        		break;
    	        		
        	        	case "ref_score_schaal.xls":
        	        		/*
        	        		 * NOC -> NOC_Waarde -> Waarde Kan niet geïmporteerd worden. 
        	        		 * Waarde en NOC kunnen geïmporteerd worden, 
        	        		 * maar zijn momenteel niet aan elkaar gekoppeld in 
        	        		 * de Access database
        	        		 * 
        	        		 */
    	        		break;
    	        		
        	        	case "ref_zorgresultaat.xls":
        	        		importNoc(row);
    	        		break;
    	        		
        	        	case "koppel_diagnose_zorgresultaat_indicator.xls":
        	        		importKoppelDiagnoseNoc(row);
    	        		break;
    	        		
        	        	case "ref_activiteit.xls":
        	        		importActiviteit(row);
    	        		break;
    	        		
        	        	case "ref_interventie.xls":
        	        		importNic(row);
    	        		break;
    	        		
        	        	case "koppel_diagnose_interventie_activiteit.xls":  	 
        	        		importDiagnoseNicActiviteit(row);
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
   	Diagnoseklasse diagnoseklasse;
	try{
		diagnoseklasse = Diagnoseklasse.find.where()
				    .ilike("diagnosedomein.diagnosedomein_code", domein.diagnosedomein_code.toString())
				    .where()
				    .ilike("diagnoseklasse_code", klasse.diagnoseklasse_code.toString())
				    .findList()
				    .get(0);
   	}
   	catch(IndexOutOfBoundsException e){
   		diagnoseklasse = new Diagnoseklasse();
   	}
		  
		  return diagnoseklasse;
    }
    
    /**
     * Function for adding various Nicversie rows
     * @param omschrijving
     * @return nicversie object
     */
    private static Nicversie createNicVersie(String omschrijving){
		  // Diagnoseversie table
		  Nicversie nicversie = new Nicversie();
		  Nicversie_Releasestatus nicversie_releasestatus = new Nicversie_Releasestatus();
		  Calendar cal = Calendar.getInstance();
		  nicversie.nicversie_begindatum = cal.getTime();
		  nicversie.nicversie_einddatum = cal.getTime();
		  try{
			  nicversie.save();
		  }
		  catch (PersistenceException e){
		     System.out.println("Diagnoseversie already exists");
		  }
		  
		  // Diagnoseversie_releasestatus table
		  nicversie_releasestatus.nicversie = nicversie;
		  nicversie_releasestatus.nicversie_releasestatus_datum = cal.getTime();
		  nicversie_releasestatus.nicversie_releasestatus_omschrijving = omschrijving;
		  try{
			  nicversie_releasestatus.save();
		  }
		  catch (PersistenceException e){
		     System.out.println("Diagnose already exists");
		  }
		return nicversie;
    }
    
    /**
     * Function for adding various Nocversie rows
     * @param omschrijving
     * @return diagnoseversie object
     */
    private static Nocversie createNocVersie(String omschrijving){
		  // Diagnoseversie table
		  Nocversie nocversie = new Nocversie();
		  Nocversie_Releasestatus nocversie_releasestatus = new Nocversie_Releasestatus();
		  Calendar cal = Calendar.getInstance();
		  nocversie.nocversie_begindatum = cal.getTime();
		  nocversie.nocversie_einddatum = cal.getTime();
		  try{
			  nocversie.save();
		  }
		  catch (PersistenceException e){
		     System.out.println("Diagnoseversie already exists");
		  }
		  
		  // Diagnoseversie_releasestatus table
		  nocversie_releasestatus.nocversie = nocversie;
		  nocversie_releasestatus.nocversie_releasestatus_datum = cal.getTime();
		  nocversie_releasestatus.nocversie_releasestatus_omschrijving = omschrijving;
		  try{
			  nocversie_releasestatus.save();
		  }
		  catch (PersistenceException e){
		     System.out.println("Diagnose already exists");
		  }
		return nocversie;
    }
    
    /**
     * Import gezondheidspatroon from Excel row
     * @param row
     */
    private static void importGezonheidsPatroon(HSSFRow row){
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
    }
    
    /**
     * Import Diagnose from Excel row
     * @param row
     */
    private static void importDiagnose(HSSFRow row){
	  Diagnose diagnose = new Diagnose();
	  Diagnoseversie diagnoseversie;
	  Diagnoseoverzicht diagnoseoverzicht = new Diagnoseoverzicht();
	  Diagnosedomein diagnosedomein = new Diagnosedomein();
	  Diagnoseklasse diagnoseklasse = new Diagnoseklasse();
	  
	  // Diagnose excel file column numbers
	  int Diagnose_ID = 0, 
		  Diagnose_Code = 1, 
		  Diagnose_Omschrijving = 2, 
		  Diagnose_Definitie = 3,
		  Diagnose_Domein = 5,
		  Diagnose_Klasse = 6,
		  Diagnose_Patroon = 7;
	  
	  // Diagnosedomein table
		  diagnosedomein.diagnosedomein_id = (long)row.getCell(Diagnose_Domein).getNumericCellValue();
	  	  diagnosedomein.diagnosedomein_code = (long)row.getCell(Diagnose_Domein).getNumericCellValue();
	  	  try{
    		  	  diagnosedomein.save();
    		  }
    		  catch (PersistenceException e){
    			  System.out.println("Domein already exists");
    		  }
	  
	  // Diagnoseklasse table
		  diagnoseklasse.diagnoseklasse_code = (long)row.getCell(Diagnose_Klasse).getNumericCellValue();
		  diagnoseklasse.diagnosedomein = diagnosedomein;
		  Diagnoseklasse klasse = getDiagnoseKlasse(diagnoseklasse, diagnosedomein);
		  if(klasse.diagnoseklasse_code == null){
		  try{
    			  diagnoseklasse.save();
    		  }
    		  catch (PersistenceException e){
    			  System.out.println("Klasse already exists");
    		  }
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
    }
    
    /**
     * Import Bepalendkenmerk from Excel row
     * @param row
     */
    private static void importBepalendkenmerk(HSSFRow row){
	  int Bepalendkenmerk_ID = 0, 
		  Bepalendkenmerk_omschrijving = 2; 
	  
	  Bepalendkenmerk bepalendkenmerk = new Bepalendkenmerk();
	  bepalendkenmerk.bepalendkenmerk_id = (long)Long.parseLong(row.getCell(Bepalendkenmerk_ID).toString());
	  bepalendkenmerk.bepalendkenmerk_omschrijving = row.getCell(Bepalendkenmerk_omschrijving).toString();
	  try{
		  bepalendkenmerk.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("Bepalend kenmerk already exists, adding definition to diagnoseversie_bepalendkenmerk");
	  }
    }
    
    /**
     * Import Risicofactor from Excel row
     * @param row
     */
    private static void importRisicofactor(HSSFRow row){
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
    }
    
    /**
     * Import Samenhangendefactor from Excel row
     * @param row
     */
    private static void importSamenhangendefactor(HSSFRow row){
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
    }
    
    /**
     * Import KoppelDIagnoseBepalendkenmerk from Excel row
     * @param row
     */
    private static void importKoppelDiagnoseBepalendkenmerk(HSSFRow row){
	  int Bepalendkenmerk_DiagnoseID = 1, 
  		  Bepalendkenmerk_BepalendkenmerkID = 2; 
	  
	  Diagnoseoverzicht bepalendkenmerkdiagnose = Diagnoseoverzicht.find.where()
			    .ilike("diagnose_id", row.getCell(Bepalendkenmerk_DiagnoseID).toString())
			    .findList()
			    .get(0);
	  Bepalendkenmerk bepalendkenmerkdiagnoseversie = Bepalendkenmerk.find.byId((long)Long.parseLong(row.getCell(Bepalendkenmerk_BepalendkenmerkID).toString()));

	  Diagnoseversie_Bepalendkenmerk diagnoseversie_bepalendkenmerk = new Diagnoseversie_Bepalendkenmerk();
	  
	  diagnoseversie_bepalendkenmerk.bepalendkenmerk = bepalendkenmerkdiagnoseversie;
	  diagnoseversie_bepalendkenmerk.diagnoseversie = bepalendkenmerkdiagnose.diagnoseversie;

	  Diagnose bepalendkenmerk_diagnose_d = new Diagnose();
	  bepalendkenmerk_diagnose_d.diagnose_id = (long)Long.parseLong(row.getCell(Bepalendkenmerk_DiagnoseID).toString());
	  
	  Bepalendkenmerk_Diagnose bepalendkenmerk_diagnose = new Bepalendkenmerk_Diagnose();
	  bepalendkenmerk_diagnose.diagnose_bepalendkenmerk_releasestatus_datum = Calendar.getInstance().getTime();
	  bepalendkenmerk_diagnose.bepalendkenmerk = bepalendkenmerkdiagnoseversie;
	  bepalendkenmerk_diagnose.diagnose = bepalendkenmerk_diagnose_d;
	  
	  try{
		  diagnoseversie_bepalendkenmerk.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("Samenhangendefactor already exists");
	  }
	  
	  try{
	  bepalendkenmerk_diagnose.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("Samenhangendefactor already exists");
	  }
    }
    
    /**
     * Import KoppelDiagnoseRisicofactor from Excel row
     * @param row
     */
    private static void importKoppelDiagnoseRisicofactor(HSSFRow row){
	  int Risicofactor_DiagnoseID = 1, 
	      Risicofactor_RisicofactorID = 2; 
	  
	  Diagnoseoverzicht risicofactordiagnose = Diagnoseoverzicht.find.where()
			    .ilike("diagnose_id", row.getCell(Risicofactor_DiagnoseID).toString())
			    .findList()
			    .get(0);

	  Risicofactor risicofactordiagnoseversie = Risicofactor.find.byId((long)Long.parseLong(row.getCell(Risicofactor_RisicofactorID).toString()));
	  Diagnoseversie_Risicofactor diagnoseversie_risicofactor = new Diagnoseversie_Risicofactor();
	  
	  diagnoseversie_risicofactor.risicofactor= risicofactordiagnoseversie;
	  diagnoseversie_risicofactor.diagnoseversie = risicofactordiagnose.diagnoseversie;

	  Diagnose risicofactor_diagnose_d = new Diagnose();
	  risicofactor_diagnose_d.diagnose_id = (long)Long.parseLong(row.getCell(Risicofactor_DiagnoseID).toString());
	  
	  Risicofactor_Diagnose risicofactor_diagnose = new Risicofactor_Diagnose();
	  risicofactor_diagnose.diagnose_risicofactor_releasestatus_datum = Calendar.getInstance().getTime();
	  risicofactor_diagnose.risicofactor = risicofactordiagnoseversie;
	  risicofactor_diagnose.diagnose = risicofactor_diagnose_d;
	  
	  try{
		  diagnoseversie_risicofactor.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("Samenhangendefactor already exists");
	  }
	  
	  try{
		  risicofactor_diagnose.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("Samenhangendefactor already exists");
	  }
    }
    
    /**
     * Import DIagnoseSamenhangendefactr from Excel row
     * @param row
     */
    private static void importDiagnoseSamenhangendefactor(HSSFRow row){
	  int Samenhangendefactor_DiagnoseID = 1, 
	      Samenhangendefactor_SamenhangendefactorID = 2; 
	  
	  Diagnoseoverzicht samenhangendefactordiagnose = Diagnoseoverzicht.find.where()
			    .ilike("diagnose_id", row.getCell(Samenhangendefactor_DiagnoseID).toString())
			    .findList()
			    .get(0);
	  Samenhangendefactor samenhangendefactordiagnoseversie = Samenhangendefactor.find.byId((long)Long.parseLong(row.getCell(Samenhangendefactor_SamenhangendefactorID).toString()));
	  
	  Diagnoseversie_Samenhangendefactor diagnoseversie_samenhangendefactor = new Diagnoseversie_Samenhangendefactor();
	  
	  diagnoseversie_samenhangendefactor.samenhangendefactor = samenhangendefactordiagnoseversie;
	  diagnoseversie_samenhangendefactor.diagnoseversie = samenhangendefactordiagnose.diagnoseversie;
	  
	  Diagnose samenhangendefactor_diagnose_d = new Diagnose();
	  samenhangendefactor_diagnose_d.diagnose_id = (long)Long.parseLong(row.getCell(Samenhangendefactor_DiagnoseID).toString());
	  
	  Samenhangendefactor_Diagnose samenhangendefactor_diagnose = new Samenhangendefactor_Diagnose();
	  samenhangendefactor_diagnose.diagnose_samenhangendefactor_releasestatus_datum = Calendar.getInstance().getTime();
	  samenhangendefactor_diagnose.samenhangendefactor = samenhangendefactordiagnoseversie;
	  samenhangendefactor_diagnose.diagnose = samenhangendefactor_diagnose_d;
	  
	  try{
		  diagnoseversie_samenhangendefactor.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("Samenhangendefactor already exists");
	  }
	  
	  try{
		  samenhangendefactor_diagnose.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("Samenhangendefactor already exists");
	  }		  
    }
    
    /**
     * Import Indicator from Excel row
     * @param row
     */
    private static void importIndicator(HSSFRow row){
		Indicator indicator = new Indicator();
		int Indicator_ID = 0,
			Indicator_Omschrijving = 2;
		indicator.indicator_id = (long)Long.parseLong(row.getCell(Indicator_ID).toString());
		indicator.indicator_omschrijving = row.getCell(Indicator_Omschrijving).toString();
		try{
			indicator.save();
	    }
	    catch (PersistenceException e){
	  	    System.out.println("Gezondheidspatroon already exists");
	    }
    }
    
    /**
     * Import ScoreValue from Excel row
     * @param row
     */
    private static void importScoreValues(HSSFRow row){
    	Waarde waarde = new Waarde();
    	
    	int Waarde_ID = 0,
			Waarde_Omschrijving = 1,
    		Waarde_Score_ID = 2;
    	waarde.waarde_id = (long)Long.parseLong(row.getCell(Waarde_ID).toString());
    	waarde.waarde_omschrijving = row.getCell(Waarde_Omschrijving).toString();
    	try{
    		waarde.save();
	    }
	    catch (PersistenceException e){
	  	    System.out.println("Gezondheidspatroon already exists");
	    }
    }
    
    /**
     * Import Noc from Excel row
     * @param row
     */
    private static void importNoc(HSSFRow row){
	  Noc noc = new Noc();
	  Nocversie nocversie;
	  Nocoverzicht nocoverzicht = new Nocoverzicht();
	  
	  // Diagnose excel file column numbers
	  int Noc_ID = 0, 
		  Noc_Omschrijving = 2,
		  Noc_Definitie = 3,
		  Noc_Code = 4;
		  
	  
	  // Diagnose table
	  noc.noc_id = (long)Long.parseLong(row.getCell(Noc_ID).toString());
	  try{
		  noc.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("Noc already exists");
	  }
  	  
	  // Diagnoseversie
	  nocversie = createNocVersie(row.getCell(Noc_Definitie).toString());
	  
	  // Diagnoseoverzicht table
	  nocoverzicht.noc = noc;
	  nocoverzicht.nocversie = nocversie;
	  nocoverzicht.nocoverzicht_code = (int)row.getCell(Noc_Code).getNumericCellValue();
	  nocoverzicht.nocoverzicht_definitie = row.getCell(Noc_Definitie).toString();
	  nocoverzicht.nocoverzicht_omschrijving = row.getCell(Noc_Omschrijving).toString();
	  try{
		  nocoverzicht.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("Nicoverzicht already exists");
	  }
    }
    
    /**
     * Import KoppelDiagnoseNoc from Excel row
     * @param row
     */
    private static void importKoppelDiagnoseNoc(HSSFRow row){
	  int Noc_NocIndicator_Diagnose_DiagnoseID = 1, 
		  Noc_NocIndicator_Diagnose_NocID = 2,
		  Noc_NocIndicator_Diagnose_IndicatorID = 3;
	  
	  Noc nocdiagnose_noc = Noc.find.byId((long)Long.parseLong(row.getCell(Noc_NocIndicator_Diagnose_NocID).toString()));
	  Indicator nocdiagnose_indicator = Indicator.find.byId((long)Long.parseLong(row.getCell(Noc_NocIndicator_Diagnose_IndicatorID).toString()));
	  Diagnose nocdiagnose_diagnose = Diagnose.find.byId((long)Long.parseLong(row.getCell(Noc_NocIndicator_Diagnose_DiagnoseID).toString()));
	  Noc_Indicator noc_indicator = new Noc_Indicator();
	  Noc_Indicator_Diagnose noc_indicator_diagnose = new Noc_Indicator_Diagnose();
	  Nocoverzicht nocoverzichtversie;
	  Nocversie_Indicator nocversie_indicator = new Nocversie_Indicator();
	  
		try{
			
			nocoverzichtversie = Nocoverzicht.find.where()
					.ilike("noc.noc_id", row.getCell(Noc_NocIndicator_Diagnose_NocID).toString())
				    .findList()
				    .get(0);	    
	   	}
	   	catch(IndexOutOfBoundsException e){
	   		nocoverzichtversie = new Nocoverzicht();
	   	}
	  
	  
	  noc_indicator.noc = nocdiagnose_noc;
	  noc_indicator.indicator = nocdiagnose_indicator;
	  noc_indicator.noc_indicator_releasestatus_datum = Calendar.getInstance().getTime();
	  
	  noc_indicator_diagnose.noc = nocdiagnose_noc;
	  noc_indicator_diagnose.indicator = nocdiagnose_indicator;
	  noc_indicator_diagnose.diagnose = nocdiagnose_diagnose;
	  noc_indicator_diagnose.noc_indicator_diagnose_releasestatus_datum = Calendar.getInstance().getTime();
	  
	  nocversie_indicator.indicator = nocdiagnose_indicator;
	  nocversie_indicator.nocversie = nocoverzichtversie.nocversie;
	  
	  
	  try{
		  noc_indicator.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("noc_indicator already exists");
	  }
	  
	  try{
		  noc_indicator_diagnose.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("noc_diagnose already exists");
	  }		
	  
	  try{
		  nocversie_indicator.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("nocversie_indicator already exists");
	  }		
    }
    
    /**
     * Import Activiteit from Excel row
     * @param row
     */
    private static void importActiviteit(HSSFRow row){
	  int Activiteit_ID = 0, 
		  Activiteit_omschrijving = 2; 
	  
	  Nicactiviteit nicactiviteit = new Nicactiviteit();
	  nicactiviteit.nicactiviteit_id = (long)Long.parseLong(row.getCell(Activiteit_ID).toString());
	  nicactiviteit.nicactiviteit_omschrijving = row.getCell(Activiteit_omschrijving).toString();
	  try{
		  nicactiviteit.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("Nicactiviteit already exists");
	  }
    }
    
    /**
     * Import Nic from Excel row
     * @param row
     */
    private static void importNic(HSSFRow row){
	  Nic nic = new Nic();
	  Nicversie nicversie;
	  Nicoverzicht nicoverzicht = new Nicoverzicht();
	  
	  // Diagnose excel file column numbers
	  int Nic_ID = 0, 
		  Nic_Omschrijving = 2,
		  Nic_Code = 3, 
		  Nic_Definitie = 4;
	  
	  // Diagnose table
	  nic.nic_id = (long)Long.parseLong(row.getCell(Nic_ID).toString());
	  System.out.println(nic.nic_id);
	  try{
		  nic.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("Diagnose already exists");
	  }
  	  
	  // Diagnoseversie
	  nicversie = createNicVersie(row.getCell(Nic_Definitie).toString());
	  
	  // Diagnoseoverzicht table
	  nicoverzicht.nic = nic;
	  nicoverzicht.nicversie = nicversie;
	  nicoverzicht.nicoverzicht_code = (int)row.getCell(Nic_Code).getNumericCellValue();
	  nicoverzicht.nicoverzicht_definitie = row.getCell(Nic_Definitie).toString();
	  nicoverzicht.nicoverzicht_omschrijving = row.getCell(Nic_Omschrijving).toString();
	  try{
		  nicoverzicht.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("Nicoverzicht already exists");
	  }
    }
    
    /**
     * Import KoppelDiagnoseNicActiviteit from Excel row
     * @param row
     */
    private static void importDiagnoseNicActiviteit(HSSFRow row){
	  int Nic_NicActiviteit_Diagnose_DiagnoseID = 1, 
		  Nic_NicActiviteit_Diagnose_NicID = 2,
		  Nic_NicActiviteit_Diagnose_ActiviteitID = 3;
		  
	  Nic nicdiagnose_nic = Nic.find.byId((long)Long.parseLong(row.getCell(Nic_NicActiviteit_Diagnose_NicID).toString()));
	  Nicactiviteit nicdiagnose_activiteit = Nicactiviteit.find.byId((long)Long.parseLong(row.getCell(Nic_NicActiviteit_Diagnose_ActiviteitID).toString()));
	  Diagnose nicdiagnose_diagnose = Diagnose.find.byId((long)Long.parseLong(row.getCell(Nic_NicActiviteit_Diagnose_DiagnoseID).toString()));
	  Nic_Nicactiviteit nic_nicactiviteit = new Nic_Nicactiviteit();
	  Nic_Diagnose nic_diagnose = new Nic_Diagnose();
	  Nicoverzicht nicoverzichtversie;
	  Nicversie_Nicactiviteit nicversie_nicactiviteit = new Nicversie_Nicactiviteit();
	  
		try{
			
			nicoverzichtversie = Nicoverzicht.find.where()
					.ilike("nic.nic_id", row.getCell(Nic_NicActiviteit_Diagnose_NicID).toString())
				    .findList()
				    .get(0);	    
	   	}
	   	catch(IndexOutOfBoundsException e){
	   		nicoverzichtversie = new Nicoverzicht();
	   	}
	  
	  nic_nicactiviteit.nic = nicdiagnose_nic;
	  nic_nicactiviteit.nicactiviteit = nicdiagnose_activiteit;
	  nic_nicactiviteit.nic_nicactiviteit_releasestatus_datum = Calendar.getInstance().getTime();
	  
	  nic_diagnose.nic = nicdiagnose_nic;
	  nic_diagnose.nicactiviteit = nicdiagnose_activiteit;
	  nic_diagnose.diagnose = nicdiagnose_diagnose;
	  nic_diagnose.nic_diagnose_releasestatus_datum = Calendar.getInstance().getTime();
	  
	  nicversie_nicactiviteit.activiteit = nicdiagnose_activiteit;
	  nicversie_nicactiviteit.nicversie = nicoverzichtversie.nicversie;
	  
	  try{
		  nic_nicactiviteit.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("nic_nicactiviteit already exists");
	  }
	  
	  try{
		  nic_diagnose.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("nic_diagnose already exists");
	  }		    
	  
	  try{
		  nicversie_nicactiviteit.save();
	  }
	  catch (PersistenceException e){
	     System.out.println("nicversie_nicactiviteit already exists");
	  }
    }
}
