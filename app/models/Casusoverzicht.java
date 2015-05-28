package models;

import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Page;

import play.db.ebean.*;
import play.data.format.Formats;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Casusoverzicht extends Model {

    private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="casus_id")
	public Casus casus;
    
    public String casus_omschrijving;
    
    public String casus_definitie;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date casus_begindatum;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date casus_einddatum;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Casusoverzicht> find = new Model.Finder<Long,Casusoverzicht>(Long.class, Casusoverzicht.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Casusoverzicht c: Casusoverzicht.find.orderBy("name").findList()) {
            options.put(c.casus.toString(), c.casus_omschrijving);
        }
        return options;
    }
    
    /**
     * Return a page of a diagnosis
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Diagnose property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Casusoverzicht> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
            	.or(
            			com.avaje.ebean.Expr.like("gezondheidspatroon.gezondheidspatroon_omschrijving", "%" + filter + "%"), 
            			com.avaje.ebean.Expr.like("casus_omschrijving", "%" + filter + "%")
        			)
                .orderBy(sortBy + " " + order)
                .fetch("diagnose")
                .fetch("gezondheidspatroon")
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

