package models;

import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Page;

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
    
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Diagnose property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Diagnoseoverzicht> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
            	.or(
            			com.avaje.ebean.Expr.like("gezondheidspatroon.gezondheidspatroon_omschrijving", "%" + filter + "%"), 
            			com.avaje.ebean.Expr.like("diagnoseoverzicht_omschrijving", "%" + filter + "%")
        			)
                .orderBy(sortBy + " " + order)
                .fetch("diagnose")
                .fetch("gezondheidspatroon")
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

