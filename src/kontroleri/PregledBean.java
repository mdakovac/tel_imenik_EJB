package kontroleri;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import entiteti.Osoba;
import entiteti.Telefon;
import session.SessionVarsBean;
import sucelja.OsobaLocal;
import sucelja.TelefonLocal;

@ManagedBean(name = "pregledBean")
public class PregledBean {
	private String search;

	@ManagedProperty(value = "#{sessionVarsBean}")
	SessionVarsBean sessionVarsBean;

	@EJB
	TelefonLocal telefonLocal;

	@EJB
	OsobaLocal osobaLocal;

	Telefon telefon;

	public PregledBean() {

	}

	public TelefonLocal getTelefonLocal() {
		return telefonLocal;
	}

	public void setTelefonLocal(TelefonLocal telefonLocal) {
		this.telefonLocal = telefonLocal;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public SessionVarsBean getSessionVarsBean() {
		return sessionVarsBean;
	}

	public void setSessionVarsBean(SessionVarsBean sessionVarsBean) {
		this.sessionVarsBean = sessionVarsBean;
	}

	public Telefon getTelefon() {
		return telefon;
	}

	public void setTelefon(Telefon telefon) {
		this.telefon = telefon;
	}

	public void trazi() {
		sessionVarsBean.updateListOsoba(search);
	}

	public String naAzuriranje(Osoba o) {
		sessionVarsBean.setOsobaIdZaAzuriranje(o.getOsoba_id());

		return "azuriranje";
	}

	public void brisi(Osoba o) {
		osobaLocal.brisi(o);
		sessionVarsBean.updateListOsoba(null);
	}

}
