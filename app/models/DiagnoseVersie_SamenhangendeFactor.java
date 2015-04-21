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
public class DiagnoseVersie_SamenhangendeFactor extends Model {

    private static final long serialVersionUID = 1L;

    public SamenhangendeFactor SamenhangendeFactr_ID;
    
    public DiagnoseVersie DiagnoseVersie_ID;
    
    public String DiagnoseVersie_SamenhangendeFactor_Omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,DiagnoseVersie_SamenhangendeFactor> find = new Model.Finder<Long,DiagnoseVersie_SamenhangendeFactor>(Long.class, DiagnoseVersie_SamenhangendeFactor.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie c: DiagnoseVersie.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

