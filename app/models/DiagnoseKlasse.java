package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class DiagnoseKlasse extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long DiagnoseKlasse_ID;
    
	public String DiagnoseKlasse_Naam;
	
	@ManyToOne
    public DiagnoseDomein DiagnoseDomein_ID;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,DiagnoseKlasse> find = new Model.Finder<Long,DiagnoseKlasse>(Long.class, DiagnoseKlasse.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseKlasse c: DiagnoseKlasse.find.orderBy("name").findList()) {
            options.put(c.DiagnoseKlasse_ID.toString(), c.DiagnoseKlasse_Naa);
        }
        return options;
    }*/

}

