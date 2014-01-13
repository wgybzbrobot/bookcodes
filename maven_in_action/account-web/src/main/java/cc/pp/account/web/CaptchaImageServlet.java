package cc.pp.account.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cc.pp.account.service.AccountService;
import cc.pp.account.service.AccountServiceException;

public class CaptchaImageServlet extends HttpServlet {

	private static final long serialVersionUID = -4743583937800783277L;

	private ApplicationContext context;

	@Override
	public void init() throws ServletException {
		super.init();
		context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String key = request.getParameter("key");
		if (key == null || key.length() == 0) {
			response.sendError(400, "No Captcha Key Found");
		} else {
			AccountService service = (AccountService) context.getBean("accountService");
			try {
				response.setContentType("image/jpeg");
				OutputStream out = response.getOutputStream();
				out.write(service.generateCaptchaImage(key));
				out.close();
			} catch (AccountServiceException e) {
				response.sendError(400, e.getMessage());
			}
		}
	}

}
