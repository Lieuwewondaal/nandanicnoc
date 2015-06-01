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
public class Casus_Nic extends Model {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="nic_id")
    public Nic nic;
    
    @ManyToOne
    @JoinColumn(name="nicactiviteit_id")
    public Nicactiviteit nicactiviteit;
    
	@ManyToOne
	@JoinColumn(name="casus_diagnose_id")
	public Casus_Diagnose casus_diagnose;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Casus_Nic> find = new Model.Finder<Long,Casus_Nic>(Long.class, Casus_Nic.class);

    public static Page<Casus_Nic> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("diagnose_id", filter)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

