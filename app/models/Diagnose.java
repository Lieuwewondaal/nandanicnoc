package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;

import com.avaje.ebean.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Diagnose entity managed by Ebean
 */
@Entity 
public class Diagnose extends Model {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long diagnose_id;
	
	@OneToMany(mappedBy="diagnose")
	@JsonManagedReference
	public List<Diagnoseoverzicht> diagnoseoverzicht;
	
	@OneToMany(mappedBy="diagnose")
	@JsonManagedReference
	public List<Noc_Indicator_Diagnose> noc_indicator_diagnose;
	
	@OneToMany(mappedBy="diagnose")
	@JsonManagedReference
	public List<Nic_Diagnose> nic_diagnose;
    
    /**
     * Generic query helper for entity Diagnose with id Long
     */
    public static Finder<Long,Diagnose> find = new Finder<Long,Diagnose>(Long.class, Diagnose.class); 
    
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Diagnose property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Diagnose> page(int page, int pageSize, String sortBy, String order, String filter) {
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

