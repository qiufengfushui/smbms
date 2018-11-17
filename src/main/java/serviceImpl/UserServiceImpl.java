package serviceImpl;

import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;
import util.PageBean;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Override
	public int checklogin(String userCode,String userPassword) {
		// TODO Auto-generated method stub
		int count = userDao.findByUserCodeCount(userCode);
		if(count == 0) {
			return -1;
		}
		Integer id = userDao.getIdByuCodeAnduPwd(userCode,userPassword);
		if(id == null) {
			return 0;
		}
		return id;
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}

	@Override
	public int pwdModify(long id,String oldPwd, String newPwd) {
		int result = userDao.findByUserPwd(id,oldPwd);
		if (result < 1){
			return -1;
		}
		return userDao.updatePwd(id,newPwd);
	}

	@Override
	public PageBean<User> findByUserAll(PageBean<User> pageBean) {
		pageBean.setList(userDao.findByUserAll(pageBean));
		return pageBean;
	}

	@Override
	public int findByUserAllCount() {
		return userDao.findByUserAllCount();
	}

	@Override
	public int findByUserCodeCount(String queryname) {
		return userDao.findByUserCodeCount(queryname);
	}

	@Override
	public PageBean<User> findByUserCode(PageBean<User> pageBean, String queryname) {
		pageBean.setList(userDao.findByUserCode(pageBean,queryname));
		return pageBean;
	}

	@Override
	public int findByUserRoleCount(int queryUserRole) {
		return userDao.findByUserRoleCount(queryUserRole);
	}

	@Override
	public PageBean<User> findByUserRole(PageBean<User> pageBean, int queryUserRole) {
		pageBean.setList(userDao.findByUserRole(pageBean,queryUserRole));
		return pageBean;
	}

	@Override
	public PageBean<User> getPageBeanUser(PageBean<User> pageBean, String queryname, int userRoleId) {
		pageBean.setList(userDao.getUser(pageBean,queryname,userRoleId));
		return pageBean;
	}

	@Override
	public User getUserSingle(long id) {
		return userDao.getUserSingle(id);
	}

	@Override
	public int findByUserRoleAndUserCodeCount(int userRoleId, String queryname) {
		return userDao.findByUserRoleAndUserCodeCount(userRoleId,queryname);
	}

	@Override
	public int updUser(User user) {
		return userDao.updUser(user);
	}

	@Override
	public String delUser(int id) {
		int num = 0;
		num = userDao.findByUserIdCount(id);
		System.out.println(num);
		if(num == 0){
			return "notexist";
		}
		num = userDao.delUser(id);
		if(num == 0){
			return "false";
		}
		return "true";
	}

	@Override
	public String checkPwd(long id, String oldpassword) {
		int result = userDao.checkPwd(id,oldpassword);
		return result == 1?"true":"false";
	}

	@Override
	public int checkUserCode(String userCode) {
		// TODO Auto-generated method stub
		return userDao.findByUserCodeCount(userCode);
	}

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}


}
