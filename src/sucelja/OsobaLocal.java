package sucelja;

import java.util.List;

import javax.ejb.Local;

import entiteti.Osoba;

@Local
public interface OsobaLocal {
	public void spremi(Osoba o);

	public List<Osoba> findAll();
	
	public Osoba find(int id);
	
	public List<Osoba> find(List<Integer> ids);
	
	public List<Osoba> find(String search);
	
	public void brisi(Osoba o);
	
	public void spremiPromjene(Osoba o);
}
