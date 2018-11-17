package controller;

import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;
import util.PageBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@RequestMapping("/user")
public class UserController {

    ApplicationContext context = new ClassPathXmlApplicationContext("spring.cfg.xml");
    UserService userService = (UserService)context.getBean("userService");


    @RequestMapping("/useradd.do")
    public String userAdd(){
        return "useradd";
    }

    //登陆
    @RequestMapping("/login.do")
    public String checkLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, User user){
        int result = userService.checklogin(user.getUserCode(),user.getUserPassword());
        if(result == -1){
            //此账号不存在
            session.setAttribute("error","此账号不存在！");
            return "redirect:../login.jsp";
        }else if(result == 0){
            //账号密码不匹配
            session.setAttribute("error","账号与密码不匹配！");
            return "redirect:../login.jsp";
        }else{
            //登陆成功
            user = userService.getUserById(result);
            session.setAttribute("userSession",user);
            return "frame";
        }
    }

    //验证旧密码
    @ResponseBody
    @RequestMapping("/checkPwd.do")
    public String checkPwd(HttpSession session, @RequestParam(value = "oldpassword",required = false)String oldpassword) {
        String result;
        if (oldpassword.equals(null) || oldpassword.equals("")){
            result = "error";
        }else if(session.getAttribute("userSession") == null){
            result = "sessionerror";
        }else{
            result = userService.checkPwd(((User) session.getAttribute("userSession")).getId(),oldpassword);
        }
        return "{\"result\": \""+result+"\"}";
    }

    //展示用户视图
    @RequestMapping("/management.do")
    public String management(HttpSession session, @RequestParam(value = "pageIndex",required = false)String pageIndex, @RequestParam(value = "queryname",required = false)String queryname, @RequestParam(value = "queryUserRole",required = false) String queryUserRole){
        PageBean pageBean;
        //表示一页展示9条数据
        int pageSize = 9;
        int pageNum = pageIndex==null?1:Integer.valueOf(pageIndex);
        String key = queryname == null ? "" : queryname;

        int userRoleId = queryUserRole==null?0:Integer.valueOf(queryUserRole);

        int totalRecord = userService.getUserCount(key,userRoleId);

        pageBean = new PageBean(pageNum,pageSize,totalRecord);
        pageBean =  userService.getPageBeanUser(pageBean,key,userRoleId);
        session.setAttribute("userList",pageBean.getList());
        session.setAttribute("pageBean",pageBean);
        session.setAttribute("queryUserName",key);
        session.setAttribute("queryUserRole",userRoleId);
        return "userlist";
    }

    @RequestMapping(value = "/pwdmodify.do",method = RequestMethod.GET)
    public String pwdModity(){
        return "pwdmodify";
    }

    @RequestMapping(value = "/pwdmodify.do",method = RequestMethod.POST)
    public  String pwdModify(HttpSession session, String oldpassword, String newpassword, String reNewPassword){
        if (!newpassword.equals(reNewPassword)){
            session.setAttribute("message","两次输入密码不一致！");
            return "pwdmodify";
        }
        User user = (User)session.getAttribute("userSession");
        int result = userService.pwdModify(user.getId(),oldpassword,newpassword);
        if (result == -1){
            session.setAttribute("message","用户密码输入错误！");
            return "pwdmodify";
        }else if(result == 1){
            session.setAttribute("message","密码修改成功！");
            return "pwdmodify";
        }else{
            session.setAttribute("message","密码修改失败，请联系管理员！");
            return "pwdmodify";
        }
    }
    @RequestMapping("/view.do")
    public String view(HttpSession session, @RequestParam("uid") long id){
        User user = userService.getUserSingle(id);
        session.setAttribute("user",user);
        return "userview";
    }

    @RequestMapping("/modify.do")
    public String modify(HttpSession session, @RequestParam("uid")int id){
        User user = userService.getUserSingle(id);
        session.setAttribute("user",user);
        return "usermodify";
    }

    @RequestMapping(value = "/sava.do",produces = "text/html;charset=utf-8;")
    public void modify(HttpServletResponse response, HttpSession session, User user) {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        long modifyBy = ((User)session.getAttribute("userSession")).getId();
        user.setModifyBy(modifyBy);
        int result = userService.updUser(user);
        try {
            if (result == 1){
                response.getWriter().print("<script>alert('保存成功！');location.href='../user/management.do'</script>");
            }else{
                response.getWriter().print("<script>alert('保存失败，请联系管理员！');history.back(-1);</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/del.do")
    public void del(HttpServletResponse response, int id){
        try {
            response.getWriter().print(userService.delUser(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @ResponseBody
    @RequestMapping("/findByUserCode.do")
    public String findByUserCode(@RequestParam(value = "userCode",required = false) String userCode){
        if(null == userCode || "".equals(userCode)){
            return "{\"result\": \"error\"}";
        }
        int result = userService.findByUserCodeCount(userCode);
        if(result == 1){
            return "{\"result\": \"exist\"}";
        }else {
            return  "{\"result\": \"true\"}";
        }
    }
    @RequestMapping("/add.do")
    public void add(HttpSession session, HttpServletResponse response, User user, String ruserPassword){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        if(!user.getUserPassword().equals(ruserPassword)) {
            try {
                response.getWriter().print("<script>alert('两次输入密码不匹配！');history.back(-1);</script>");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long createdBy = ((User)session.getAttribute("userSession")).getId();
        user.setCreatedBy(createdBy);
        int result = userService.addUser(user);
        try {
            if(result > 0){
                response.getWriter().print("<script>alert('保存成功！');location.href='../user/management.do'</script>");
            }else {
                response.getWriter().print("<script>alert('添加失败，请联系管理员！');history.back(-1);</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/exit.do")
    public String exit(HttpServletRequest req){
        req.getSession().removeAttribute("userSession");
        return "redirect:../login.jsp";
    }

}
