package com.diakomio.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diakomio.model.Claim;
import com.diakomio.model.Dealer;
import com.diakomio.model.User;
import com.diakomio.model.Warranty;
import com.diakomio.services.ClaimsService;
import com.diakomio.services.DealerService;
import com.diakomio.services.WarrantyService;

@Controller
public class RedirectController {

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public ModelAndView customerDashboard(HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
			@ModelAttribute("registerComplete") String registerComplete) {
		if (httpServletRequest.getSession().getAttribute("user") != null) {
			User user = (User) httpServletRequest.getSession().getAttribute("user");
			WarrantyService warrantyService = new WarrantyService();
			List<Warranty> warranties = new ArrayList<>();
			List<Claim> claims = null;
			try {
				warranties = warrantyService.getWarranties(user.username);
				claims = new ClaimsService().getClaims(user.username);
			} catch (Exception e) {
				e.printStackTrace();
				return new ModelAndView("errorMessage");
			}
			ModelAndView modelAndView = new ModelAndView("customerDashboard");
			modelAndView.addObject("totalWarranties", warranties.size());
			modelAndView.addObject("totalClaims", claims.size());
			int openClaims = 0;
			for (Claim claim : claims) {
				if (claim.getStatus().contentEquals("open")) {
					openClaims++;
				}
			}
			modelAndView.addObject("openClaims", openClaims);
			modelAndView.addObject("completeClaims", claims.size() - openClaims);

			return modelAndView;

		}
		ModelAndView modelAndView = new ModelAndView("redirect:loginPage");
		System.out.println(registerComplete);
		redirectAttributes.addFlashAttribute("registerComplete", registerComplete);
		return modelAndView;
	}

	@RequestMapping(value = "/dealer", method = RequestMethod.GET)
	public ModelAndView dealerDashboard(HttpServletRequest httpServletRequest, String registerComplete1,
			RedirectAttributes redirectAttributes, @ModelAttribute("registerComplete") String registerComplete) {
		if (httpServletRequest.getSession().getAttribute("user") != null) {
			User user = (User) httpServletRequest.getSession().getAttribute("user");
			DealerService dealerService = new DealerService();
			ModelAndView modelAndView = new ModelAndView("dealerDashboard");
			int totalWarranties = 0;
			int totalClaims = 0;
			Dealer dealer = null;
			try {
				dealer = dealerService.getDealer(user.username);
				totalWarranties = dealerService.getAllWarrantiesForDealer(user.username).size();
				totalClaims = dealerService.getClaimsForDealers(user.username).size();
			} catch (Exception e) {
				e.printStackTrace();
				return new ModelAndView("errorMessage");
			}
			modelAndView.addObject("totalWarranties", totalWarranties);
			modelAndView.addObject("totalClaims", totalClaims);
			modelAndView.addObject("dealer", dealer);
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("redirect:loginPage");
		System.out.println(registerComplete);
		redirectAttributes.addFlashAttribute("registerComplete", registerComplete);
		return modelAndView;
	}
}
