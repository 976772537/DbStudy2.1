package cn.drp.controller;

import utils.VerifyCode;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VerifyCodeServlet")
public class VerifyCodeServlet  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		VerifyCode vc=new VerifyCode();
		BufferedImage img = vc.getImage(3);
		String text = vc.getText();
		String send=req.getParameter("send");
		if(send.equals("login")){
			req.getSession().setAttribute("verifyCodeForLogin",text);
		}
		if(send.equals("regist")){
			req.getSession().setAttribute("verifyCodeForRegist",text);
		}
		vc.output(img, resp.getOutputStream());
	}
	
}
