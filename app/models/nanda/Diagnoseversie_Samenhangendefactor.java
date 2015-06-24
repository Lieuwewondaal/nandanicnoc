package models.nanda;

import javax.persistence.*;

import play.db.ebean.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Diagnoseversie_Samenhangendefactor extends Model {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="samenhangendefactor_id")
    public Samenhangendefactor samenhangendefactor;
    
    @ManyToOne
    @JoinColumn(name="diagnoseversie_id")
    public Diagnoseversie diagnoseversie;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Diagnoseversie_Samenhangendefactor> find = new Model.Finder<Long,Diagnoseversie_Samenhangendefactor>(Long.class, Diagnoseversie_Samenhangendefactor.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie c: DiagnoseVersie.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

