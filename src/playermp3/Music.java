package playermp3;

public class Music {
	
	private String path;
	private String name;
	
	
	public Music(String path, String name) {
		super();
		this.path = path;
		this.name = name;
	}
	public String getMusicPath() {
		return path;
	}
	public void setMusicPath(String path) {
		this.path = path;
	}
	public String getMusicName() {
		return name;
	}
	public void setMusicName(String name) {
		this.name = name;
	}
	

}
