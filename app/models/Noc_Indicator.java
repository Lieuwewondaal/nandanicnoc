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
public class Noc_Indicator extends Model {

    private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="indicator_id")
    public Indicator indicator;
	
	@ManyToOne
	@JoinColumn(name="noc_id")
	public Noc noc;
    
	@Formats.DateTime(pattern="yyyy-MM-dd")
    public Date noc_indicator_releasestatus_datum;
    
    public String noc_indicator_releasestatus_omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Noc_Indicator> find = new Model.Finder<Long,Noc_Indicator>(Long.class, Noc_Indicator.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseDomein c: DiagnoseDomein.find.orderBy("name").findList()) {
            options.put(c.DiagnoseDomein_ID.toString(), c.DiagnoseDomein_Naa);
        }
        return options;
    }*/

}

