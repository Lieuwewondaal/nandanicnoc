package models.casus;

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
	
	@Constraints.Required
    public String casus_omschrijving;
    
    public String casus_definitie;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date casus_begindatum;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date casus_einddatum;
	
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
    /**
     * Return a page of a diagnosis
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
            	.or(
            			com.avaje.ebean.Expr.like("casus_definitie", "%" + filter + "%"), 
            			com.avaje.ebean.Expr.like("casus_omschrijving", "%" + filter + "%")
        			)
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }
    
}

