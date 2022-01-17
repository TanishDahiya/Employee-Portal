package com.app.ticketmanagement.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.ticketmanagement.dao.TicketRepository;
import com.app.ticketmanagement.dao.UserRepository;
import com.app.ticketmanagement.models.Ticket;
import com.app.ticketmanagement.models.User;
import com.app.ticketmanagement.validate.Message;


@Controller    
@RequestMapping("/user")
public class UserController {
	private static final Logger log= LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	//method for adding common data to response
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("USERNAME " + userName);
		User user = userRepository.getUserByUserName(userName);
		System.out.println("USER " + user);
		model.addAttribute("user", user);

	}
	
	//Dashboard -->Home
	@RequestMapping("/index")
	public String dasboard(Model model) {
		model.addAttribute("title","Dashboard");
		return "user_dashboard";
	}
	
	// add form handler
	@GetMapping("/add-ticket")
	public String openAddTicketForm(Model model) {
		log.info("Generate Tickets Page");
		model.addAttribute("title", "Add Ticket");
		model.addAttribute("ticket", new Ticket());

		return "add_ticket_form";
	}
	
	//processing add ticket form
	@PostMapping("/process-ticket")
	public String processContact(@ModelAttribute Ticket ticket,
			Principal principal, HttpSession session) {

		try {

			String name = principal.getName();
			User user = this.userRepository.getUserByUserName(name);
			user.getTickets().add(ticket);
			ticket.setUser(user);
			this.userRepository.save(user);
			System.out.println("DATA " + ticket);
			System.out.println("Added to data base");

			// message success.......
			session.setAttribute("message", new Message("Your Ticket is added !! Add more..", "success"));

		} catch (Exception e) {
			log.error("An ERROR occured");
			System.out.println("ERROR " + e.getMessage());
			e.printStackTrace();
			// message error
			session.setAttribute("message", new Message("Something went wrong !! Try again..", "danger"));

		}
		System.out.println("DATA"+ticket);

		return "add_ticket_form";
	}
	
	// show tickets handler

		@GetMapping("/show-tickets")
		public String showContacts( Model m, Principal principal) {
			m.addAttribute("title", "Show User Tickets");
			//send tickets from he database
			String userName = principal.getName();
			User user = this.userRepository.getUserByUserName(userName);
  		    List<Ticket> tickets = this.ticketRepository.findticketsByUser(user.getId());
			m.addAttribute("tickets", tickets);
			return "show_tickets";
		}
		
		//delete ticket handler
		@GetMapping("/delete/{ticketId}")
		public String deleteContact(@PathVariable("ticketId") Integer ticketId, Model model, HttpSession session,
				Principal principal) {
			System.out.println("CID " + ticketId);

			Ticket ticket= this.ticketRepository.findById(ticketId).get();
			
			User user=this.userRepository.getUserByUserName(principal.getName());
			user.getTickets().remove(ticket);
	    
			this.userRepository.save(user);

			System.out.println("DELETED");
			session.setAttribute("message", new Message("Contact deleted succesfully...", "success"));

			return "redirect:/user/show-tickets";
		}
		
		//open update ticket form handler
		@PostMapping("/update-ticket/{ticketId}")
		public String updateForm(@PathVariable("ticketId") Integer ticketId, Model m) {
			

			m.addAttribute("title", "Update Ticket");

			Ticket ticket = this.ticketRepository.findById(ticketId).get();

			m.addAttribute("ticket", ticket);

			return "update_form";
		}
		
		//update ticket handler
		@RequestMapping(value="/process-update",method=RequestMethod.POST)
		public String updateHandler(@ModelAttribute Ticket ticket, Model m,HttpSession session, Principal principal) {
			
			try {
				log.info("Ticket is Updated");
				System.out.println("Ticket created by:" + ticket.getCreatedby());
				User user=this.userRepository.getUserByUserName(principal.getName());
				ticket.setUser(user);
				this.ticketRepository.save(ticket);
				session.setAttribute("message", new Message("Ticket is updated...","success"));
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return "redirect:/user/show-tickets";
		}



}
