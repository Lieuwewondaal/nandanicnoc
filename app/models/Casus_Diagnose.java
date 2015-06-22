package models;

import java.util.Date;

import javax.persistence.*;

import com.avaje.ebean.Page;

import play.data.format.Formats;
import play.db.ebean.*;


/**
 * Company entity managed by Ebean
 */
@Entity 
public class Casus_Diagnose extends Model {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long casus_diagnose_id;
    
    public int user_id;
    
    @ManyToOne(optional=true) 
    @JoinColumn(name="diagnose_id")
    public Diagnose diagnose;
    
    @ManyToOne
    @JoinColumn(name="casus_id")
    public Casus casus;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date casus_diagnose_datum;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Casus_Diagnose> find = new Model.Finder<Long,Casus_Diagnose>(Long.class, Casus_Diagnose.class);

    public static Page<Casus_Diagnose> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("diagnose_id", filter)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

