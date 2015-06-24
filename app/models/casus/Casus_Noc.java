package models.casus;

import java.util.Date;

import javax.persistence.*;

import models.noc.Indicator;
import models.noc.Noc;

import com.avaje.ebean.Page;

import play.data.format.Formats;
import play.db.ebean.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Casus_Noc extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int casus_noc_id;
    
    @ManyToOne
    @JoinColumn(name="noc_id")
    public Noc noc;
    
    @ManyToOne
    @JoinColumn(name="indicator_id")
    public Indicator indicator;
    
	@ManyToOne
	@JoinColumn(name="casus_diagnose_id")
	public Casus_Diagnose casus_diagnose;
	
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date casus_noc_datum;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Casus_Noc> find = new Model.Finder<Long,Casus_Noc>(Long.class, Casus_Noc.class);

    public static Page<Casus_Noc> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("diagnose_id", filter)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

