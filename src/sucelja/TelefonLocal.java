package sucelja;

import java.util.List;

import javax.ejb.Local;

import entiteti.Telefon;

@Local
public interface TelefonLocal {
	public void brisi(List<Telefon> t);
}
