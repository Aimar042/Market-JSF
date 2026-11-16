package eredua.bean;

import businessLogic.BLFacade;
import jakarta.inject.Named;

@Named("sortu")
public class SortuEskaintzaBean {
	
	private BLFacade facadeBL = FacadeBean.getBusinessLogic();
}
