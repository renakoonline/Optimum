package com.optimum.dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.optimum.pojo.User;

public interface UserDAO {

	String register(User refUser, InputStream inputStream);

	String Login(User refUser);

	public String updateProfile(User refUser);

	public byte[] getPic(String email);

	String DLocker(User refUser);

	
}
