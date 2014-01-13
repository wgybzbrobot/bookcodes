package cc.pp.account.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cc.pp.account.service.AccountService;
import cc.pp.account.service.AccountServiceException;
import cc.pp.account.service.SignUpRequest;

public class SignUpServlet extends HttpServlet {

	private static final long serialVersionUID = -924438721802732871L;

	private ApplicationContext context;

	@Override
	public void init() throws ServletException {
		super.init();
		context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String id = req.getParameter("id");
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirm_password");
		String captchaKey = req.getParameter("captcha_key");
		String captchaValue = req.getParameter("captcha_value");

		if (id == null || id.length() == 0 || email == null || email.length() == 0 || //
				name == null || name.length() == 0 || password == null || password.length() == 0 //
				|| confirmPassword == null || confirmPassword.length() == 0 //
				|| captchaKey == null || captchaKey.length() == 0 || //
				captchaValue == null || captchaValue.length() == 0) {
			resp.sendError(400, "Parameter Incomplete.");
			return;
		}

		AccountService service = (AccountService) context.getBean("accountService");
		SignUpRequest request = new SignUpRequest();

		request.setId(id);
		request.setEmail(email);
		request.setName(name);
		request.setPassword(password);
		request.setCaptchaKey(captchaKey);
		request.setCaptchaValue(captchaValue);

		request.setActivateServiceUrl(getServletContext().getRealPath("/") + "activate");

		try {
			service.singnUp(request);
			resp.getWriter().print("Account is created, please check your mail box for activation link.");
		} catch (AccountServiceException e) {
			resp.sendError(400, e.getMessage());
			return;
		}
	}

}
