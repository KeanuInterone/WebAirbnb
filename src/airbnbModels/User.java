package airbnbModels;

import java.io.Serializable;
import java.sql.*;


public class User implements Serializable {
	
	public String name;
	public String username;
	public String password;
	public String address;
	public String phone;
	
	public User(String name, String username, String password, int type, String address, String phone)
	{
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phone = phone;
	}
	
	public User(String _username, String _password) {
		username = _username;
		password = _password;
	}
	
	public static User AuthenticateUser(String username, String password)
	{
		Connection con = DBConnection.getConnection();
		System.out.println("connected to the database");
	 	if(con == null) {
			return null;
		}
		
		String query;
        query = "SELECT * FROM Users WHERE Login = ?;";
        ResultSet rs = null;
        
        try {
            // set up query
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, username);
            rs = preparedStmt.executeQuery();
            
            // get the password
            rs.next();
            String dbPassword = rs.getString("Password");
            
            // check validity
            if (password.equals(dbPassword)) {
                String dbName = rs.getString("Name");
                String dbLogin = rs.getString("Login");
                int dbType = rs.getInt("userType");
                String dbAddress = rs.getString("Address");
                String dbPhoneNumebr = rs.getString("PhoneNumber");
                User user = new User(dbName, dbLogin, dbPassword, dbType, dbAddress, dbPhoneNumebr);
                return user;
            }
            else
                return null;
            
        } catch(Exception e) {
            System.err.println("Unable to execute query:"+query+"\n");
            System.err.println(e.getMessage());
            return null;
        }
	}
	
	public static User InsertUser(String Name, String Login, int type, String Password, String Address, String PhoneNumber)
    {
		Connection con = DBConnection.getConnection();
		System.out.println("connected to the database");
	 	if(con == null) {
			return null;
		}
		
        //INSERT INTO Users (Name, Login, userType, Password, Address, PhoneNumber) VALUES ('Joey Despain','Jdlog','0','password','123 street','5556666');
        String query;
        
        query= "INSERT INTO Users (Name, Login, userType, Password, Address, PhoneNumber) "+"VALUES (?, ?, ?, ?, ?,?)";
        
        try{
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, Name);
            preparedStmt.setString (2, Login);
            preparedStmt.setInt(3, type);
            preparedStmt.setString(4, Password);
            preparedStmt.setString(5, Address);
            preparedStmt.setString(6, PhoneNumber);
            preparedStmt.execute();
            User user = new User(Name, Login, Password, type, Address, PhoneNumber);
            return user;
            
        } catch(Exception e) {
            System.err.println("Unable to execute query:"+query+"\n");
            System.err.println(e.getMessage());
            return null;
        }
    }

	
	public String toString() {
		return "username: " + username + " password: " + password;
	}
}
