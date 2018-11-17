package service;

import entity.User;
import util.PageBean;

public interface UserService {


	/** 验证登陆
	 * @param User 用户表
	 * @return 验证登陆结果(i：用户编号，0：用户名密码不匹配，-1：用户名不存在)
	 */
	int checklogin(String userCode, String userPassword);

	/** 根据用户编号查询用户信息
	 * @param id 用户编号
	 * @return 根据用户编号查询用户对象
	 */
	User getUserById(int id);

	int addUser(User user);

	/**
	 * 根据用户编号和之前密码修改 用户密码
	 * @param id 用户编号
	 * @param oldpassword 用户密码
	 * @param newpassword 新密码
	 * @return 修改结果：（-1：用户密码输入错误，0：修改失败，1：修改成功）
	 */
	int pwdModify(long id, String oldpassword, String newpassword);

    int findByUserCodeCount(String queryname);


	PageBean<User> getPageBeanUser(PageBean<User> pageBean, String queryname, int userRoleId);

	User getUserSingle(long id);

	int updUser(User user);

    String delUser(int id);

	String checkPwd(long id, String oldpassword);

    int getUserCount(String key, int userRoleId);
}
