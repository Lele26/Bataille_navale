package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class Model {

	public ResultSet  bddConnection(String query) {
		ResultSet rs = null;
		try {
			Connection con= DriverManager.getConnection("jdbc:oracle:thin:@iutdoua-oracle.univ-lyon1.fr:1521:orcl","p1700225","294223");
			Statement stmt=con.createStatement();
			rs = stmt.executeQuery(query);
			 
			}catch(Exception e){ System.out.println(e);}


		return rs;
		
	}
	
	
	public void AddUser(UserNew user)
	{
		ResultSet rs;
		String query = "INSERT INTO utilisateur (login, password, mail) VALUES ('"+user.login+"', '"+user.password+"', '"+user.email+"')";
		rs = bddConnection(query);
	}
	
	
	}