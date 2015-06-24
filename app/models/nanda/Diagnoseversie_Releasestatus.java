package models.nanda;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.Formats;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Diagnoseversie_Releasestatus extends Model {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="diagnoseversie_id")
    public Diagnoseversie diagnoseversie;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date diagnoseversie_releasestatus_datum;
    
    public String diagnoseversie_releasestatus_omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Diagnoseversie_Releasestatus> find = new Model.Finder<Long,Diagnoseversie_Releasestatus>(Long.class, Diagnoseversie_Releasestatus.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie_ReleaseStatus c: DiagnoseVersie_ReleaseStatus.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

