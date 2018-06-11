package com.diakomio.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.diakomio.model.Claim;
import com.diakomio.model.Dealer;
import com.diakomio.model.DealerWarrantyDetails;
import com.diakomio.model.User;
import com.diakomio.services.DealerService;
import com.diakomio.services.UserAuthenticationService;
import com.diakomio.util.MailUtil;
import com.diakomio.util.StringUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Controller
public class DealerController {

	@RequestMapping(value = "/dealerWarranty", method = RequestMethod.GET)
	public ModelAndView getDealerWarrantiesPage(HttpServletRequest httpServletRequest, String cannotAddWarranty) {
		User user = (User) httpServletRequest.getSession().getAttribute("user");
		if (user == null) {
			return new ModelAndView("redirect:loginPage");
		}
		List<DealerWarrantyDetails> warranties = new ArrayList<>();
		DealerService dealerService = new DealerService();
		try {
			Dealer dealer = dealerService.getDealer(user.username);
			warranties = dealerService.getAllWarrantyDetailsForDealer(dealer.getDealerName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("errorMessage");
		}

		// List<Warranty> warranties = null;
		// try {
		// warranties = new
		// DealerService().getAllWarrantiesForDealer(user.username);
		// } catch (Exception e) {
		// e.printStackTrace();
		// return new ModelAndView("errorMessage");
		// }
		ModelAndView modelAndView = new ModelAndView("dealerWarranties");
		modelAndView.addObject("warranties", warranties);
		modelAndView.addObject("cannotAddWarranty", cannotAddWarranty);
		return modelAndView;

	}

	@RequestMapping(value = "/dealerClaims", method = RequestMethod.GET)
	public ModelAndView getDealerClaimsPage(HttpServletRequest httpServletRequest) {
		User user = (User) httpServletRequest.getSession().getAttribute("user");
		if (user == null) {
			return new ModelAndView("redirect:loginPage");
		}
		List<Claim> claims = null;
		try {
			claims = new DealerService().getClaimsForDealers(user.username);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errorMessage");
		}
		ModelAndView modelAndView = new ModelAndView("dealerClaims");
		modelAndView.addObject("claims", claims);
		return modelAndView;

	}

	@RequestMapping(value = "/getWarrantyNumberForDealer", method = RequestMethod.POST)
	public ModelAndView getWarrantyNumberForDealer(@RequestParam String consumerName, @RequestParam String address,
			@RequestParam String orderNumber, @RequestParam float purchasePrice, @RequestParam String productName,
			@RequestParam String productSerial, @RequestParam String dateOfPurchase,
			HttpServletRequest httpServletRequest) {
		User user = (User) httpServletRequest.getSession().getAttribute("user");
		if (user == null) {
			return new ModelAndView("redirect:loginPage");
		}
		try {
			DealerService dealerService = new DealerService();
			Dealer dealer = dealerService.getDealer(user.username);
			DealerWarrantyDetails dealerWarrantyDetails = new DealerWarrantyDetails();
			dealerWarrantyDetails.setAddress(address);
			dealerWarrantyDetails.setConsumerName(consumerName);
			dealerWarrantyDetails.setDateOfPurchase(dateOfPurchase);
			dealerWarrantyDetails.setOrderNumber(orderNumber);
			dealerWarrantyDetails.setProductName(productName);
			dealerWarrantyDetails.setPurchasePrice(purchasePrice);
			dealerWarrantyDetails.setProductSerial(productSerial);
			dealerService.generateWarrantyNumber(dealerWarrantyDetails, dealer.getDealerName());

		} catch (MySQLIntegrityConstraintViolationException exception) {
			exception.printStackTrace();
			ModelAndView andView = new ModelAndView("redirect:dealerWarranty");
			andView.addObject("cannotAddWarranty",
					"Cannot Add Warranty, Product Serial Number is already registered (if not, please contact site admin)! Try again");
			return andView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errorMessage");
		}

		return new ModelAndView("redirect:dealerWarranty");

	}

	@RequestMapping(value = "/postWarrantyDealer", method = RequestMethod.POST)
	public String postWarrantyForDealer(@RequestParam String username, @RequestParam String password,
			@RequestParam String dealerName, @RequestParam String consumerName, @RequestParam String address,
			@RequestParam String orderNumber, @RequestParam float purchasePrice, @RequestParam String productName,
			@RequestParam String productSerial, @RequestParam String dateOfPurchase) {
		UserAuthenticationService authenticationService = new UserAuthenticationService();
		if (!authenticationService.authorize(username, password, "dealer")) {
			return "errorMessage";
		}
		try {
			DealerService dealerService = new DealerService();
			Dealer dealer = dealerService.getDealer(username);
			DealerWarrantyDetails dealerWarrantyDetails = new DealerWarrantyDetails();
			dealerWarrantyDetails.setAddress(address);
			dealerWarrantyDetails.setConsumerName(consumerName);
			dealerWarrantyDetails.setDateOfPurchase(dateOfPurchase);
			dealerWarrantyDetails.setOrderNumber(orderNumber);
			dealerWarrantyDetails.setProductName(productName);
			dealerWarrantyDetails.setPurchasePrice(purchasePrice);
			dealerWarrantyDetails.setProductSerial(productSerial);
			dealerService.generateWarrantyNumber(dealerWarrantyDetails, dealer.getDealerName());
		} catch (Exception e) {
			e.printStackTrace();
			return "errorMessage";
		}
		return "successAdded";

	}

	@RequestMapping(value = "/validateDealer", method = RequestMethod.GET)
	public String activateDealer(@RequestParam String username) {
		boolean flag = false;
		try {
			flag = new DealerService().activateDealer(username);
		} catch (Exception e) {
			e.printStackTrace();
			return "errorMessage";

		}
		Dealer dealer = null;
		try {
			dealer = new DealerService().getDealer(username);
		} catch (Exception e) {
			e.printStackTrace();
			return "errorMessage";
		}
		if (flag) {
			MailUtil.setRecipient(dealer.getEmail());
			MailUtil.setSubject(StringUtil.activatedDealerSubject);
			MailUtil.setBody(StringUtil.activatedDealerBody.replace("{username}", username));
			MailUtil.sendEmail();
			return "successfulActivation";
		} else {
			return "failedActivation";
		}
	}
}
