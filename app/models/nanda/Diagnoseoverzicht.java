package models.nanda;

import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Page;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.fasterxml.jackson.annotation.JsonBackReference;

import controllers.VerpleegkundigeApplication;
import controllers.VerpleegkundigeApplication.nurseType;
import play.db.ebean.*;

/**
 * Company entity managed by Ebean
 */
@Entity 
public class Diagnoseoverzicht extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int diagnoseoverzicht_id;
    
	@ManyToOne
	@JoinColumn(name="diagnose_id")
	@JsonBackReference
	public Diagnose diagnose;
    
    @ManyToOne
    @JoinColumn(name="diagnoseversie_id")
    public Diagnoseversie diagnoseversie;
    
    @Column(nullable = true)
    public int diagnose_code;
    
    @ManyToOne
    @JoinColumn(name="gezondheidspatroon_id")
    public Gezondheidspatroon gezondheidspatroon;
    
    @ManyToOne
    @JoinColumn(name="diagnoseklasse_id")
    public Diagnoseklasse diagnoseklasse;
    
    public String diagnoseoverzicht_omschrijving;
    
    public String diagnoseoverzicht_definitie;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Diagnoseoverzicht> find = new Model.Finder<Long,Diagnoseoverzicht>(Long.class, Diagnoseoverzicht.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Diagnoseoverzicht c: Diagnoseoverzicht.find.orderBy("name").findList()) {
            options.put(c.diagnose.toString(), c.diagnoseoverzicht_definitie);
        }
        return options;
    }
    
    /**
     * Return a page of a diagnosis
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Diagnose property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Diagnoseoverzicht> page(int page, int pageSize, String sortBy, String order, String filter) {
		String sql = "select distinct t0.diagnoseoverzicht_id, t0.diagnose_code c0, t0.diagnoseoverzicht_omschrijving c1, t0.diagnoseoverzicht_definitie c2, t0.diagnoseversie_id c3, t0.gezondheidspatroon_id c4, t0.diagnoseklasse_id c5, t1.diagnose_id c6,	t2.gezondheidspatroon_omschrijving from diagnoseoverzicht t0 left outer join diagnose t1 on t1.diagnose_id = t0.diagnose_id left outer join gezondheidspatroon t2 on t2.gezondheidspatroon_id = t0.gezondheidspatroon_id";
		
		sql += VerpleegkundigeApplication.tokenizeQuery(filter, nurseType.NANDA);
		
		RawSql rawSql =   
			    RawSqlBuilder  
			        .parse(sql)  
			        .columnMapping("t0.diagnoseoverzicht_id",  "diagnoseoverzicht_id")  
			        .columnMapping("t0.diagnose_code",  "diagnose_code")  
			        .columnMapping("t0.diagnoseoverzicht_omschrijving",  "diagnoseoverzicht_omschrijving")  
			        .columnMapping("t0.diagnoseoverzicht_definitie",  "diagnoseoverzicht_definitie")  
			        .columnMapping("t0.diagnoseversie_id",  "diagnoseversie.diagnoseversie_id")  
			        .columnMapping("t0.gezondheidspatroon_id", "gezondheidspatroon.gezondheidspatroon_id")
			        .columnMapping("t0.diagnoseklasse_id", "diagnoseklasse.diagnoseklasse_id")
			        .columnMapping("t1.diagnose_id", "diagnose.diagnose_id")
			        .columnMapping("t2.gezondheidspatroon_omschrijving", "gezondheidspatroon.gezondheidspatroon_omschrijving")
			        .create();  
				
		Page<Diagnoseoverzicht> d = find
    			.setRawSql(rawSql)
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
		// Check if paged list is empty
		if(d.getList().size() > 0){
			return d;
		}
		else{
			//Fix to prevent persistence exception
	        return 
	                find.where()
	                	.eq("diagnoseoverzicht_omschrijving", "%" + filter + "%")
	                    .orderBy(sortBy + " " + order)
	                    .fetch("diagnose")
	                    .fetch("gezondheidspatroon")
	                    .findPagingList(pageSize)
	                    .setFetchAhead(false)
	                    .getPage(page);
		}
    }

}

