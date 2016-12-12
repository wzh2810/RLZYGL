package com.wz.service;

import java.util.List;

import com.wz.domain.User;

/**
 * 用户操作相关的业务层接口
 * @author zhen
 *
 */
public interface IUserSevice {

	/**
	 * 用户登录
	 * @param logonName
	 * @param logonPwd
	 * @return
	 */
	User login(String logonName,String logonPwd);
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	int saveUser(User user);
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	int modifyUser(User user);
	
	/**
	 * 根据用户ID，删除用户
	 * @param userID
	 * @return
	 */
	int removeUser(Integer userID);
	
	/**
	 * 根据用户ID，获取用户信息
	 * @param userID
	 * @return
	 */
	User findUserById(Integer userID);
	
	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> findAllUser();
	
	/***
	 * 根据条件查询用户信息
	 * @param userName
	 * @param gender
	 * @param education
	 * @param isUpload
	 * @return
	 */
	List<User> findUserByCondition(String userName,String gender,String education,String isUpload);
}





















