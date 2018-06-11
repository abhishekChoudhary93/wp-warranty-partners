package com.diakomio.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diakomio.model.User;

@Controller
public class BaseController {

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView getLoginPage(HttpServletRequest httpServletRequest, String invalidLogin,
			RedirectAttributes attributes, String registerComplete, String failedRegistration) {
		User user = (User) httpServletRequest.getSession().getAttribute("user");
		if (user != null) {
			return new ModelAndView("redirect:" + user.userType);
		}
		ModelAndView andView = new ModelAndView("login");
		attributes.addFlashAttribute("invalidLogin", invalidLogin);
		attributes.addFlashAttribute("registerComplete", registerComplete);
		attributes.addFlashAttribute("failedRegistration", failedRegistration);
		return andView;
	}

	@RequestMapping(value = "/apiDocumentation", method = RequestMethod.GET)
	public String getAPIDocumentation() {
		return "apiDoc";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome() {
		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex() {
		return "index";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String getAbout() {
		return "about";
	}

	@RequestMapping(value = "/warranty", method = RequestMethod.GET)
	public String getWarranty() {
		return "warranty";
	}

	@RequestMapping(value = "/claims", method = RequestMethod.GET)
	public String getClaims() {
		return "claims";
	}

	@RequestMapping(value = "/faq", method = RequestMethod.GET)
	public String getFAQ() {
		return "faq";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String getContact() {
		return "contact";
	}

	@RequestMapping(value = "/whatWeCover", method = RequestMethod.GET)
	public String whatWeCover() {
		return "whatWeCover";
	}

}
