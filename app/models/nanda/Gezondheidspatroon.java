package models.nanda;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Gezondheidspatroon extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long gezondheidspatroon_id;
    
    public int gezondheidspatroon_code;
    
    public String gezondheidspatroon_omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Gezondheidspatroon> find = new Model.Finder<Long,Gezondheidspatroon>(Long.class, Gezondheidspatroon.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Gezondheidspatroon c: Gezondheidspatroon.find.orderBy("gezondheidspatroon_omschrijving").findList()) {
            options.put(c.gezondheidspatroon_id.toString(), c.gezondheidspatroon_omschrijving);
        }
        return options;
    }

}

