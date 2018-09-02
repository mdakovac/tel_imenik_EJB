package kontroleri;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entiteti.Osoba;
import entiteti.Telefon;
import session.SessionVarsBean;
import sucelja.OsobaLocal;
import util.Language;
import util.Message;

@ManagedBean(name = "unosBean")
@ViewScoped
public class UnosBean {
	private int tip_fiksni = Telefon.TIP_TELEFONA_FIKSNI;
	private int tip_mobitel = Telefon.TIP_TELEFONA_MOBITEL;
	private int tip_fax = Telefon.TIP_TELEFONA_FAX;

	private String ime = "";
	private String prezime = "";
	private String adresa = "";
	private String mjesto = "";

	private List<Telefon> telBroj = new ArrayList<Telefon>();

	@EJB
	OsobaLocal osobaLocal;

	@ManagedProperty(value = "#{sessionVarsBean}")
	SessionVarsBean sessionVarsBean;

	public UnosBean() {
		dodajUnosNaFormu();
	}

	public void dodajUnosNaFormu() {
		telBroj.add(new Telefon());
	}

	public void ukloniUnosNaFormi(Telefon t) {
		telBroj.remove(t);
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

	public List<Telefon> getTelBroj() {
		return telBroj;
	}

	public void setTelBroj(List<Telefon> telBroj) {
		this.telBroj = telBroj;
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

	public SessionVarsBean getSessionVarsBean() {
		return sessionVarsBean;
	}

	public void setSessionVarsBean(SessionVarsBean sessionVarsBean) {
		this.sessionVarsBean = sessionVarsBean;
	}

	public String spremi() {
		
		boolean unosErrors = validateUnos();
		
		if (unosErrors == true) {
			return null;
		}

		Osoba o = new Osoba();
		o.setIme(ime);
		o.setPrezime(prezime);
		o.setAdresa(adresa);
		o.setMjesto(mjesto);

		for (Telefon t : telBroj) {
			t.setOsoba(o);
		}

		o.setTelefoni(telBroj);

		osobaLocal.spremi(o);

		sessionVarsBean.updateListOsoba(null);

		return "pregled";
	}

	private boolean validateUnos() {
		ResourceBundle rbundle = Language.getResourceBundle();
		boolean errors = false;

		if (ime.length() < 1) {
			Message.display(rbundle.getString("imePraznoGreska"));
			errors = true;
		}
		if (prezime.length() < 1) {
			Message.display(rbundle.getString("prezimePraznoGreska"));
			errors = true;
		}
		if (adresa.length() < 1) {
			Message.display(rbundle.getString("adresaPraznoGreska"));
			errors = true;
		}
		if (mjesto.length() < 1) {
			Message.display(rbundle.getString("mjestoPraznoGreska"));
			errors = true;
		}
		for (Telefon t : telBroj) {
			if (t.getBroj().length() < 1) {
				Message.display(rbundle.getString("brojPraznoGreska"));
				errors = true;
				break;
			}
		}
		
		return errors;
	}
}
