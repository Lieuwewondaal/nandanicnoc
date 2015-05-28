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
public class Casus_Samenhangendefactor extends Model {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="risicofactor_id")
    public Risicofactor risicofactor;
    
	@ManyToOne
	@JoinColumn(name="casus_diagnose_id")
	public Casus_Diagnose casus_diagnose;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Casus_Samenhangendefactor> find = new Model.Finder<Long,Casus_Samenhangendefactor>(Long.class, Casus_Samenhangendefactor.class);

    public static Page<Casus_Samenhangendefactor> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("diagnose_id", filter)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

