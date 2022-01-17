package com.app.ticketmanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tickets_table")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ticketId;
	private String createdby;
	@Column(length=50)
	private String description;

	@ManyToOne
	private User user;

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public Ticket() {
		super();
	}
//	@Override
//	public String toString() {
//		return "Ticket [ticketId=" + ticketId + ", createdby=" + createdby + ", description=" + description + ", user="
//				+ user + "]";
//	}

	@Override
	public boolean equals(Object obj) {
		return this.ticketId==((Ticket)obj).getTicketId();
	}
}
