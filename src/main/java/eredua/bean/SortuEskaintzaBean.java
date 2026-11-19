package eredua.bean;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import businessLogic.BLFacade;
import exceptions.MustBeLaterThanTodayException;
import exceptions.ParamNullException;
import exceptions.SaleAlreadyExistException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("sortu")
@ViewScoped
public class SortuEskaintzaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private BLFacade facadeBL = FacadeBean.getBusinessLogic();
	private String egoera = "BERRIA";
	private UploadedFile file;
	private File targetFile;
	private String file64;
	private Date data;
	private String tituloa;
	private String deskripzioa;
	private Float prezioa;
	private Date minDate;
	
	
	public String sortuEskaintza() {
		int status = 0;;
		switch (egoera) {
		case "BERRIA":
			status = 0;
			break;
			
		case "OSOONA":
			status = 1;
			break;
			
		case "EGOKIA":
			status = 2;
		
		case "TXARRA":
			status = 3;
		}
		
		try {
			facadeBL.createSale(tituloa, deskripzioa, status, prezioa, data, "seller1@gmail.com", targetFile);
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Eskaintza ondo sortu da!"));	
		} catch (ParamNullException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Balioak ezin dira hutsak izan, idatzi balio guztiak mesedez", null));
		} catch (MustBeLaterThanTodayException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data gaur edo beranduago izan behar du", null));
		} catch (SaleAlreadyExistException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Eskaintza jadanik sortua dago",null));
		}
		
		return "ok";
	}
	
	public void kargatuIrudia() {
		targetFile = null;
		
		if (file != null && file.getSize() > 0) {
			try {
				targetFile = File.createTempFile("eskaintza_", "_" + file.getFileName());
				java.io.InputStream input = file.getInputStream();
				java.nio.file.Files.copy(input, targetFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
				
				byte[] bytes = java.nio.file.Files.readAllBytes(targetFile.toPath());
				String mimeType = (file.getContentType() != null) ? file.getContentType() : "image/png";
				this.file64 = "data:" + mimeType + ";base64," + java.util.Base64.getEncoder().encodeToString(bytes);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		this.file = event.getFile();
		kargatuIrudia();
	}
	
	public Date getMinDate() {
		return new Date();
	}
	
	public String getEgoera() {
		return this.egoera;
	}
	
	public void setEgoera(String egoera) {
		this.egoera = egoera;
	}
	
	public UploadedFile getFile() {
		return this.file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public Date getData() {
		return this.data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getFile64() {
		return this.file64;
	}
	
	public String getTituloa() {
		return this.tituloa;
	}
	
	public void setTituloa(String tituloa) {
		this.tituloa = tituloa;
	}

	public String getDeskripzioa() {
		return deskripzioa;
	}

	public void setDeskripzioa(String deskripzioa) {
		this.deskripzioa = deskripzioa;
	}

	public Float getPrezioa() {
		return prezioa;
	}

	public void setPrezioa(Float prezioa) {
		this.prezioa = prezioa;
	}
}
