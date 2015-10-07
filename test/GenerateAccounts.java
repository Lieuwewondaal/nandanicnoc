import org.apache.commons.lang3.RandomStringUtils;

// Some code to generate a lot of accounts
public class GenerateAccounts {

	public static void main(String[] args) {
		int accounts=60;
		int baseid=100;
		String prefix="verpleegkundige_";
		
		String upList="";
		String sql="";
		
		for (int i=0; i<accounts; i++)
		{
			String username=prefix + Integer.toString(i+1);
			String password=RandomStringUtils.randomAlphanumeric(8);
			
			upList=upList+username+":"+password+"\n";
			sql=sql+"insert into gebruiker (gebruiker_id, gebruiker_naam, gebruiker_wachtwoord, gebruiker_admin) values (" + Integer.toString(baseid+i) + ", '" + username + "', '"+password+"', 0);\n";
		}
		System.out.println(upList);
		System.out.println("\n\n");
		System.out.println(sql);
	}

}
