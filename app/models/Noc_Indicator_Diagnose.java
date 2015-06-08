package models;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	@JsonBackReference
	public Diagnose diagnose;
	
	@Formats.DateTime(pattern="yyyy-MM-dd")
    public Date noc_indicator_diagnose_releasestatus_datum;
    
    public String noc_indicator_diagnose_releasestatus_omschrijving;
    
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

