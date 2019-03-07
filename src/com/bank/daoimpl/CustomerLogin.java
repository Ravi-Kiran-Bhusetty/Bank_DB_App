package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.bank.dao.Login;
import com.bank.driver.Driver;
import com.bank.model.UserData;
import com.bank.utility.DatabaseConnection;

public class CustomerLogin implements Login {
	Scanner sc = new Scanner(System.in);
	DatabaseConnection db = new DatabaseConnection();
	Connection connection = db.database();
	UserData u = new UserData();

	public UserData customerLogin() {

		System.out.println("Enter Account Number and Password");
		long acno = sc.nextLong();
		String pw = sc.next();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from bank_customer");

			int i = 0;
			while (rs.next()) {
				if (rs.getLong(1) == acno && rs.getString(2).equals(pw)) {
					u.setBalance(rs.getDouble(6));
					u.setAccountNo(acno);
					i++;
					break;
				}
			}

			if (i != 1) {
				System.out.println("Account no. or Password Incorrect. Press 1 to retry. any other to exit");
				int j = sc.nextInt();
				if (j == 1)
					customerLogin();
				else
					System.exit(0);
			}
			// connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;

	}
//	Driver d = new Driver();
//	public void deposit(double amt1) {
//		defaultBalance += amt1;
//		System.out.print("Remaining Balance: "+defaultBalance);
//		System.out.println();
//		try {
//			PreparedStatement stmt = connection.prepareStatement("update bank_customer set balance = ? where accountNo = ?");
//			stmt.setLong(2, defaultAccountNo);
//			stmt.setDouble(1, defaultBalance);
//			stmt.executeUpdate();
//			
//			System.out.println("Press 1 to continue. Any other to exit");
//			int i = sc.nextInt();
//			if (i == 1) {
//				d.operations();
//			} else {
//				System.exit(0);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	public void withdraw(double amt) {
//		
//		if(defaultBalance >= amt)
//		{
//			defaultBalance -= amt;
//			System.out.println("Remaining Balance: "+defaultBalance);
//			System.out.println();
//			try {
//				PreparedStatement stmt = connection.prepareStatement("update bank_customer set balance = ? where accountNo = ?");
//				stmt.setLong(2, defaultAccountNo);
//				stmt.setDouble(1, defaultBalance);
//				stmt.executeUpdate();
//				
//				System.out.println("Press 1 to continue. Any other to exit");
//				int i = sc.nextInt();
//				if (i == 1) {
//					d.operations();
//				} else {
//					System.exit(0);
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		else 
//			System.out.println("Insufficient Fund");
//	}

}
