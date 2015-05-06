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
public class DiagnoseVersie_BepalendKenmerk extends Model {

    private static final long serialVersionUID = 1L;

    public BepalendKenmerk BepalendKenmerk_ID;
    
    public Diagnoseversie DiagnoseVersie_ID;
    
    public String DiagnoseVersie_BepalendKenmerk_Omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,DiagnoseVersie_BepalendKenmerk> find = new Model.Finder<Long,DiagnoseVersie_BepalendKenmerk>(Long.class, DiagnoseVersie_BepalendKenmerk.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie c: DiagnoseVersie.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

