package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class DiagnoseDomein extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long DiagnoseDomein_ID;
    
    public String DiagnoseDomein_Naam;
    
    public String DiagnoseDomein_Omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,DiagnoseDomein> find = new Model.Finder<Long,DiagnoseDomein>(Long.class, DiagnoseDomein.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseDomein c: DiagnoseDomein.find.orderBy("name").findList()) {
            options.put(c.DiagnoseDomein_ID.toString(), c.DiagnoseDomein_Naa);
        }
        return options;
    }*/

}

