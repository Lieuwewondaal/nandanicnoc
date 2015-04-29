package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class DiagnoseOverzicht extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long DiagnoseOverzicht_ID;
    
    public int Diagnose_Code;
    
    @ManyToOne
    public DiagnoseVersie DiagnoseVersie_ID;
    
    @ManyToOne
    public GezondheidsPatroon GezondheidsPatroon_ID;
    
    @ManyToOne
    public DiagnoseKlasse DiagnoseKlasse_ID;
    
    public String DiagnoseOverzicht_Omschrijving;
    
    public String DiagnoseOverzicht_Definitie;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,DiagnoseOverzicht> find = new Model.Finder<Long,DiagnoseOverzicht>(Long.class, DiagnoseOverzicht.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseOverzicht c: DiagnoseOverzicht.find.orderBy("name").findList()) {
            options.put(c.DiagnoseOverzicht_ID.toString(), c.DiagnoseOverzicht_Definitie);
        }
        return options;
    }

}

