package obrade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entiteti.Osoba;
import sucelja.OsobaLocal;

@Stateless
public class OsobaObrada implements OsobaLocal{
	
	@PersistenceContext(unitName = "rf_demo")
	private EntityManager entityManager;

	public void spremi(Osoba o) {
		entityManager.persist(o);
	}
	
	@SuppressWarnings("unchecked")
	public List<Osoba> findAll() {
		Query q = entityManager.createQuery("FROM Osoba", Osoba.class);
		q.setMaxResults(30);

		return q.getResultList();
	}

	public void brisi(Osoba o) {
		Osoba merged = entityManager.merge(o);
		entityManager.remove(merged);
	}

	public Osoba find(int id) {
		return entityManager.find(Osoba.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Osoba> find(List<Integer> ids) {
		if(!ids.isEmpty()) {
			Query q = entityManager.createQuery("FROM Osoba WHERE osoba_id in (?1)", Osoba.class);
			q.setParameter(1, ids);
			return q.getResultList();
		}
		
		return new ArrayList<Osoba>();
	}

	public void spremiPromjene(Osoba o) {
		entityManager.merge(o);
	}
	
	@SuppressWarnings("unchecked")
	public List<Osoba> find(String search) {
		Query q = entityManager.createNativeQuery(""
				+ "SELECT DISTINCT o.osoba_id FROM (osoba o LEFT JOIN telefon t ON o.osoba_id = t.osoba_id) "
				+ "WHERE  "
				+ "t.broj LIKE ?1 OR "
				+ "o.ime LIKE ?1 OR "
				+ "o.prezime LIKE ?1 OR "
				+ "o.adresa LIKE ?1 OR "
				+ "o.mjesto LIKE ?1");
		
		q.setParameter(1, "%" + search + "%");
		List<Integer> ids = q.getResultList();
		
		List<Osoba> osobe = find(ids);
		return osobe;
	}
	
}
