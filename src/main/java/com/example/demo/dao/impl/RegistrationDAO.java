package com.example.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IRegistrationDAO;
import com.example.demo.model.Registration;
import com.example.demo.scope.SHASecure;

@Transactional
@Repository
public class RegistrationDAO implements IRegistrationDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Registration getRegistrationById(int registrationId) {
		return entityManager.find(Registration.class, registrationId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registration> getAllArticles() {
		String hql = "FROM Registration as atcl ORDER BY atcl.uid";
		return (List<Registration>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public boolean createRegistration(Registration registration) {

		try {
			registration.setPswd(SHASecure.get_SHA_1_SecurePassword(registration.getPswd()));
			entityManager.persist(registration);

			System.out.println("registration : " + registration.toString().length() + " : " + registration);

			if (registration.getId() > 0)
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public void updateRegistration(Registration registration) {
		Registration artcl = getRegistrationById(registration.getId());
		artcl.setName(registration.getName());
		artcl.setUsername(registration.getUsername());
		entityManager.flush();
	}

	@Override
	public void deleteRegistration(int registrationId) {
		entityManager.remove(getRegistrationById(registrationId));
	}

	@Override
	public Registration registrationExists(String user, String pswd) {
		Registration reg = null;
		try {
			if ((user.length() > 0 & user != null) & (pswd.length() > 0 & pswd != null)) {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<Registration> cq = cb.createQuery(Registration.class);
				Root<Registration> root = cq.from(Registration.class);
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.get("pswd"), SHASecure.get_SHA_1_SecurePassword(pswd)));
				predicates.add(cb.or((cb.equal(root.get("username"), user)), cb.equal(root.get("email"), user)));
				cq.select(root).where(predicates.toArray(new Predicate[] {}));
				reg = entityManager.createQuery(cq).getSingleResult();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return reg;

	}
}
