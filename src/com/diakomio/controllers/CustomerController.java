package com.diakomio.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diakomio.model.Claim;
import com.diakomio.model.User;
import com.diakomio.model.Warranty;
import com.diakomio.services.ClaimsService;
import com.diakomio.services.DealerService;
import com.diakomio.services.UserService;
import com.diakomio.services.WarrantyService;
import com.diakomio.util.MailUtil;
import com.diakomio.util.StringUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Controller
public class CustomerController {

	@RequestMapping(value = "/customerWarranty", method = RequestMethod.GET)
	public ModelAndView getCustomerWarrantyPage(RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, String cannotAddWarranty) {
		User user = (User) httpServletRequest.getSession().getAttribute("user");
		if (user == null) {
			return new ModelAndView("redirect:loginPage");
		}
		WarrantyService warrantyService = new WarrantyService();
		List<Warranty> warranties = null;
		try {
			warranties = warrantyService.getWarranties(user.username);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errorMessage");
		}

		ModelAndView modelAndView = new ModelAndView("customerWarranties");
		modelAndView.addObject("warranties", warranties);
		modelAndView.addObject("cannotAddWarranty", cannotAddWarranty);
		return modelAndView;
	}

	@RequestMapping(value = "/addWarranty", method = RequestMethod.POST)
	public ModelAndView addCustomerWarranty(RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, @RequestParam String warrantyNumber,
			@RequestParam String productSerial) {
		User user = (User) httpServletRequest.getSession().getAttribute("user");
		if (user == null) {
			return new ModelAndView("redirect:loginPage");
		}
		ModelAndView modelAndView = new ModelAndView("redirect:customerWarranty");
		WarrantyService warrantyService = new WarrantyService();
		int result = 0;
		try {
			result = warrantyService.addWarranty(user.username, warrantyNumber, productSerial);
		} catch (MySQLIntegrityConstraintViolationException exception) {
			exception.printStackTrace();
			modelAndView.addObject("cannotAddWarranty",
					"Cannot register Warranty! Warranty Number or Product Serial Number incorrect or Already Registered !! Please contact admin");
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errorMessage");
		}
		if (result != 1) {
			redirectAttributes.addFlashAttribute("cannotAddWarranty", "Cannot register Warranty, Please contact Admin");
		}

		try {
			MailUtil.setRecipient(new UserService().getCustomerEmail(user.username));
		} catch (Exception e) {
			e.printStackTrace();
		}
		MailUtil.setSubject(StringUtil.newWarrantyAddedSubject);
		String body = StringUtil.newWarrantyAddedBody.replace("{warrantyId}", warrantyNumber)
				.replace("{productSerialNumber}", productSerial);
		MailUtil.setBody(body);
		try {
			MailUtil.sendEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		body = StringUtil.newWarrantyAddedBodyForDealer.replace("{warrantyId}", warrantyNumber)
				.replace("{productSerialNumber}", productSerial);
		MailUtil.setBody(body);
		try {
			// MailUtil.setRecipient(new
			// DealerService().getDealerEmailFromDealerName(warranty.getDealer()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// MailUtil.sendEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/customerClaims", method = RequestMethod.GET)
	public ModelAndView getCustomerClaimsPage(HttpServletRequest httpServletRequest, String cannotFileClaim) {
		User user = (User) httpServletRequest.getSession().getAttribute("user");
		if (user == null) {
			return new ModelAndView("redirect:loginPage");
		}
		WarrantyService warrantyService = new WarrantyService();
		List<Warranty> warranties = null;
		try {
			warranties = warrantyService.getWarranties(user.username);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errorMessage");
		}
		ClaimsService claimsService = new ClaimsService();
		List<Claim> claims = null;
		try {
			claims = claimsService.getClaims(user.username);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errorMessage");
		}
		ModelAndView modelAndView = new ModelAndView("customerClaims");
		modelAndView.addObject("warranties", warranties);
		modelAndView.addObject("claims", claims);
		modelAndView.addObject("cannotFileClaim", cannotFileClaim);
		return modelAndView;
	}

	@RequestMapping(value = "/addClaim", method = RequestMethod.POST)
	public ModelAndView getCustomerClaimsPage(RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, @RequestParam String name, @RequestParam String warrantyRegNumber,
			@RequestParam String warrantyNumber, @RequestParam String contactNo, @RequestParam String address) {
		User user = (User) httpServletRequest.getSession().getAttribute("user");
		if (user == null) {
			return new ModelAndView("redirect:loginPage");
		}
		ClaimsService claimsService = new ClaimsService();
		Claim claim = new Claim();
		claim.setName(name);
		claim.setWarrantyId(warrantyRegNumber);
		claim.setContactNo(contactNo);
		claim.setAddress(address);
		claim.setWarrantyNumber(warrantyNumber);
		boolean status = false;
		try {
			status = claimsService.addNewClaim(user.username, claim);
		} catch (MySQLIntegrityConstraintViolationException exception) {
			ModelAndView modelAndView = new ModelAndView("redirect:customerClaims");
			modelAndView.addObject("cannotFileClaim",
					"Cannot file Claim !! Either Warranty Number or Warranty Reg. Number is incorrect, or an Open Claim is already there for this warranty");
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errorMessage");
		}

		if (status != true) {
			redirectAttributes.addFlashAttribute("cannotAddWarranty", "Cannot File Claim, Please contact Admin");
		}
		try {
			MailUtil.setRecipient(new UserService().getCustomerEmail(user.username));
		} catch (Exception e) {
			e.printStackTrace();
		}
		MailUtil.setSubject(StringUtil.newClaimAddedSubject);
		String body = StringUtil.newClaimAddedBody.replace("{name}", claim.getName());
		MailUtil.setBody(body);
		try {
			MailUtil.sendEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MailUtil.setBody("A new Claim has been filed. Please check you account for details!");
		MailUtil.setSubject("New Claim Filed");
		try {
			MailUtil.setRecipient(new DealerService().getDealerEmailFromWarrantyId(claim.getWarrantyId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			MailUtil.sendEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:customerClaims");
	}

}
