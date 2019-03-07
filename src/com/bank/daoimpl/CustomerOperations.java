package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.bank.dao.Operations;
import com.bank.driver.Driver;
import com.bank.model.UserData;
import com.bank.utility.DatabaseConnection;

public class CustomerOperations implements Operations {

	static Scanner sc = new Scanner(System.in);
	DatabaseConnection db = new DatabaseConnection();
	Connection connection = db.database();
	Driver d = new Driver();

	public UserData withdraw(double amt, UserData u) {
		double bal = u.getBalance();
		if (bal >= amt) {
			bal -= amt;
			System.out.println("Remaining Balance: " + bal);
			try {
				PreparedStatement stmt = connection
						.prepareStatement("update bank_customer set balance = ? where accountNo = ?");
				stmt.setLong(2, u.getAccountNo());
				stmt.setDouble(1, bal);
				stmt.executeUpdate();
				u.setBalance(bal);
				System.out.println("Press 1 to continue. Any other to exit");
				int i = sc.nextInt();
				if (i == 1) {
					d.operations(u);
				} else {
					System.exit(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else
			System.out.println("Insufficient Fund");
		return u;
	}

	public UserData deposit(double amt, UserData u) {

		double bal = u.getBalance();
		bal += amt;
		System.out.println("Remaining balance is: " + bal);
		try {
			PreparedStatement stmt = connection
					.prepareStatement("update bank_customer set balance = ? where accountNo = ?");
			stmt.setLong(2, u.getAccountNo());
			stmt.setDouble(1, bal);
			stmt.executeUpdate();
			u.setBalance(bal);
			System.out.println("Press 1 to continue. Any other to exit");
			int i = sc.nextInt();
			if (i == 1) {
				d.operations(u);
			} else {
				System.exit(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

}
