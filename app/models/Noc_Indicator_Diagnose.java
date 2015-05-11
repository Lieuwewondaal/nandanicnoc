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
public class Noc_Indicator_Diagnose extends Model {

    private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="indicator_id")
    public Indicator indicator;
	
	@ManyToOne
	@JoinColumn(name="noc_id")
	public Noc noc;
	
	@ManyToOne
	@JoinColumn(name="diagnose_id")
	public Diagnose diagnose;
	
	@Id
    public Long diagnose_noc_releasestatus_id;
    
	@Formats.DateTime(pattern="yyyy-MM-dd")
    public Date diagnose_noc_releasestatus_date;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Noc_Indicator_Diagnose> find = new Model.Finder<Long,Noc_Indicator_Diagnose>(Long.class, Noc_Indicator_Diagnose.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseDomein c: DiagnoseDomein.find.orderBy("name").findList()) {
            options.put(c.DiagnoseDomein_ID.toString(), c.DiagnoseDomein_Naa);
        }
        return options;
    }*/

}

