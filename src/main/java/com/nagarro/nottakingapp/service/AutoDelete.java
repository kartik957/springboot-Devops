package com.nagarro.nottakingapp.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nagarro.nottakingapp.entities.Notes;
import com.nagarro.nottakingapp.repository.UserRepository;

@Service
public class AutoDelete {
	
	@Autowired
	private UserRepository userRepository;
	

	//@Scheduled(fixedRate = 60000*60)   
	@Scheduled(cron = "0 0 * * * MON-SUN")  //will delete after every 1 hour
	public void deleteNotesAfterOneHour() {
		
		System.out.println("called");
		
		List<com.nagarro.nottakingapp.entities.User> userList = userRepository.findAll();
		
		for(com.nagarro.nottakingapp.entities.User user: userList) {
			
			Set<Notes> set = user.getNotes();
			
			List<Notes> notes = new ArrayList<Notes>(set);
			
			if(notes.size()>10) {
				System.out.println("starting");
				
				List<Notes> notesToDelete = notes.stream()
						.sorted((n1, n2)-> 
						n2.getCreatedOn().compareTo(n1.getCreatedOn()))
						.skip(10)
						.collect(Collectors.toList());
				notes.removeAll(notesToDelete);
				Set<Notes> latestNotes = new HashSet<Notes>(notes);
				user.setNotes(latestNotes);
				userRepository.save(user);
				System.out.println("deleted");
			}
		}
	}
}
