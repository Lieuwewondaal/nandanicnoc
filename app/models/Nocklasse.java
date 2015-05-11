package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Nocklasse extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long nocklasse_id;
	
	@ManyToOne
	@JoinColumn(name="nocdomein_id")
	public Nocdomein nocdomein;
    
    public Long nocklasse_code;
    
    public String nocklasse_omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Nocklasse> find = new Model.Finder<Long,Nocklasse>(Long.class, Nocklasse.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseDomein c: DiagnoseDomein.find.orderBy("name").findList()) {
            options.put(c.DiagnoseDomein_ID.toString(), c.DiagnoseDomein_Naa);
        }
        return options;
    }*/

}

