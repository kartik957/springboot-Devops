package com.nagarro.nottakingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.ExitTest.exceptions.ProductNotFound;
import com.nagarro.nottakingapp.dto.NoteDto;
import com.nagarro.nottakingapp.entities.Notes;
import com.nagarro.nottakingapp.exceptions.NoteNotFoundException;
import com.nagarro.nottakingapp.service.NoteService;

@RestController
@RequestMapping("/api/post")
@CrossOrigin
public class NoteController 
{
	@Autowired
	private NoteService noteService;
	
	@PostMapping("/create/{userId}")
	public ResponseEntity createNote(@PathVariable Integer userId, @RequestBody Notes noteDto)
	{
		Notes createdNote=noteService.createNote(userId,noteDto);
		return new ResponseEntity(createdNote,new HttpHeaders(),HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getByUserId/{userId}")
    public ResponseEntity<List<Notes>> getNotesByUserId(@PathVariable Integer userId) throws NoteNotFoundException {
		
        List<Notes> list = noteService.getNoteByUserId(userId);
 
        return new ResponseEntity<List<Notes>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/all/{id}")
	public ResponseEntity<NoteDto> getSingleNote(@PathVariable @RequestBody Long id) throws Exception
	{
		return new ResponseEntity<>(noteService.readSingleNote(id),HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
    public HttpStatus deleteProductById(@PathVariable("id") Long id) 
    		throws ProductNotFound {
		
        noteService.deleteNotet(id);
        return HttpStatus.ACCEPTED;
    }
}
