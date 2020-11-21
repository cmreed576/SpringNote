package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.spring.model.RestNote;

@Repository
public class RestNoteDaoImpl extends AbstractDao implements RestNoteDao{

	public void saveRestNote(RestNote note) {
		persist(note);
	}

	public void updateRestNote(RestNote note) {
		getSession().update(note);
	}

	public void deleteRestNoteById(int id) {
		Query query = getSession().createSQLQuery("delete from RestNote where noteId = :noteId");
		query.setInteger("userId", id);
		query.executeUpdate();
	}

	public boolean deleteNote(RestNote note) {
		getSession().delete(note);
		return true;
	}

	public RestNote findById(int id) {
		Criteria criteria = getSession().createCriteria(RestNote.class);
		criteria.add(Restrictions.eq("noteId", id));
		return (RestNote) criteria.uniqueResult();
	}

	public List<RestNote> findByCategory(String category) {
		List<RestNote> usersByCity = getSession().createQuery("from RestNote rs where rs.category = " + category).list();
		return usersByCity;
	}
	
	@SuppressWarnings("unchecked")
	public List<RestNote> findAllRestNotes() {
		Criteria criteria = getSession().createCriteria(RestNote.class);
		return (List<RestNote>) criteria.list();
	}

	public List<RestNote> findByOrderTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RestNote> findByName(String name) {
		List<RestNote> usersByName = getSession().createQuery("from RestNote rs where rs.name = " + name).list();
		return usersByName;
	}

}
