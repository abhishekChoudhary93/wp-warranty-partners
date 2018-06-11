package com.diakomio.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diakomio.model.Customer;
import com.diakomio.model.Dealer;
import com.diakomio.model.User;
import com.diakomio.services.UserAuthenticationService;
import com.diakomio.services.UserService;
import com.diakomio.util.MailUtil;
import com.diakomio.util.StringUtil;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam String username, @RequestParam String password,
			@RequestParam String userType, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		UserAuthenticationService userAuthService = new UserAuthenticationService();
		boolean isAuthorized = false;
		try {
			isAuthorized = userAuthService.authorize(username, password, userType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (isAuthorized) {
			ModelAndView modelAndView = new ModelAndView("redirect:" + userType);
			User user = new User(username, userType);
			// modelAndView.addObject("user", user);
			request.getSession().setAttribute("user", user);
			return modelAndView;
		}

		ModelAndView modelAndView = new ModelAndView("redirect:loginPage");
		redirectAttributes.addFlashAttribute("invalidLogin", "Username or Password is incorrect!");
		return modelAndView;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:loginPage";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerNewUser(RedirectAttributes redirectAttributes, HttpServletRequest request,
			@RequestParam String name, @RequestParam String username, @RequestParam String email,
			@RequestParam String contactNo, @RequestParam String address, @RequestParam String userType,
			@RequestParam String password) {
		UserService userService = new UserService();
		User user = new User(username, userType);
		boolean flag = false;
		if (userType.contentEquals("customer")) {
			Customer customer = new Customer();
			customer.setAddress(address);
			customer.setContactNumber(contactNo);
			customer.setEmail(email);
			customer.setCustomerName(name);
			try {
				flag = userService.addCustomer(customer, user, password);
			} catch (Exception e) {
				e.printStackTrace();

			}
			if (flag == true) {
				MailUtil.setSubject(StringUtil.newUserSubject.replace("{user}", customer.getCustomerName()));
				MailUtil.setRecipient(customer.getEmail());
				MailUtil.setBody(StringUtil.newCustomerBody);
				try {
					MailUtil.sendEmail();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			Dealer dealer = new Dealer();
			dealer.setAddress(address);
			dealer.setContactNumber(contactNo);
			dealer.setDealerName(name);
			dealer.setEmail(email);

			try {
				flag = userService.addDealer(dealer, user, password);
			} catch (Exception e) {
				e.printStackTrace();

			}
			if (flag == true) {

				MailUtil.setSubject(StringUtil.newUserSubject.replace("{user}", dealer.getDealerName()));
				MailUtil.setBody(StringUtil.activateDealerBody);
				MailUtil.setRecipient(dealer.getEmail());
				try {
					MailUtil.sendEmail();
				} catch (Exception e) {
					e.printStackTrace();
				}
				MailUtil.setRecipient(StringUtil.adminEmail);
				MailUtil.setSubject(StringUtil.activateDealerSubjectToAdmin.replace("{user}", dealer.getDealerName()));
				MailUtil.setBody(StringUtil.activateDealerBodyAdmin.replace("{username}", user.username));
				try {
					MailUtil.sendEmail();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ModelAndView modelAndView = new ModelAndView();
		if (flag) {
			modelAndView.setViewName("redirect:" + userType);
			if (!userType.contentEquals("customer")) {
				modelAndView.addObject("registerComplete",
						"You have been registered, Please wait for account Activation");
				System.out.println("here");
			} else {
				modelAndView.addObject("registerComplete", "You have been registered, Please Login!!");
			}
		} else {
			modelAndView.setViewName("redirect:loginPage");
			redirectAttributes.addFlashAttribute("failedRegistration", "Cannot register user, try different username");
		}

		return modelAndView;

	}
}
