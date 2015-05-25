package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Nicdomein extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long nicdomein_id;
    
    public Long nicdomein_code;
    
    public String nicdomein_omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Nicdomein> find = new Model.Finder<Long,Nicdomein>(Long.class, Nicdomein.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseDomein c: DiagnoseDomein.find.orderBy("name").findList()) {
            options.put(c.DiagnoseDomein_ID.toString(), c.DiagnoseDomein_Naa);
        }
        return options;
    }*/

}

