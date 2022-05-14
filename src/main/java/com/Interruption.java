package com;

import java.sql.*;

public class Interruption {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/eletrogrid", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertInterruption(String time, String no_of_crew, String vehicle_no, String phone_no, String province) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into interupationdb(`intID`,`time`,`no_of_crew`,`vehicle_no`,`phone_no`,`province`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, time);
			preparedStmt.setInt(3, Integer.parseInt(no_of_crew));
			preparedStmt.setString(4, vehicle_no);
			preparedStmt.setInt(5, Integer.parseInt(phone_no));
			preparedStmt.setString(6, province);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newInterruption = readInterruption();
			output = "{\"status\":\"success\", \"data\": \"" + newInterruption + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the interruption details.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readInterruption() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<div class='w-100'><table class='table table-striped w-100 text-white'><tr><th>Time</th>" + "<th>No of Crew</th><th>Vehicle No</th>"
					+ "<th>Phone No</th>" + "<th>Province</th>"+ "<th>Update</th><th>Remove</th></tr></div>";
			String query = "select * from interupationdb";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String intID = Integer.toString(rs.getInt("intID"));
				String time = rs.getString("time");
				String no_of_crew = Integer.toString(rs.getInt("no_of_crew"));
				String vehicle_no = rs.getString("vehicle_no");
				String phone_no =Integer.toString(rs.getInt("phone_no"));
				String province = rs.getString("province");

				// Add into the html table
				output += "<tr><td>" + time + "</td>";
				output += "<td>" + no_of_crew + "</td>";
				output += "<td>" + vehicle_no + "</td>";
				output += "<td>" + phone_no + "</td>";
				output += "<td>" + province + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-info' data-itemid='" + intID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-itemid='" + intID + "'></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the interruption details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateInterruption(String intID, String time, String no_of_crew, String vehicle_no, String phone_no, String province) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE interupationdb SET time=?,no_of_crew=?,vehicle_no=?,phone_no=?,province=? WHERE intID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, time);
			preparedStmt.setInt(2,Integer.parseInt(no_of_crew));
			preparedStmt.setString(3, vehicle_no);
			preparedStmt.setInt(4,Integer.parseInt(phone_no));
			preparedStmt.setString(5, province);
			preparedStmt.setInt(6, Integer.parseInt(intID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newInterruption = readInterruption();
			output = "{\"status\":\"success\", \"data\": \"" + newInterruption + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteInterruption(String intID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from interupationdb where intID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(intID));
	
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newInterruption = readInterruption();
			output = "{\"status\":\"success\", \"data\": \"" + newInterruption + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the interruption details\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
