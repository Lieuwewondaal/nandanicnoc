package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import play.db.ebean.*;
import play.data.format.Formats;
import play.data.validation.*;

/**
 * User entity managed by Ebean
 */
@Entity 
public class Gebruiker extends Model {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long gebruiker_id;
	
	@Constraints.Required
    public String gebruiker_naam;
    
	@Constraints.Required
    public String gebruiker_wachtwoord;
	
	public boolean gebruiker_admin;
	
	public static Model.Finder<Long,Gebruiker> find = new Model.Finder<Long,Gebruiker>(Long.class, Gebruiker.class);

	public static Gebruiker authenticate(String username, String password) {
		Gebruiker gebruiker = find.where().eq("gebruiker_naam", username).findUnique();
		if (gebruiker != null) {
			if (gebruiker.gebruiker_wachtwoord.equals(password)) {
				return gebruiker;
			}
		}
		return null;		
	}
    
}

