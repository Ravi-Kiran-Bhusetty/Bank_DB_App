package com.bank.dao;

import com.bank.model.UserData;

public interface Operations {

	public UserData withdraw(double amt, UserData u);

	public UserData deposit(double amt, UserData u);
}
