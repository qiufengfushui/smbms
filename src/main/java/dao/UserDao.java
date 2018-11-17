package dao;

import entity.User;
import org.apache.ibatis.annotations.Param;
import util.PageBean;

import java.util.List;

public interface UserDao {

    /**根据 userCode 查询用户名数量
     * @param userCode 用户名
     * @return 根据 userCode 查询用户名数量
     */
    int findByUserCodeCount(@Param("userCode") String userCode);

    /**
     * 根据 userCode 和 userPassword 查询用户编号
     * @param userCode 用户编码
     * @param userPassword 用户密码
     * @return 用户编号( 返回值：null，用户编码和用户密码匹配不到 )
     */
    Integer getIdByuCodeAnduPwd(@Param("userCode") String userCode, @Param("userPassword") String userPassword);


    /**
     * 根据 id 查询这个用户的信息
     * @param id 用户编号
     * @return 用户对象
     */
    User getUserById(int id);


    /**
     *  新增 user
     * @param user 用户对象
     * @return 新增结果
     */
    int addUser(User user);

    /**
     * 修改 user 信息
     * @param user 用户对象
     * @return 修改结果
     */
    int updatePwd(@Param("id") long id, @Param("newPwd") String newPwd);

    /**
     *  根据 id 删除用户信息
     * @param id 用户编号
     * @return 删除结果
     */
    int delUser(int id);


    int findByUserPwd(@Param("id") long id, @Param("oldPwd") String oldPwd);

    List<User> findByUserAll(@Param("pageBean") PageBean<User> pageBean);

    int findByUserAllCount();

    List<User> findByUserCode(@Param("pageBean") PageBean<User> pageBean, @Param("userCode") String queryname);

    int findByUserRoleCount(@Param("userRoleId") int userRoleId);

    List<User> findByUserRole(@Param("pageBean") PageBean<User> pageBean, @Param("queryUserRole") int queryUserRole);

    List<User> getUser(@Param("pageBean") PageBean<User> pageBean, @Param("userCode") String queryname, @Param("userRoleId") int userRoleId);

    User getUserSingle(@Param("id") long uid);

    int findByUserRoleAndUserCodeCount(@Param("id") int userRoleId, @Param("userCode") String queryname);

    int updUser(@Param("user") User user);

    int findByUserIdCount(int id);

    int checkPwd(@Param("id") long id, @Param("userPassword") String oldpassword);
}
