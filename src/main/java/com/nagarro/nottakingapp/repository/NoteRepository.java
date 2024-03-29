package com.nagarro.nottakingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.nottakingapp.entities.Notes;

public interface NoteRepository extends JpaRepository<Notes,Long>{

}
