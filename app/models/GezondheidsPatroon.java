package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class GezondheidsPatroon extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long GezondheidsPatroon_ID;
    
    public int GezondheidsPatroon_Code;
    
    public String GezondheidsPatroon_Omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,GezondheidsPatroon> find = new Model.Finder<Long,GezondheidsPatroon>(Long.class, GezondheidsPatroon.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(GezondheidsPatroon c: GezondheidsPatroon.find.orderBy("name").findList()) {
            options.put(c.GezondheidsPatroon_ID.toString(), c.GezondheidsPatroon_Omschrijving);
        }
        return options;
    }

}

