package com.setec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.setec.entities.Booked;
import com.setec.repos.bookedRepo;
import com.setec.services.MyTelegramBot;

import org.springframework.ui.Model;

@Controller
public class MyController {
	//http://localhost:8080/home
	
	@GetMapping({"/","/home"})
	public String home(Model mod) {
		Booked booked = new Booked();
		
		booked.setId(1);
	    booked.setName("Ngor Pavly");
	    booked.setPhoneNumber("012 345 678");
	    booked.setEmail("pavly@gmail.com");
	    booked.setDate("11-27-2025");
	    booked.setTime("5:17 PM");
	    booked.setPerson(1);
	    
	    mod.addAttribute("booked", booked);
	    
		return "index";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/service")
	public String service() {
		return "service";
	}
	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	
	  @GetMapping("/reservation")
	  public String reservation(Model mod) {
	    Booked booked = new Booked(
//	    		1,
//				"Ngor Pavly",
//				"0123456789",
//				"pavly@gmail.com",
//				"27/11/2025",
//				"5:00 PM",
//				2
				);
	    
	    booked.setId(1);
	    booked.setName("Ngor Pavly");
	    booked.setPhoneNumber("012 345 678");
	    booked.setEmail("pavly@gmail.com");
	    booked.setDate("11-27-2025");
	    booked.setTime("5:17 PM");
	    booked.setPerson(1);
	    
	    mod.addAttribute("booked", booked);
	    
	    return "reservation";
	  }


	@GetMapping("/testimonial")
	public String testimonial() {
		return "testimonial";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	  @Autowired
	  private bookedRepo bookedRepo;
	  
	  @Autowired
	  private MyTelegramBot bot;
	  
	  @PostMapping("/success")
	  public String success(@ModelAttribute Booked booked) {
	    bookedRepo.save(booked);
	    bot.sendMessage(formatBooking(booked));
	    return "success";
	  }
	  
	  public String formatBooking(Booked booked) {
	      StringBuilder sb = new StringBuilder();

	      sb.append("New Booking Received\n\n");
	      sb.append("🆔 ID: ").append(booked.getId()).append("\n");
	      sb.append("👤 Name: ").append(booked.getName()).append("\n");
	      sb.append("📞 Phone: ").append(booked.getPhoneNumber()).append("\n");
	      sb.append("✉️ Email: ").append(booked.getEmail()).append("\n");
	      sb.append("📅 Date: ").append(booked.getDate()).append("\n");
	      sb.append("⏰ Time: ").append(booked.getTime()).append("\n");
	      sb.append("👥 Person: ").append(booked.getPerson()).append("\n");


	      return sb.toString();
	  }

	
	
}
