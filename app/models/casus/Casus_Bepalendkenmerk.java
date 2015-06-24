package models.casus;

import javax.persistence.*;

import models.nanda.Bepalendkenmerk;

import com.avaje.ebean.Page;

import play.db.ebean.*;

/**
 * Company entity managed by Ebean
 */
@Entity 
public class Casus_Bepalendkenmerk extends Model {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="bepalendkenmerk_id")
    public Bepalendkenmerk bepalendkenmerk;
    
	@ManyToOne
	@JoinColumn(name="casus_diagnose_id")
	public Casus_Diagnose casus_diagnose;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Casus_Bepalendkenmerk> find = new Model.Finder<Long,Casus_Bepalendkenmerk>(Long.class, Casus_Bepalendkenmerk.class);

    public static Page<Casus_Bepalendkenmerk> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("diagnose_id", filter)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

