package models;


import javax.persistence.*;

import play.db.ebean.*;

/**
 * Company entity managed by Ebean
 */
@Entity 
public class Diagnose_RisicoFactor_Specialisme extends Model {

    private static final long serialVersionUID = 1L;

    public Risicofactor RisicoFactor_ID;
    
    public Diagnose Diagnose_ID;
    
    public Specialisme Specialisme_ID;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Diagnose_RisicoFactor_Specialisme> find = new Model.Finder<Long,Diagnose_RisicoFactor_Specialisme>(Long.class, Diagnose_RisicoFactor_Specialisme.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie c: DiagnoseVersie.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

