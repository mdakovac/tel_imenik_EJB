package session;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entiteti.Osoba;
import sucelja.OsobaLocal;
import sucelja.TelefonLocal;

@ManagedBean(name = "sessionVarsBean")
@SessionScoped
public class SessionVarsBean {
	@EJB
	TelefonLocal telefonLocal;

	@EJB
	OsobaLocal osobaLocal;

	private List<Osoba> listOsoba;
	private int osobaIdZaAzuriranje;

	public SessionVarsBean() {
	}

	@PostConstruct
	private void init() {
		updateListOsoba(null);
	}

	public TelefonLocal getTelefonLocal() {
		return telefonLocal;
	}

	public void setTelefonLocal(TelefonLocal telefonLocal) {
		this.telefonLocal = telefonLocal;
	}

	public List<Osoba> getListOsoba() {
		return listOsoba;
	}

	public void setListOsoba(List<Osoba> listOsoba) {
		this.listOsoba = listOsoba;
	}

	public OsobaLocal getOsobaLocal() {
		return osobaLocal;
	}

	public void setOsobaLocal(OsobaLocal osobaLocal) {
		this.osobaLocal = osobaLocal;
	}
	
	
	public int getOsobaIdZaAzuriranje() {
		return osobaIdZaAzuriranje;
	}

	public void setOsobaIdZaAzuriranje(int i) {
		this.osobaIdZaAzuriranje = i;
	}

	public void updateListOsoba(String search) {
		
		if (search == null) {
			listOsoba = osobaLocal.findAll();
		} else {
			listOsoba = osobaLocal.find(search);
		}
		
	}
}
