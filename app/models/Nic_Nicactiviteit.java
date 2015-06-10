package models;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import play.db.ebean.*;
import play.data.format.Formats;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Nic_Nicactiviteit extends Model {

    private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="nic_id", referencedColumnName = "nic_id")
	public Nic nic;
    
    @ManyToOne
	@JoinColumn(name="nicactiviteit_id")
	public Nicactiviteit nicactiviteit;
	
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date nic_nicactiviteit_releasestatus_datum;
    
    public String nic_nicactiviteit_releasestatus_omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Nic_Nicactiviteit> find = new Model.Finder<Long,Nic_Nicactiviteit>(Long.class, Nic_Nicactiviteit.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseDomein c: DiagnoseDomein.find.orderBy("name").findList()) {
            options.put(c.DiagnoseDomein_ID.toString(), c.DiagnoseDomein_Naa);
        }
        return options;
    }*/

}

