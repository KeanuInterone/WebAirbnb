package airbnbModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class House {
	
	public String category;
	public String name;
	public String address;
	public String url;
	public String phone;
	public String year;
	public String ownerUsername;
	
	public House(String category, String name, String address, String url, String phone, String year, String ownerUsername)
	{
		this.category = category;
		this.name = name;
		this.address = address;
		this.url = url;
		this.phone = phone;
		this.year = year;
		this.ownerUsername = ownerUsername;
	}
	
	public static House InsertHouse(String Category, String Name, String Address, String URL, String PhoneNumber, String Year, String UserLogin)
    {
		Connection con = DBConnection.getConnection();
		System.out.println("connected to the database");
	 	if(con == null) {
			return null;
		}

	 	String query;
		query= "INSERT INTO THs (Category, Name, Address, URL, PhoneNumber, Year, Login) "+"VALUES (?, ?, ?, ?, ?,?,?);";
		
		try{
			  PreparedStatement preparedStmt = con.prepareStatement(query);
			  preparedStmt.setString (1, Category);
		      preparedStmt.setString (2, Name);
		      preparedStmt.setString (3, Address);
		      preparedStmt.setString(4, URL);
		      preparedStmt.setString(5, PhoneNumber);
		      preparedStmt.setString(6, Year);
		      preparedStmt.setString(7, UserLogin);
		      preparedStmt.execute();
		      House house = new House(Category, Name, Address, URL, PhoneNumber, Year, UserLogin);
		      return house;
			 
        } catch(Exception e) {
        	System.out.println("unable to insert house");
			System.err.println("Unable to execute query:"+query+"\n");
	        System.err.println(e.getMessage());
			return null;
		}
    }
	
	public static House GetHouseWithID(int ID)
	{
		Connection con = DBConnection.getConnection();
		System.out.println("connected to the database");
	 	if(con == null) {
			return null;
		}
	 	
	 	String query;
		query= "SELECT * FROM THs h WHERE h.hid = ?";
		ResultSet rs = null;
		
		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt (1, ID);
			rs = preparedStmt.executeQuery();
			
			rs.next();
	        String dbCategory = rs.getString("Category");
	        String dbName = rs.getString("Name");
	        String dbAddress = rs.getString("Address");
	        String dbURL = rs.getString("URL");
	        String dbPhoneNumber = rs.getString("PhoneNumber");
	        String dbYear = rs.getString("Year");
	        String dbOwnerLogin = rs.getString("Login");
	         
	        House house = new House(dbCategory, dbName, dbAddress, dbURL, dbPhoneNumber, dbYear, dbOwnerLogin);
		    return house;
		}
		catch (Exception e) {
			System.out.println("unable to get house");
			System.err.println("Unable to execute query:"+query+"\n");
	        System.err.println(e.getMessage());
			return null;
		}	
	}
	
	// havent tried this out yet
	public static ArrayList<House> GetHousesOwnedBy(String ownerUsername)
	{
		Connection con = DBConnection.getConnection();
		System.out.println("connected to the database");
	 	if(con == null) {
			return null;
		}
	 	
	 	String query;
		query= "SELECT * FROM THs h WHERE h.Login = ?";
		ResultSet rs = null;
		ArrayList<House> houseArray = new ArrayList<House>();
		
		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString (1, ownerUsername);
			rs = preparedStmt.executeQuery();
			
			while(rs.next()) 
			{
				String dbCategory = rs.getString("Category");
		        String dbName = rs.getString("Name");
		        String dbAddress = rs.getString("Address");
		        String dbURL = rs.getString("URL");
		        String dbPhoneNumber = rs.getString("PhoneNumber");
		        String dbYear = rs.getString("Year");
		        String dbOwnerLogin = rs.getString("Login");
		         
		        House house = new House(dbCategory, dbName, dbAddress, dbURL, dbPhoneNumber, dbYear, dbOwnerLogin);
			    houseArray.add(house);
			}
	        
			return houseArray;
		}
		catch (Exception e) {
			System.out.println("unable to get house");
			System.err.println("Unable to execute query:"+query+"\n");
	        System.err.println(e.getMessage());
			return null;
		}	
		
		
	}

}
