package com.example.demo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Registration;

@Transactional
@Repository
public class RegistrationDAO implements IRegistrationDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Registration getArticleById(int registrationId) {
		return entityManager.find(Registration.class, registrationId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registration> getAllArticles() {
		String hql = "FROM Registration as atcl ORDER BY atcl.uid";
		return (List<Registration>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void addArticle(Registration registration) {
		entityManager.persist(registration);
	}

	@Override
	public void updateArticle(Registration registration) {
		Registration artcl = getArticleById(registration.getId());
		artcl.setName(registration.getName());
		artcl.setUsername(registration.getUsername());
		entityManager.flush();
	}

	@Override
	public void deleteArticle(int registrationId) {
		entityManager.remove(getArticleById(registrationId));
	}

	@Override
	public boolean articleExists(String title, String category) {
		String hql = "FROM Registration as atcl WHERE atcl.title = ? and atcl.category = ?";
		int count = entityManager.createQuery(hql).setParameter(1, title).setParameter(2, category).getResultList()
				.size();
		return count > 0 ? true : false;
	}
}
