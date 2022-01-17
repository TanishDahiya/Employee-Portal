package com.app.ticketmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.ticketmanagement.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	@Query("from Ticket as c where c.user.id =:userId")
	public List<Ticket> findticketsByUser(@Param("userId")int userId);

}
