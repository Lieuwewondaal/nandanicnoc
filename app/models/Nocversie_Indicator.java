package models;

import javax.persistence.*;

import play.db.ebean.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Nocversie_Indicator extends Model {

    private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="nocversie_id")
    public Nocversie nocversie;
	
	@ManyToOne
	@JoinColumn(name="indicator_id")
	public Indicator indicator;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Nocversie_Indicator> find = new Model.Finder<Long,Nocversie_Indicator>(Long.class, Nocversie_Indicator.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseDomein c: DiagnoseDomein.find.orderBy("name").findList()) {
            options.put(c.DiagnoseDomein_ID.toString(), c.DiagnoseDomein_Naa);
        }
        return options;
    }*/

}

