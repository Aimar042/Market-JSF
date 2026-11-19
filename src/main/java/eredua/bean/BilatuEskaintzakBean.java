package eredua.bean;

import businessLogic.BLFacade;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("bilatu")
@RequestScoped
public class BilatuEskaintzakBean {
	
	private BLFacade facadeBL = FacadeBean.getBusinessLogic();
	private String eskaintzIzena;
	
	public String getEskaintzIzena() {
		return this.eskaintzIzena;
	}
}
