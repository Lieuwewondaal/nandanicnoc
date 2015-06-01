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
public class Nicversie extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long nicversie_id;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date nicversie_begindatum;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date nicversie_einddatum;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Nicversie> find = new Model.Finder<Long,Nicversie>(Long.class, Nicversie.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie c: DiagnoseVersie.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

