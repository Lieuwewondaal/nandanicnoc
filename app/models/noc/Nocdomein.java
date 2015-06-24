package models.noc;

import javax.persistence.*;

import play.db.ebean.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Nocdomein extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long nocdomein_id;
    
    public Long nocdomein_code;
    
    public String nocdomein_omschrijving;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Nocdomein> find = new Model.Finder<Long,Nocdomein>(Long.class, Nocdomein.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseDomein c: DiagnoseDomein.find.orderBy("name").findList()) {
            options.put(c.DiagnoseDomein_ID.toString(), c.DiagnoseDomein_Naa);
        }
        return options;
    }*/

}

