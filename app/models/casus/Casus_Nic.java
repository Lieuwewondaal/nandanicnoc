package models.casus;

import java.util.Date;

import javax.persistence.*;

import models.nic.Nic;
import models.nic.Nicactiviteit;

import com.avaje.ebean.Page;

import play.data.format.Formats;
import play.db.ebean.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Casus_Nic extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int casus_nic_id;
    
    @ManyToOne
    @JoinColumn(name="nic_id")
    public Nic nic;
    
    @ManyToOne
    @JoinColumn(name="nicactiviteit_id")
    public Nicactiviteit nicactiviteit;
    
	@ManyToOne
	@JoinColumn(name="casus_diagnose_id")
	public Casus_Diagnose casus_diagnose;
	
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date casus_nic_datum;
    
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

