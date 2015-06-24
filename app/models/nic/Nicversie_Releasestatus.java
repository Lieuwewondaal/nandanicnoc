package models.nic;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.Formats;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Nicversie_Releasestatus extends Model {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="nicversie_id")
    public Nicversie nicversie;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date nicversie_releasestatus_datum;
    
    public String nicversie_releasestatus_omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Nicversie_Releasestatus> find = new Model.Finder<Long,Nicversie_Releasestatus>(Long.class, Nicversie_Releasestatus.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie_ReleaseStatus c: DiagnoseVersie_ReleaseStatus.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

