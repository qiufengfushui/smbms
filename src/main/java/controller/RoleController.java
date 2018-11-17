package controller;

import com.alibaba.fastjson.JSON;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RoleService;

import javax.servlet.http.HttpSession;
@Controller
@RequestMapping("/role")
public class RoleController {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring.cfg.xml");
    RoleService service = (RoleService)context.getBean("roleService");

    @RequestMapping("/getRoleList.do")
    public String getRoleList(HttpSession session){
        session.setAttribute("userRoleItem",service.findByRoleList());
        return "useradd";
    }

    @ResponseBody
    @RequestMapping(value = "/getAjaxRoleList.do")
    public String getAjaxRoleList(){
        return JSON.toJSONString(service.findByRoleList());
    }

}
