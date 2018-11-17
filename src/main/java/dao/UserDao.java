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
     *  根据用户id 修改用户密码
     * @param id 用户id
     * @param newPwd 用户密码
     * @return 修改结果
     */
    int updPwd(@Param("id") long id, @Param("newPwd") String newPwd);

    /**
     *  根据 id 删除用户信息
     * @param id 用户编号
     * @return 删除结果
     */
    int delUser(int id);

    /**
     * 根据用户id和用户密码查询用户量
     * @param id 用户id
     * @param oldPwd 用户密码
     * @return 用户量
     */
    int findByUserPwd(@Param("id") long id, @Param("oldPwd") String oldPwd);

    /**
     * 根据用户编码和y用户角色编号 分页查询
     * @param pageBean 分页
     * @param queryname 用户编码
     * @param userRoleId 用户角色编号
     * @return 用户集合
     */
    List<User> getUser(@Param("pageBean") PageBean<User> pageBean, @Param("userCode") String queryname, @Param("userRoleId") int userRoleId);

    /**
     * 根据用户id查询用户信息
     * @param uid 用户id
     * @return 用户信息
     */
    User getUserSingle(@Param("id") long uid);

    /**
     *  根据用户id 修改用户信息
     * @param user  用户信息
     * @return 修改结果
     */
    int updUser(@Param("user") User user);

    /**
     * 根据用户id查询用户量
     * @param id 用户id
     * @return 用户量
     */
    int findByUserIdCount(int id);

    /**
     * 根据用户id和用户密码查询用户量
     * @param id 用户id
     * @param oldpassword 用户密码
     * @return 用户量
     */
    int checkPwd(@Param("id") long id, @Param("userPassword") String oldpassword);

    /**
     * 根据用户编码和用户角色id查询用户量
     * @param key 用户编码
     * @param userRoleId 用户角色id
     * @return 用户量
     */
    int getUserCount(@Param("key") String key,@Param("userRoleId") int userRoleId);
}
