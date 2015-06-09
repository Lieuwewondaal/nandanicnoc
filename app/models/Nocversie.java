package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.Formats;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Nocversie extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long nocversie_id;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date nocversie_begindatum;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date nocversie_einddatum;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Nocversie> find = new Model.Finder<Long,Nocversie>(Long.class, Nocversie.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie c: DiagnoseVersie.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

