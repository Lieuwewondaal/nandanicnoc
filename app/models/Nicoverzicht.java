package models;

import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Page;
import com.fasterxml.jackson.annotation.JsonBackReference;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Nicoverzicht extends Model {

    private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="nic_id")
	@JsonBackReference
	public Nic nic;
    
    @ManyToOne
    @JoinColumn(name="nicversie_id")
    public Nicversie nicversie;
    
    @ManyToOne
    @JoinColumn(name="nicklasse_id")
    public Nicklasse nicklasse;
    
    public int nicoverzicht_code;
    
    public String nicoverzicht_omschrijving;
    
    public String nicoverzicht_definitie;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Nicoverzicht> find = new Model.Finder<Long,Nicoverzicht>(Long.class, Nicoverzicht.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Nicoverzicht c: Nicoverzicht.find.orderBy("name").findList()) {
            options.put(c.nic.toString(), c.nicoverzicht_definitie);
        }
        return options;
    }
    
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Diagnose property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Nicoverzicht> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("diagnoseoverzicht_omschrijving", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("diagnose")
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

