package controller;

import entity.Bill;
import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.BillService;
import util.PageBean;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/bill")
public class BillController {


    ApplicationContext context = new ClassPathXmlApplicationContext("spring.cfg.xml");
    BillService service = (BillService) context.getBean("billService");

    @RequestMapping("billadd.do")
    public String billAdd(){
        return "billadd";
    }


    @RequestMapping("management.do")
    public String management(HttpSession session, @RequestParam(value = "pageIndex",required = false)String pageIndex, @RequestParam(value = "queryProductName",required = false)String proName, @RequestParam(value = "queryProviderId",required = false) String providerId, @RequestParam(value = "queryIsPayment",required = false) String isPayment) throws UnsupportedEncodingException {
        PageBean pageBean;
        int totalRecord = 0;
        //表示一页展示7条数据
        int pageSize = 7;
        int pageNum = pageIndex==null?1:Integer.valueOf(pageIndex);
        String key = proName == null ? "" : new String(proName.getBytes("iso-8859-1"),"utf-8");
        int proId = providerId==null?0:Integer.valueOf(providerId);
        isPayment = isPayment == null ? "":new String(isPayment.getBytes("iso-8859-1"),"utf-8");

        totalRecord = service.getBillCount(key,proId,isPayment);

        pageBean = new PageBean(pageNum,pageSize,totalRecord);
        pageBean =  service.getBill(pageBean,key,proId,isPayment);
        session.setAttribute("billList",pageBean.getList());
        session.setAttribute("pageBean",pageBean);
        session.setAttribute("queryIsPayment",isPayment);
        session.setAttribute("queryProductName",key);
        session.setAttribute("proId",proId);
        return "billlist";
    }

    @RequestMapping("view.do")
    public String view(HttpSession session, @RequestParam("billid") long id){
        Bill bill = service.getBillSingle(id);
        System.out.println(bill);
        session.setAttribute("bill",bill);
        return "billview";
    }

    @RequestMapping("modify.do")
    public String modify(HttpSession session, @RequestParam("billid") long id){
        Bill bill = service.getBillSingle(id);
        session.setAttribute("bill",bill);
        return "billmodify";
    }


    @RequestMapping(value = "/sava.do",produces = "text/html;charset=UTF-8;")
    public void updBill(HttpSession session, HttpServletResponse response, Bill bill) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        long modifyBy = ((User)session.getAttribute("userSession")).getId();
        bill.setModifyBy(modifyBy);
        int result = service.updBill(bill);
        if(result > 0){
            response.getWriter().print("<script>alert('保存成功！');location.href='../bill/management.do'</script>");
        }else {
            response.getWriter().print("<script>alert('保存失败，请联系管理员！');history.back(-1);</script>");
        }
    }

    @RequestMapping(value = "add.do",produces = "text/html;charset=utf-8;")
    public void add(HttpSession session, HttpServletResponse response, Bill bill) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        long createdBy = ((User)session.getAttribute("userSession")).getId();
        bill.setCreatedBy(createdBy);
        int result = service.addBill(bill);
        if(result > 0){
            response.getWriter().print("<script>alert('保存成功！');location.href='../bill/management.do'</script>");
        }else {
            response.getWriter().print("<script>alert('保存失败，请联系管理员！');history.back(-1);</script>");
        }
    }

    @RequestMapping("/del.do")
    public void del(HttpServletResponse response, long id) throws IOException {
         int result = service.delBill(id);
         if(result == 1){
            response.getWriter().print("删除成功");
         }else{
             response.getWriter().print("删除失败");
         }
    }
}
