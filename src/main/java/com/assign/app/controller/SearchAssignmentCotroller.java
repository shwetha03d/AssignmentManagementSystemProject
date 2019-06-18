package com.assign.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.assign.app.dto.CreateAssignmentEntity;
import com.assign.app.service.SearchAssignmentService;

@Controller
@RequestMapping("/")
public class SearchAssignmentCotroller {
	
	@Autowired
	private SearchAssignmentService searchAssignmentService;
	
	public SearchAssignmentCotroller() {
		System.out.println("created:"+this.getClass().getSimpleName());
	}
	@RequestMapping(value="/SearchAssignment.ams", method=RequestMethod.POST)
	public String searchAssignment(@RequestParam long pin, Model model) {
		System.out.println("invoked searchAssignment");
		System.out.println("Pin:"+pin);
		try {
			CreateAssignmentEntity entity;
			entity=searchAssignmentService.searchAssign(pin);
			
			if(entity!=null) {
				System.out.println("Entity from controller");
				model.addAttribute("data", entity);
				
			}
			else {
				model.addAttribute("Message", "Record Not Found...");
				//return new ModelAndView(viewName, modelName, modelObject);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("Message", "Record Not Found...");
		}
		
		
		return "Home.jsp";
		
	}

}
