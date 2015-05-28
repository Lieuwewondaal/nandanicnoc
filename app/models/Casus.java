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
public class Casus extends Model {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long casus_id;
	
    @OneToMany(mappedBy="casus")
	public List<Casusoverzicht> casusoverzicht;
	
    /**
     * Generic query helper for entity Diagnose with id Long
     */
    public static Finder<Long,Casus> find = new Finder<Long,Casus>(Long.class, Casus.class); 
    
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Diagnose property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Casus> page(int page, int pageSize, String sortBy, String order, String filter) {
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

