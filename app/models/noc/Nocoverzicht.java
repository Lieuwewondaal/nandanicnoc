package models.noc;

import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Page;
import com.fasterxml.jackson.annotation.JsonBackReference;

import play.db.ebean.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Nocoverzicht extends Model {

    private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="noc_id")
	@JsonBackReference
	public Noc noc;
    
    @ManyToOne
    @JoinColumn(name="nocversie_id")
    public Nocversie nocversie;
    
    @ManyToOne
    @JoinColumn(name="nocklasse_id")
    public Nocklasse nocklasse;
    
    public String nocoverzicht_omschrijving;
    
    public String nocoverzicht_definitie;
    
    public int nocoverzicht_code;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Nocoverzicht> find = new Model.Finder<Long,Nocoverzicht>(Long.class, Nocoverzicht.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Nocoverzicht c: Nocoverzicht.find.orderBy("name").findList()) {
            options.put(c.noc.toString(), c.nocoverzicht_definitie);
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
    public static Page<Nocoverzicht> page(int page, int pageSize, String sortBy, String order, String filter) {
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

