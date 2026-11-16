package eredua.bean;

import businessLogic.BLFacade;
import jakarta.inject.Named;

@Named("ikusi")
public class IkusiEskaintzakBean {
	
	private BLFacade facadeBL = FacadeBean.getBusinessLogic();
}
