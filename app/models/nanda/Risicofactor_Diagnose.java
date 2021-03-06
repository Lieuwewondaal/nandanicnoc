package models.nanda;

import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Page;

import play.db.ebean.*;
import play.data.format.Formats;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Risicofactor_Diagnose extends Model {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="risicofactor_id")
    public Risicofactor risicofactor;
    
    @ManyToOne
    @JoinColumn(name="diagnose_id")
    public Diagnose diagnose;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date diagnose_risicofactor_releasestatus_datum;
    
    public String diagnose_risicofactor_releasestatus_omschrijving;
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Risicofactor_Diagnose> find = new Model.Finder<Long,Risicofactor_Diagnose>(Long.class, Risicofactor_Diagnose.class);

    public static Page<Risicofactor_Diagnose> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("diagnose_id", filter)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

