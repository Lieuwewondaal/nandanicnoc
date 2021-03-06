package models.nic;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import com.avaje.ebean.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Diagnose entity managed by Ebean
 */
@Entity 
public class Nic extends Model {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long nic_id;
	
    @OneToMany(mappedBy="nic")
    @JsonManagedReference
	public List<Nicoverzicht> nicoverzicht;
    
	@OneToMany(mappedBy="nic")
	@JsonManagedReference
	public List<Nic_Diagnose> nic_diagnose;
	
	@OneToMany(mappedBy="nic", fetch=FetchType.EAGER)
	public List<Nic_Nicactiviteit> nic_nicactiviteit;
	
    /**
     * Generic query helper for entity Diagnose with id Long
     */
    public static Finder<Long,Nic> find = new Finder<Long,Nic>(Long.class, Nic.class); 
    
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Diagnose property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Nic> page(int page, int pageSize, String sortBy, String order, String filter) {
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

