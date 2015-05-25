package models;

import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.FetchConfig;
import com.avaje.ebean.Page;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;

import play.db.ebean.*;
import play.data.format.Formats;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Nic_Diagnose extends Model {

    private static final long serialVersionUID = 1L;

    @ManyToOne
	@JoinColumn(name="nic_id")
	public Nic nic;
    
    @ManyToOne
	@JoinColumn(name="nicactiviteit_id")
	public Nicactiviteit nicactiviteit;
    
    @ManyToOne
	@JoinColumn(name="diagnose_id")
	public Diagnose diagnose;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date nic_diagnose_releasestatus_datum;
    
    public String nic_diagnose_releasestatus_omschrijving;
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Nic_Diagnose> find = new Model.Finder<Long,Nic_Diagnose>(Long.class, Nic_Diagnose.class);


    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Diagnose property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Nic_Diagnose> page(int page, int pageSize, String sortBy, String order, String filter) {
    	String sql = "select t0.nic_diagnose_releasestatus_datum c0, t0.nic_diagnose_releasestatus_omschrijving c1,t0.nicactiviteit_id c2,t0.diagnose_id c3,t1.nic_id c4,t1.nicoverzicht_omschrijving c5 from nic_diagnose t0 left outer join nicoverzicht t1 on t1.nic_id = t0.nic_id where diagnose_id = '491322316502'";
    	 
    	 SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
    	 
    	 // execute the query returning a List of MapBean objects
    	 List<SqlRow> list = sqlQuery.findList();
    	 
        return 
        		find
        		.fetch("nic", new FetchConfig().query())
        		.fetch("nic_overzicht", new FetchConfig().query())
        		.where()
                .eq("diagnose_id", filter)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

