package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class UserController {
	
	Model model = new Model();
	final String secretKey = "secret";
	public UserController() 
	{
		
	}
	
		
	
		//Verifie si l'utilisateur existe, revoie un objet UserCheck 
		public boolean CheckUser(UserCon user) throws SQLException {
			UserCon conUser = new UserCon(); 
			conUser.login=user.login;
			conUser.password=user.password;
			
			String username="";
			
			ResultSet rs= null;
			//String password = AES.encrypt(user.password, secretKey);
			//System.out.print(password+"ici");
			String query = "SELECT login FROM utilisateur WHERE login='"+user.login+"' AND password='"+user.password+"' ";
			System.out.print(query);
			rs = model.bddConnection(query);
			try {
				while(rs.next()) {
				username = rs.getString(1);
				System.out.print(username);
				}		} catch (SQLException e) {
				e.printStackTrace();
			} 
		if(Objects.equals(username,user.login)) 
		{
			System.out.print("can connect");
			conUser.canConnect=true;
			return conUser.canConnect;
		}else {
			System.out.print("can not connect");
			conUser.canConnect=false;
			return conUser.canConnect;
		}
		}	
	
		// Ajoute un utilisateur
		public String UserAdd(UserNew user)
		{
			UserNew newUser = new UserNew();
			newUser.email=user.email;
			newUser.login = user.login;
			newUser.password=user.password;
			String queryLogin = "SELECT login FROM utilisateur WHERE login='"+user.login+"'";
			String queryEmail = "SELECT mail FROM utilisateur WHERE mail='"+user.email+"' ";
			ResultSet rsLogin= null;
			ResultSet rsEmail= null;
			rsLogin = model.bddConnection(queryLogin);
			rsEmail = model.bddConnection(queryEmail);
			
			
			try {
				if(!rsLogin.next() && !rsEmail.next()) // !rs.next() renvoie true si la requete est vide
				{
					 //Ajoute k'utilisateur s'il n'hexiste pas
					System.out.print("add user");
					newUser.done=true;
					newUser.result="inscrit";
					model.AddUser(newUser);
					System.out.println(newUser.result);
					System.out.println(newUser.done);
					return newUser.result;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			};
			
			
			try {
				if(!rsLogin.next() && rsEmail.next()) {
					System.out.print("email existe deja");
					newUser.done = false;
					newUser.result="pas inscrit";
					System.out.println(newUser.result);
					System.out.print(newUser.done);
					return newUser.result;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			
			
			try {
				if(rsLogin.next() && !rsEmail.next()) {
					System.out.print("login existe deja");
					newUser.done = false;
					newUser.result="pas inscrit";
					System.out.println(newUser.result);
					System.out.print(newUser.done);
					return newUser.result;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		
}
