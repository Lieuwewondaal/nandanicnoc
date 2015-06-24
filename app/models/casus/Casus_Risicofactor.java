package models.casus;

import javax.persistence.*;

import models.nanda.Risicofactor;

import com.avaje.ebean.Page;

import play.db.ebean.*;


/**
 * Company entity managed by Ebean
 */
@Entity 
public class Casus_Risicofactor extends Model {

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
    public static Model.Finder<Long,Casus_Risicofactor> find = new Model.Finder<Long,Casus_Risicofactor>(Long.class, Casus_Risicofactor.class);

    public static Page<Casus_Risicofactor> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("diagnose_id", filter)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

