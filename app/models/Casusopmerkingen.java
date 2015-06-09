package models;

import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Page;

import play.db.ebean.*;
import play.data.format.Formats;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Casusopmerkingen extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    public int casusopmerkingen_id;
    
	@ManyToOne
	@JoinColumn(name="casus_diagnose_id")
	public Casus_Diagnose casus_diagnose;
    
    public String casusopmerking;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date casusopmerkingdatum;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Casusopmerkingen> find = new Model.Finder<Long,Casusopmerkingen>(Long.class, Casusopmerkingen.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Casusopmerkingen c: Casusopmerkingen.find.orderBy("name").findList()) {
            options.put(c.casus_diagnose.toString(), c.casusopmerking);
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
    public static Page<Casusopmerkingen> page(int page, int pageSize, String sortBy, String order, String filter) {
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

