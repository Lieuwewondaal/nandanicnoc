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
public class Samenhangendefactor_Diagnose extends Model {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="samenhangendefactor_id")
    public Samenhangendefactor samenhangendefactor;
    
    @ManyToOne
    @JoinColumn(name="diagnose_id")
    public Diagnose diagnose;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date diagnose_samenhangendefactor_releasestatus_datum;
    
    public String diagnose_samenhangendefactor_releasestatus_omschrijving;
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Samenhangendefactor_Diagnose> find = new Model.Finder<Long,Samenhangendefactor_Diagnose>(Long.class, Samenhangendefactor_Diagnose.class);

    public static Page<Samenhangendefactor_Diagnose> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("diagnose_id", filter)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

