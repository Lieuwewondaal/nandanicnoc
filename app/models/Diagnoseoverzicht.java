package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Diagnoseoverzicht extends Model {

    private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="diagnose_id")
	public Diagnose diagnose;
    
    @ManyToOne
    @JoinColumn(name="diagnoseversie_id")
    public Diagnoseversie diagnoseversie;
    
    public int diagnose_code;
    
    @ManyToOne
    @JoinColumn(name="gezondheidspatroon_id")
    public Gezondheidspatroon gezondheidspatroon;
    
    @ManyToOne
    @JoinColumn(name="diagnoseklasse_id")
    public Diagnoseklasse diagnoseklasse;
    
    public String diagnoseoverzicht_omschrijving;
    
    public String diagnoseoverzicht_definitie;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Diagnoseoverzicht> find = new Model.Finder<Long,Diagnoseoverzicht>(Long.class, Diagnoseoverzicht.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Diagnoseoverzicht c: Diagnoseoverzicht.find.orderBy("name").findList()) {
            options.put(c.diagnose.toString(), c.diagnoseoverzicht_definitie);
        }
        return options;
    }

}

