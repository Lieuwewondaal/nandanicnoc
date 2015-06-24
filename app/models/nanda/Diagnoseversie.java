package models.nanda;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.Formats;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Diagnoseversie extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long diagnoseversie_id;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date diagnoseversie_begindatum;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date diagnoseversie_einddatum;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Diagnoseversie> find = new Model.Finder<Long,Diagnoseversie>(Long.class, Diagnoseversie.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie c: DiagnoseVersie.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

