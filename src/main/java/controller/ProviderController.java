package controller;

import com.alibaba.fastjson.JSON;
import entity.Provider;
import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ProviderService;
import util.PageBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/provider")
public class ProviderController {

    ApplicationContext context = new ClassPathXmlApplicationContext("spring.cfg.xml");
    ProviderService service = (ProviderService)context.getBean("providerService");

    @RequestMapping("/getProviderNameAll.do")
    public String getProviderNameAll(HttpSession session){
        session.setAttribute("providerList",service.getAllProvider());
        return "billadd";
    }

    @ResponseBody
    @RequestMapping("/getProList.do")
    public String getProList(){
        List<Provider> list = service.getAllProvider();
        return JSON.toJSONString(list);
    }

    @RequestMapping(value = "/management.do")
    public String management(HttpServletRequest request,HttpSession session, @RequestParam(value = "pageIndex",required = false)String pageIndex, @RequestParam(value = "queryProCode",required = false) String proCode, @RequestParam(value = "queryProName",required = false) String proName) throws UnsupportedEncodingException {
        PageBean pageBean;
        //表示一页展示9条数据
        int pageSize = 9;
        int pageNum = pageIndex==null?1:Integer.valueOf(pageIndex);
        proName = proName==null?"":new String(proName.getBytes("ISO-8859-1"),"utf-8");
        proCode = proCode==null?"":proCode;

        int totalRecord = service.getProviderCount(proName,proCode);

        pageBean = new PageBean(pageNum,pageSize,totalRecord);
        pageBean = service.findByProvider(pageBean,proName,proCode);
        session.setAttribute("pageBean",pageBean);
        session.setAttribute("providerList",pageBean.getList());
        session.setAttribute("proName",proName);
        session.setAttribute("proCode",proCode);
        return "providerlist";
    }
    @RequestMapping("provideradd.do")
    public String providerAdd(){
        return "provideradd";
    }
    @RequestMapping("add.do")
    public void add(HttpSession session, HttpServletResponse response, Provider provider){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        long createdBy = ((User)session.getAttribute("userSession")).getId();
        provider.setCreatedBy(createdBy);
        int result = service.addProvider(provider);
        try {
            if(result > 0){
                response.getWriter().print("<script>alert('保存成功！');location.href='../provider/management.do'</script>");
            }else {
                response.getWriter().print("<script>alert('添加失败，请联系管理员！');history.back(-1);</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("view.do")
    public String view(HttpSession session, @RequestParam("proid") long id){
        Provider provider = service.getProvider(id);
        session.setAttribute("provider",provider);
        return "providerview";
    }
    @RequestMapping("modify.do")
    public String modify(HttpSession session, @RequestParam("proid")long id){
        Provider provider = service.getProvider(id);
        session.setAttribute("provider",provider);
        return "providermodify";
    }
    @RequestMapping("del.do")
    public void del(HttpServletResponse response, @RequestParam("proid")long id){
        try {
            response.getWriter().print(service.delProvider(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("sava.do")
    public void sava(HttpServletResponse response, HttpSession session, Provider provider){
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        long modifyBy = ((User)session.getAttribute("userSession")).getId();
        int result = service.updateProviderInfo(provider,modifyBy);
        try {
            if (result == 1){
                response.getWriter().print("<script>alert('保存成功！');location.href='../provider/management.do'</script>");
            }else{
                response.getWriter().print("<script>alert('保存失败，请联系管理员！');history.back(-1);</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
