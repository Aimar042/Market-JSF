package eredua.bean;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import businessLogic.BLFacade;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("sortu")
@ViewScoped
public class SortuEskaintzaBean implements Serializable{
	
	private BLFacade facadeBL = FacadeBean.getBusinessLogic();
	private String egoera = "BERRIA";
	private UploadedFile file;
	private File targetFile;
	private String file64;
	private Date data;
	
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
	
}
