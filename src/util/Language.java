package util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class Language {
	public static ResourceBundle getResourceBundle() {
		ResourceBundle rbundle;
		Locale browserLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		
		if(browserLocale.toString().equals("hr")) {
			rbundle = ResourceBundle.getBundle("messages_hr");
		}else {
			rbundle = ResourceBundle.getBundle("messages_en");
		}
		
		return rbundle;
	}
}
