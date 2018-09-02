package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Message {
	public static void display(String m) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, m, ""));
	}
}
