package com.spring.service;

import java.util.List;

import com.spring.model.RestNote;

public interface RestNoteService {
	void saveRestNote(RestNote note);
	
    void updateRestNote(RestNote note);
    
    void deleteRestNoteById(int id);
    
    boolean deleteNote(RestNote note);
    
    RestNote findById(int id);
    
    List<RestNote> findByCategory(String category);
    
    List<RestNote> findAllRestNotes();
    
    List<RestNote> findByOrderTime();
    
    List<RestNote> findByName(String name);
    
}
