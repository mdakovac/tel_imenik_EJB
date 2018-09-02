package entiteti;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "osobaSeq", sequenceName = "osobeSequence", allocationSize = 1)
public class Osoba {
	private int osoba_id;
	private String ime = "";
	private String prezime = "";
	private String adresa = "";
	private String mjesto = "";
	
	private List<Telefon> telefoni;

	
	public Osoba() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "osobaSeq")
	public int getOsoba_id() {
		return osoba_id;
	}
	public void setOsoba_id(int osoba_id) {
		this.osoba_id = osoba_id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getMjesto() {
		return mjesto;
	}
	public void setMjesto(String mjesto) {
		this.mjesto = mjesto;
	}
	
	@OneToMany(targetEntity=Telefon.class, mappedBy="osoba", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public List<Telefon> getTelefoni() {
		return telefoni;
	}
	public void setTelefoni(List<Telefon> items) {
		this.telefoni = items;
	}
	
}
