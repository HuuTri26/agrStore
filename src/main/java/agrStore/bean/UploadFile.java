package agrStore.bean;

public class UploadFile {
	private String basePath;
	
	public UploadFile() {
		super();
	}
	
	public UploadFile(String basePath) {
		super();
		this.basePath = basePath;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	
}
