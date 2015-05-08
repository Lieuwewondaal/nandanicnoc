package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.Formats;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class BepalendKenmerk_Diagnose extends Model {

    private static final long serialVersionUID = 1L;

    public Bepalendkenmerk BepalendKenmerk_ID;
    
    public Diagnose Diagnose_ID;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,BepalendKenmerk_Diagnose> find = new Model.Finder<Long,BepalendKenmerk_Diagnose>(Long.class, BepalendKenmerk_Diagnose.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie c: DiagnoseVersie.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

