package com.wz.dao;

import java.util.List;

import com.wz.domain.User;

public interface IUserDao {
	
	User selectUserByInfo(String logonName,String logonPwd);
	
	int addUser(User user);
	
	int updateUser(User user);
	
	int deleteUser(Integer userID);
	
	User selectUserById(Integer userID);
	
	List<User> selectAllUser();
	
	List<User> selectUserByCondition(String userName, String gender,String education, String isUpload);
	
}
