package entiteti;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "telSeq", sequenceName = "telefoniSequence", allocationSize = 1)
public class Telefon {

	private int telefon_id;
	
	public static final int TIP_TELEFONA_FIKSNI = 0;
	public static final int TIP_TELEFONA_MOBITEL = 1;
	public static final int TIP_TELEFONA_FAX = 2;

	private String broj = "";
	
	private int tipTelefona = 0;
	
	private Osoba osoba;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telSeq")
	public int getTelefon_id() {
		return telefon_id;
	}

	public void setTelefon_id(int telefon_id) {
		this.telefon_id = telefon_id;
	}
	
	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public int getTipTelefona() {
		return tipTelefona;
	}

	public void setTipTelefona(int tipTelefona) {
		this.tipTelefona = tipTelefona;
	}

	@ManyToOne
    @JoinColumn(name="osoba_id", nullable=false)
	public Osoba getOsoba() {
		return osoba;
	}

	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}

	@Override
	public String toString() {
		return "Telefon [telefon_id=" + telefon_id + ", broj=" + broj + ", tipTelefona=" + tipTelefona + "]";
	}

	public String tipString() {
		if(getTipTelefona() == TIP_TELEFONA_FIKSNI) {
			return "Fiksni";
		}
		if(getTipTelefona() == TIP_TELEFONA_MOBITEL) {
			return "Mobitel";
		}
		return "Fax";
	}
	
	
}
