package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Nicversie_Nicactiviteit extends Model {

    private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="nicversie_id")
    public Nicversie nicversie;
	
	@ManyToOne
	@JoinColumn(name="nicactiviteit_id")
	public Nicactiviteit activiteit;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Nicversie_Nicactiviteit> find = new Model.Finder<Long,Nicversie_Nicactiviteit>(Long.class, Nicversie_Nicactiviteit.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseDomein c: DiagnoseDomein.find.orderBy("name").findList()) {
            options.put(c.DiagnoseDomein_ID.toString(), c.DiagnoseDomein_Naa);
        }
        return options;
    }*/

}

