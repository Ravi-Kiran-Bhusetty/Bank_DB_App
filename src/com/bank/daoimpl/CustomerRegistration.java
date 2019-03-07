package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import com.bank.dao.Registration;
import com.bank.model.UserData;
import com.bank.utility.DatabaseConnection;

public class CustomerRegistration implements Registration {
	Scanner sc = new Scanner(System.in);
	CustomerLogin cl = new CustomerLogin();
	DatabaseConnection db = new DatabaseConnection();
	Connection connection = db.database();
	UserData u = new UserData();

	public void customerRegistration() {

		setInfo();
		CustomerLogin cl = new CustomerLogin();

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("insert into bank_customer values(?,?,?,?,?,?,?,?,?,?)");

			preparedStatement.setLong(1, u.getAccountNo());
			preparedStatement.setString(2, u.getPassword());
			preparedStatement.setString(3, u.getFirstName());
			preparedStatement.setString(4, u.getLastName());
			preparedStatement.setString(5, u.getAccountType());
			preparedStatement.setDouble(6, u.getBalance());
			preparedStatement.setLong(7, u.getAadhaarNo());
			preparedStatement.setString(8, u.getPanCardNo());
			preparedStatement.setLong(9, u.getPhoneNo());
			preparedStatement.setString(10, u.getAddress());

			preparedStatement.executeUpdate();
			System.out.println("Registration Successful");
			System.out.println("Account number is: " + u.getAccountNo());
			// connection.close();
			cl.customerLogin();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setInfo() {
		long accno = 10000 + new Random().nextInt(99999);
		u.setAccountNo(accno);
		System.out.println("Register a new password");
		u.setPassword(sc.next());
		System.out.println("Enter first name and last name");
		u.setFirstName(sc.next());
		u.setLastName(sc.next());
		System.out.println("Enter account type");
		u.setAccountType(sc.next());
		u.setBalance(0.0);
		System.out.println("Enter aadhaar card number");
		u.setAadhaarNo(sc.nextLong());
		System.out.println("Enter pan card number");
		u.setPanCardNo(sc.next());
		System.out.println("Enter mobile number");
		u.setPhoneNo(sc.nextLong());
		System.out.println("Enter the address");
		u.setAddress(sc.next());
		int flag = 0;
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from bank_customer");

			while (rs.next()) {
				if (rs.getLong(7) == u.getAadhaarNo())
					flag++;
			}
			if (flag == 1) {
				System.out.println("Customer already Existing. Press 1 to login. Any other to exit");
				int i = sc.nextInt();
				if (i == 1)
					cl.customerLogin();
				else
					System.exit(0);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
