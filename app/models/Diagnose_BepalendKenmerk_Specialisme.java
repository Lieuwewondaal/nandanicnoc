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
public class Diagnose_BepalendKenmerk_Specialisme extends Model {

    private static final long serialVersionUID = 1L;

    public RisicoFactor RisicoFactor_ID;
    
    public Diagnose Diagnose_ID;
    
    public Specialisme Specialisme_ID;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Diagnose_BepalendKenmerk_Specialisme> find = new Model.Finder<Long,Diagnose_BepalendKenmerk_Specialisme>(Long.class, Diagnose_BepalendKenmerk_Specialisme.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie c: DiagnoseVersie.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

