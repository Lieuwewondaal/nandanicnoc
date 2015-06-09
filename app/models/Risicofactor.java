package models;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import play.db.ebean.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Risicofactor extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    public Long risicofactor_id;
    
    public String risicofactor_omschrijving;
    
	@OneToMany(mappedBy="risicofactor")
	@JsonManagedReference
	public List<Risicofactor_Diagnose> risicofactor_diagnose;
	
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Risicofactor> find = new Model.Finder<Long,Risicofactor>(Long.class, Risicofactor.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie c: DiagnoseVersie.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

