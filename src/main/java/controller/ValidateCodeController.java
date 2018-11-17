package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@Controller
public class ValidateCodeController {
    private char code[] = {'a','b','c','d','e','f','g','h','i','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','M','N','P','Q','R','S','T','U','V','W','X','Y','Z'};

    private static final int WIDTH = 50;

    private static final int HEIGHT = 20;

    private static final int LENGTH = 4;

    @RequestMapping("/validateCode")
    public void validateCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应报头信息
        resp.setHeader("Pragma","NO-cache");
        resp.setHeader("Cache-Control","no-cache");
        resp.setDateHeader("Expires",0);
        //设置响应的MIME类型
        resp.setContentType("/image/jpeg");
        BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        Font font = new Font("Arial",Font.TRUETYPE_FONT,18);
        Graphics g = image.getGraphics();
        Random rd = new Random();

        //设置背景颜色
        g.setColor(new Color(rd.nextInt(55)+200,rd.nextInt(55)+200,rd.nextInt(55)+200));
        g.fillRect(0,0,WIDTH-1,HEIGHT);
        g.setFont(font);

        //画边框
        g.setColor(Color.black);
        g.drawRect(0,0,WIDTH-1,HEIGHT-1);

        //随机生成验证码
        String result = "";
        for (int i = 0; i < LENGTH; ++i) {
            result += code[rd.nextInt(code.length)];
        }
        HttpSession session = req.getSession();
        session.setAttribute("code",result);

        //画验证码
        for (int i = 0; i < result.length(); i++) {
            g.setColor(new Color(rd.nextInt(200),rd.nextInt(200),rd.nextInt(200)));
            g.drawString(result.charAt(i)+"",12 * i+1,16);
        }

        //随机生成两个干扰线
        for (int i = 0; i < 2; i++) {
            g.setColor(new Color(rd.nextInt(200),rd.nextInt(200),rd.nextInt(200)));
            int x1 = rd.nextInt(WIDTH);
            int x2 = rd.nextInt(WIDTH);
            int y1 = rd.nextInt(HEIGHT);
            int y2 = rd.nextInt(HEIGHT);
            g.drawLine(x1,y1,x2,y2);
        }
        g.dispose();

        try{
            OutputStream os = resp.getOutputStream();
            ImageIO.write(image,"JPEG",os);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
