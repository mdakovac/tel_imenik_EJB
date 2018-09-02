package kontroleri;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entiteti.Osoba;
import entiteti.Telefon;
import session.SessionVarsBean;
import sucelja.OsobaLocal;
import sucelja.TelefonLocal;

@ManagedBean(name="azuriranjeBean")
@ViewScoped
public class AzuriranjeBean {
	private int tip_fiksni = Telefon.TIP_TELEFONA_FIKSNI;
	private int tip_mobitel = Telefon.TIP_TELEFONA_MOBITEL;
	private int tip_fax = Telefon.TIP_TELEFONA_FAX;
	
	Osoba osoba;
	
	List<Telefon> uklonjeniTelefoni = new ArrayList<Telefon>();
	
	@ManagedProperty(value = "#{sessionVarsBean}")
	SessionVarsBean sessionVarsBean;
	
	@EJB
	OsobaLocal osobaLocal;
	@EJB
	TelefonLocal telefonLocal;
	
	public AzuriranjeBean() {
		
	}
	
	@PostConstruct
	private void init() {
		int osobaId = sessionVarsBean.getOsobaIdZaAzuriranje();
		osoba = osobaLocal.find(osobaId);
	}

	public Osoba getOsoba() {
		return osoba;
	}

	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}

	public SessionVarsBean getSessionVarsBean() {
		return sessionVarsBean;
	}

	public void setSessionVarsBean(SessionVarsBean sessionVarsBean) {
		this.sessionVarsBean = sessionVarsBean;
	}

	public OsobaLocal getOsobaLocal() {
		return osobaLocal;
	}

	public void setOsobaLocal(OsobaLocal osobaLocal) {
		this.osobaLocal = osobaLocal;
	}

	public int getTip_fiksni() {
		return tip_fiksni;
	}

	public void setTip_fiksni(int tip_fiksni) {
		this.tip_fiksni = tip_fiksni;
	}

	public int getTip_mobitel() {
		return tip_mobitel;
	}

	public void setTip_mobitel(int tip_mobitel) {
		this.tip_mobitel = tip_mobitel;
	}

	public int getTip_fax() {
		return tip_fax;
	}

	public void setTip_fax(int tip_fax) {
		this.tip_fax = tip_fax;
	}
	
	public List<Telefon> getUklonjeniTelefoni() {
		return uklonjeniTelefoni;
	}

	public void setUklonjeniTelefoni(List<Telefon> uklonjeniTelefoni) {
		this.uklonjeniTelefoni = uklonjeniTelefoni;
	}

	public TelefonLocal getTelefonLocal() {
		return telefonLocal;
	}

	public void setTelefonLocal(TelefonLocal telefonLocal) {
		this.telefonLocal = telefonLocal;
	}

	public void dodajBroj() {
		List<Telefon> lt = osoba.getTelefoni();
		lt.add(new Telefon());
	}
	
	public void ukloniBroj(Telefon t) {
		List<Telefon> lt = osoba.getTelefoni();
		lt.remove(t);
		uklonjeniTelefoni.add(t);
	}
	
	public String spremiPromjene() {
		for(Telefon t: osoba.getTelefoni()) {
			t.setOsoba(osoba);
		}
		
		osobaLocal.spremiPromjene(osoba);
		
		if(!uklonjeniTelefoni.isEmpty()) {
			telefonLocal.brisi(uklonjeniTelefoni);
		}
	
		sessionVarsBean.updateListOsoba(null);
		return "pregled";
	}

	
}
