package models.noc;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Indicator extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long indicator_id;
    
    public String indicator_omschrijving;
    
    @OneToMany(mappedBy="indicator")
	public List<Noc_Indicator> noc_indicator;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Indicator> find = new Model.Finder<Long,Indicator>(Long.class, Indicator.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Indicator c: Indicator.find.orderBy("gezondheidspatroon_omschrijving").findList()) {
            options.put(c.indicator_id.toString(), c.indicator_omschrijving);
        }
        return options;
    }

}

