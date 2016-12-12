package com.wz.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.TokenHelper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.wz.domain.User;
import com.wz.service.IUserSevice;
import com.wz.service.impl.UserSeviceImpl;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	private IUserSevice service = new UserSeviceImpl();

	private User user = new User();

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	//-------------多条件查询------------------
	private String isUpload;
	public String findUserByCondition() throws Exception {
		//根据条件查询用户，特殊条件为isupload
		users = service.findUserByCondition(user.getUserName(), user.getGender(), user.getEducation(), isUpload);
		return SUCCESS;
	}
	
	// ------------编辑用户--------------
	public String edit() throws Exception {
		// 1.判断用户是否从新选择了文件
		if (upload == null) {
			// 没有选择文件，就用原来的
			User dbUser = service.findUserById(user.getUserID());
			// 由于没有选择文件。user模型中的filename和path属性都是null，我们需要用查出来的用户里面的值替换
			user.setFilename(dbUser.getFilename());
			user.setPath(dbUser.getPath());
			int res = service.modifyUser(user);
			if (res > 0) {
				return SUCCESS;
			} else {
				// 用户从新选择了文件
				// 1.文件保存的路径
				String filePath = ServletActionContext.getServletContext()
						.getRealPath("/files");
				String dir = generateChildPath(filePath);
				// 2生成带有随机性的文件名
				String fileName = TokenHelper.generateGUID() + "_"
						+ uploadFileName;
				// 3.把user模型中缺少的属性，填充进去
				user.setPath(dir);
				user.setFilename(fileName);
				// 4.上传文件操作
				upload.renameTo(new File(fileName + File.separator + dir,
						fileName));
				// 5.保存用户
				int re = service.modifyUser(user);
				if (re > 0) {
					return SUCCESS;
				}
			}
		}
		return null;
	}

	// -------------显示编辑页面的动作方法-------------
	public String editUI() throws Exception {
		// 根据userID获取user对象
		user = service.findUserById(user.getUserID());
		// 把user对象压入栈顶
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.push(user);
		return SUCCESS;
	}

	// ------------删除用户-----------------
	public String delete() throws Exception {
		int res = service.removeUser(user.getUserID());
		if (res > 0) {
			return SUCCESS;
		}
		return null;
	}

	// ------------文件下载-----------------
	private InputStream inputStream;
	private String oldFileName;

	public String download() throws Exception {
		// 1.获取用户的信息
		User dbUser = service.findUserById(user.getUserID());
		// 2.文件存放的路径
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"/files");
		// 给原始文件名赋值
		oldFileName = dbUser.getFilename().substring(
				dbUser.getFilename().indexOf("_") + 1);
		// 3.给字节输入流赋值
		inputStream = new FileInputStream(filePath + File.separator
				+ dbUser.getPath() + File.separator + dbUser.getFilename());
		// 4.返回成功
		return SUCCESS;
		// 5.剩下的交给stream类型的结果视图
	}

	// -------------查看用户详细信息------------------
	public String findUserById() throws Exception {
		user = service.findUserById(user.getUserID());
		// 把user对象压入栈顶
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.push(user);
		return SUCCESS;
	}

	// ----------查询所有用户-----------------
	// 用于存放查询出来的所用用户
	private List<User> users;

	public String findAll() throws Exception {
		users = service.findAllUser();
		System.out.println("----------------");
		return SUCCESS;

	}

	// --------用户添加-----------
	// 保存文件的file
	private File upload;
	// 文件名
	private String uploadFileName;

	public String add() throws Exception {
		// 1.文件保存的路径
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"/files");
		String dir = generateChildPath(filePath);
		// 2.生成带有随机性的文件名
		String fileName = TokenHelper.generateGUID() + "_" + uploadFileName;
		// 3.把user模型中缺少的属性，填充进去
		user.setPath(dir);
		user.setFilename(fileName);
		// 4.上传文件操作
		upload.renameTo(new File(filePath + File.separator + dir, fileName));
		// 5保存用户
		int res = service.saveUser(user);
		if (res > 0) {
			return SUCCESS;
		}
		return null;
	}

	// 生成yyyy-MM-dd的格式名称文件夹
	private String generateChildPath(String filePath) {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dir = format.format(date);
		File file = new File(filePath, dir);
		if (!file.exists()) {
			file.mkdirs();
		}

		return dir;
	}

	// --------用户登录的动作方法-----------

	public String login() throws Exception {
		User dbUser = service.login(user.getLogonName(), user.getLogonPwd());
		if (dbUser == null) {
			addActionError("用户名不存在或密码不正确!");
			return "input";
		}

		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("user", dbUser);
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getOldFileName() {
		return oldFileName;
	}

	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}

	public String getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}

	
}
