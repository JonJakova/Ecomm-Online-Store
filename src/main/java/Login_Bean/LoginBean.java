
package Login_Bean;

import Connection_Bean.DataGatherer;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 7765876811740798583L;
	private static final String[] users = {"anna:qazwsx","kate:123456"};
	private String username;
	private String password;
	private boolean loggedIn;
        private boolean verify;
        DataGatherer dg = new DataGatherer();

	@ManagedProperty(value="#{navigationBean}")
	private NavigationBean navigationBean;
	
	/**
	 * Login operation.
	 * @return
	 */
	public String doLogin() {
             try{
                 verify=dg.auth(username, password);
             }
             catch(SQLException e){
                 System.err.println("Error: "+e);
             }
             catch(ClassNotFoundException e){
                 System.err.println("Error: "+e);
             }
             if(verify==true){
                 return navigationBean.redirectToWelcome();
             }
		
		// Set login ERROR
		FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
		
		// To to login page
		return navigationBean.toLogin();
		
	}
	
	/**
	 * Logout operation.
	 * @return
	 */
	public String doLogout() {
		// Set the paremeter indicating that user is logged in to false
		loggedIn = false;
		
		// Set logout message
		FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return navigationBean.toLogin();
	}

	// ------------------------------
	// Getters & Setters 
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}
	
}





