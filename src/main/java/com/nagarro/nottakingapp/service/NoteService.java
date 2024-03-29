package com.nagarro.nottakingapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.ExitTest.exceptions.ProductNotFound;
import com.nagarro.nottakingapp.dto.NoteDto;
import com.nagarro.nottakingapp.entities.Notes;
import com.nagarro.nottakingapp.exceptions.NoteNotFoundException;
import com.nagarro.nottakingapp.repository.NoteRepository;
import com.nagarro.nottakingapp.repository.UserRepository;


@Service
public class NoteService {

	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public List<Notes> getNoteByUserId(Integer userId)throws NoteNotFoundException
	{
		com.nagarro.nottakingapp.entities.User user = userRepository.findById(userId).orElseThrow();
		List<Notes> notesList = new ArrayList<Notes>(user.getNotes());
		if(notesList.size()>10) {
			Collections.sort(notesList, Collections.reverseOrder(new Comparator<Notes>() {

				@Override
				public int compare(Notes o1, Notes o2) {
					
					return o1.getCreatedOn().compareTo(o2.getCreatedOn());
				}
			}));
			notesList.subList(10, notesList.size()).clear();
		}
		return notesList;

	}
	

	public Notes createNote(Integer userId, Notes entity)
	{
		com.nagarro.nottakingapp.entities.User user = userRepository.findById(userId).orElseThrow();
		Notes createdNote = noteRepository.save(entity);
		user.getNotes().add(createdNote);
		userRepository.save(user);
		
		return createdNote;
	}

    @Transactional
    public NoteDto readSingleNote(Long id) throws Exception {
        Optional<Notes>note = noteRepository.findById(id);
        return mapFromNoteToDto(note.get());
    }
    
    public void deleteNotet(Long id) throws ProductNotFound {

		// returns Note entity or null
		Optional<Notes> note = noteRepository.findById(id);

		// check if note is present or not
		if (note.isPresent()) {
			noteRepository.deleteById(id);
		} else {
			throw new ProductNotFound("No note dont exist with ", id);
		}
	}

    private NoteDto mapFromNoteToDto(Notes note) {
        NoteDto noteDto = new NoteDto();
        noteDto.setId(note.getId());
        noteDto.setTitle(note.getTitle());
        noteDto.setContent(note.getContent());
        return noteDto;
    }



}
