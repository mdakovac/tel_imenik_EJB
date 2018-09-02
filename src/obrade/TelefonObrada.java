package obrade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entiteti.Telefon;
import sucelja.TelefonLocal;

@Stateless
public class TelefonObrada implements TelefonLocal {
	@PersistenceContext(unitName = "rf_demo")
	private EntityManager entityManager;

	public void brisi(List<Telefon> t) {
		
		List<Integer> ids = new ArrayList<Integer>();
		for(Telefon a: t) {
			ids.add(a.getTelefon_id());
		}
		
		Query q = entityManager.createQuery("DELETE FROM Telefon WHERE telefon_id IN (?1)");
		q.setParameter(1, ids);
		q.executeUpdate();	
	}
}
