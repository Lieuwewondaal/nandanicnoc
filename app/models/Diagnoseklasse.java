package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Diagnoseklasse extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long diagnoseklasse_id;
    
	public Long diagnoseklasse_klasse;
	
	public String diagnoseklasse_omschrijving;
	
	@ManyToOne
	@JoinColumn(name="diagnosedomein_id")
    public Diagnosedomein diagnosedomein;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Diagnoseklasse> find = new Model.Finder<Long,Diagnoseklasse>(Long.class, Diagnoseklasse.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseKlasse c: DiagnoseKlasse.find.orderBy("name").findList()) {
            options.put(c.DiagnoseKlasse_ID.toString(), c.DiagnoseKlasse_Naa);
        }
        return options;
    }*/

}

