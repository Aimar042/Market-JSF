package eredua.bean;

import businessLogic.BLFacade;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("ikusi")
@RequestScoped
public class IkusiEskaintzakBean {
	
	private BLFacade facadeBL = FacadeBean.getBusinessLogic();
}
