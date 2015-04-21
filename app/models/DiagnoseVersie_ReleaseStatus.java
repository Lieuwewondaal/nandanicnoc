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
public class DiagnoseVersie_ReleaseStatus extends Model {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    public DiagnoseVersie DiagnoseVersie_ID;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date DiagnoseVersie_ReleaseStatus_Datum;
    
    public String DiagnoseVersie_ReleaseStatus_Omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,DiagnoseVersie_ReleaseStatus> find = new Model.Finder<Long,DiagnoseVersie_ReleaseStatus>(Long.class, DiagnoseVersie_ReleaseStatus.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie_ReleaseStatus c: DiagnoseVersie_ReleaseStatus.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

