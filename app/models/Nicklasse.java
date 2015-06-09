package models;

import javax.persistence.*;

import play.db.ebean.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Nicklasse extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long nicklasse_id;
	
	@ManyToOne
	@JoinColumn(name="nicdomein_id")
	public Nicdomein nicdomein;
    
    public Long nicklasse_code;
    
    public String nicklasse_omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Nicklasse> find = new Model.Finder<Long,Nicklasse>(Long.class, Nicklasse.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseDomein c: DiagnoseDomein.find.orderBy("name").findList()) {
            options.put(c.DiagnoseDomein_ID.toString(), c.DiagnoseDomein_Naa);
        }
        return options;
    }*/

}

