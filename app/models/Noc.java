package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

/**
 * Diagnose entity managed by Ebean
 */
@Entity 
public class Noc extends Model {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long noc_id;
    
    @OneToMany(mappedBy="noc")
	public List<Noc_Indicator> noc_indicator;
    
	@OneToMany(mappedBy="noc")
	public List<Noc_Waarde> noc_waarde;
	
	@OneToMany(mappedBy="noc")
	public List<Nocoverzicht> nocoverzicht;
    /**
     * Generic query helper for entity Diagnose with id Long
     */
    public static Finder<Long,Noc> find = new Finder<Long,Noc>(Long.class, Noc.class); 
    
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Diagnose property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Noc> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("company")
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }
    
}

