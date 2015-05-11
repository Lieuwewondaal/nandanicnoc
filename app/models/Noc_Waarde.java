package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Noc_Waarde extends Model {

    private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="waarde_id")
    public Waarde waarde;
	
	@ManyToOne
	@JoinColumn(name="noc_id")
	public Noc noc;
    
    public String noc_waarde_getalwaarde;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Noc_Waarde> find = new Model.Finder<Long,Noc_Waarde>(Long.class, Noc_Waarde.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseDomein c: DiagnoseDomein.find.orderBy("name").findList()) {
            options.put(c.DiagnoseDomein_ID.toString(), c.DiagnoseDomein_Naa);
        }
        return options;
    }*/

}

