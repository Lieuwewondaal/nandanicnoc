package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.Formats;

/**
 * Company entity managed by Ebean
 */
@Entity 
public class Diagnose_Specialisme extends Model {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="diagnose_id")
    public Diagnose diagnose;
    
    @ManyToOne
    @JoinColumn(name="specialisme_id")
    public Specialisme specialisme;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date diagnose_specialisme_releasestatus_datum;
    
    public String diagnose_specialisme_releasestatus_omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Diagnose_Specialisme> find = new Model.Finder<Long,Diagnose_Specialisme>(Long.class, Diagnose_Specialisme.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie c: DiagnoseVersie.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

