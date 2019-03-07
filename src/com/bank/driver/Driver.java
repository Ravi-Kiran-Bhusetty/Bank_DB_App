package com.bank.driver;

import java.util.Scanner;

import com.bank.dao.Login;
import com.bank.dao.Operations;
import com.bank.dao.Registration;
import com.bank.daoimpl.CustomerLogin;
import com.bank.daoimpl.CustomerOperations;
import com.bank.daoimpl.CustomerRegistration;
import com.bank.model.UserData;

public class Driver {

	static Scanner sc = new Scanner(System.in);
	static Login lg = new CustomerLogin();
	static UserData u = new UserData();
	static Operations o = new CustomerOperations();

	public static void main(String[] args) {

		System.out.println("Enter 1 for Registration. 2 for Login.");
		int menu = sc.nextInt();
		switch (menu) {
		case 1:
			Registration reg = new CustomerRegistration();
			reg.customerRegistration();
			break;

		case 2:
			u = lg.customerLogin();
			operations(u);
			break;

		default:
			System.out.println("Invalid Choice");
			break;
		}
	}

	public static void operations(UserData u) {

		System.out.println("Choose required operation. 1. Withdrawal 2. Deposit 3. Exit");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Enter withdrawal amount");
			double amt = sc.nextDouble();
			o.withdraw(amt, u);
			break;
		case 2:
			System.out.println("Enter deposit amount");
			double amt1 = sc.nextDouble();
			o.deposit(amt1, u);
			break;
		case 3:
			System.exit(0);
		default:
			break;
		}
	}
}
