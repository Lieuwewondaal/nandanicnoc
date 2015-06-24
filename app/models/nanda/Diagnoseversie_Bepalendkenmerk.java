package models.nanda;

import javax.persistence.*;

import play.db.ebean.*;

/**
 * Company entity managed by Ebean
 */
@Entity 
public class Diagnoseversie_Bepalendkenmerk extends Model {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="bepalendkenmerk_id")
    public Bepalendkenmerk bepalendkenmerk;
    
    @ManyToOne
    @JoinColumn(name="diagnoseversie_id")
    public Diagnoseversie diagnoseversie;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Diagnoseversie_Bepalendkenmerk> find = new Model.Finder<Long,Diagnoseversie_Bepalendkenmerk>(Long.class, Diagnoseversie_Bepalendkenmerk.class);

    /*public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(DiagnoseVersie c: DiagnoseVersie.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

