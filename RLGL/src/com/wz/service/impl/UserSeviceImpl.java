package com.wz.service.impl;

import java.util.List;

import com.wz.dao.IUserDao;
import com.wz.dao.impl.UserDaoImpl;
import com.wz.domain.User;
import com.wz.service.IUserSevice;

public class UserSeviceImpl implements IUserSevice {
	
	private IUserDao dao = new  UserDaoImpl();

	@Override
	public User login(String logonName, String logonPwd) {
		// TODO Auto-generated method stub
		return dao.selectUserByInfo(logonName, logonPwd);
	}

	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		return dao.addUser(user);
	}

	@Override
	public int modifyUser(User user) {
		// TODO Auto-generated method stub
		return dao.updateUser(user);
	}

	@Override
	public int removeUser(Integer userID) {
		// TODO Auto-generated method stub
		return dao.deleteUser(userID);
	}

	@Override
	public User findUserById(Integer userID) {
		// TODO Auto-generated method stub
		return dao.selectUserById(userID);
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return dao.selectAllUser();
	}

	@Override
	public List<User> findUserByCondition(String userName, String gender,
			String education, String isUpload) {
		// TODO Auto-generated method stub
		return dao.selectUserByCondition(userName, gender, education, isUpload);
	}

}
