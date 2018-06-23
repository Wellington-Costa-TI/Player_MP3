package playermp3;

/**
 * Representa Usuário da aplicação PlayerMP3.
 * @author Wellington de Oliveira Costa.
 *
 */
public class User {
	
	private String userName;
	private String password;
	private int id;
	private boolean vip;
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isVip() {
		return vip;
	}
	public void setVip(boolean vip) {
		this.vip = vip;
	}
	

}
