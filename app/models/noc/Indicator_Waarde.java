package models.noc;

import javax.persistence.*;

import play.db.ebean.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Indicator_Waarde extends Model {

    private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="indicator_id")
    public Indicator indicator;
	
	@ManyToOne
	@JoinColumn(name="waarde_id")
	public Waarde waarde;
    
    public String indicator_waarde_getalwaarde;
    
    public String indicator_waarde_omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Indicator_Waarde> find = new Model.Finder<Long,Indicator_Waarde>(Long.class, Indicator_Waarde.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseDomein c: DiagnoseDomein.find.orderBy("name").findList()) {
            options.put(c.DiagnoseDomein_ID.toString(), c.DiagnoseDomein_Naa);
        }
        return options;
    }*/

}

