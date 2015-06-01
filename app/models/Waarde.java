package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Waarde extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long waarde_id;
    
    public String waarde_omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Waarde> find = new Model.Finder<Long,Waarde>(Long.class, Waarde.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Waarde c: Waarde.find.orderBy("gezondheidspatroon_omschrijving").findList()) {
            options.put(c.waarde_id.toString(), c.waarde_omschrijving);
        }
        return options;
    }

}

