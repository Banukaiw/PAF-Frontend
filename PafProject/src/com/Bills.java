package com;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bills {

	private Connection connect() {
		
		
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electricityborad?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertBills(String accNo,String usage1, String vat, String value, String total) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into billservice(`billID`,`accNo`,`usage1`,`vat`,`value`,`total`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, accNo);
			preparedStmt.setString(3, usage1);
			preparedStmt.setString(4, vat);
			preparedStmt.setString(5, value);
			preparedStmt.setString(6, total);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newBills = readBills(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the bill data.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readBills() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			output = "Deleted successfully";
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>--BillID--</th><th>--Account No--</th><th>--Usage(Point)--</th><th>--Vat/Tax--</th><th>--Value (RS)--</th><th>--Total Amount (RS)--</th></tr>";
			
			String query = "select * from billservice";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String billID = Integer.toString(rs.getInt("billID"));
				String accNo = rs.getString("accNo");
				String usage1 = rs.getString("usage1");
				String vat = rs.getString("vat");
				String value = rs.getString("value");
				String total = rs.getString("total");
				

				// Add into the html table
				//output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate'type='hidden' value='" + billID+ "'>" + accNo + "</td>";
				output += "<tr><td>" + billID + "</td>";
				output += "<td>" + accNo + "</td>";
				output += "<td>" + usage1 + "</td>";
				output += "<td>" + vat + "</td>";
				output += "<td>" + value + "</td>";
				output += "<td>" + total + "</td>";
				
				
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-billID='" + billID + "'>" + "</td></tr>"; 
				
				
				
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
			
			} catch (Exception e) {
			output = "Error while reading the bill data.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	public String updateBills(String billID, String accNo, String usage1, String vat, String value, String total) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE billservice SET accNo=?,usage1=?,vat=?,value=?,total=?," + "WHERE billID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, accNo);
			preparedStmt.setString(2, usage1);
			preparedStmt.setString(3, vat);
			preparedStmt.setString(4, value);
			preparedStmt.setString(5, total);
			
			preparedStmt.setInt(6, Integer.parseInt(billID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newBills = readBills();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the bill data.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteBills(String billID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from billservice where billID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(billID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the bill data.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
