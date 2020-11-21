package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.RestNoteDao;
import com.spring.model.RestNote;

public class RestNoteServiceImpl implements RestNoteService {
	
	@Autowired
	private RestNoteDao dao;
	
	public void saveRestNote(RestNote note) {
		dao.saveRestNote(note);
	}
	
    public void updateRestNote(RestNote note) {
    	dao.updateRestNote(note);
    }
    
    public void deleteRestNoteById(int id) {
    	dao.deleteRestNoteById(id);
    }
    
    public boolean deleteNote(RestNote note) {
    	return dao.deleteNote(note);
    }
    
    public RestNote findById(int id) {
    	return dao.findById(id);
    }
    
    public List<RestNote> findByCategory(String category) {
    	return dao.findByCategory(category);
    }
    
    public List<RestNote> findAllRestNotes(){
    	return dao.findAllRestNotes();
    }
    
    public List<RestNote> findByOrderTime(){
    	return dao.findByOrderTime();
    }
    
    public List<RestNote> findByName(String name){
    	return dao.findByName(name);
    }
    
}
