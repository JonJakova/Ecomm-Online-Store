package Login_Bean;

import Connection_Bean.DataGatherer;
import Utility_Bean.Manager;
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
    private String username;
    private String password;
    private boolean loggedIn;
    private boolean adminLog;
    private int clientId;
    private String adminCheck;
    DataGatherer dg = new DataGatherer();
    Manager mg = new Manager();

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigationBean;

    public LoginBean() {
        adminCheck = "false";
    }

    /**
     * Login operation.
     *
     * @return
     */
    public String doLogin() throws ClassNotFoundException, SQLException {

        if (adminCheck.equals("false")) {
            setClientId(dg.auth(username, password));
            mg.setClientId(String.valueOf(clientId));
            if (loggedIn == false) {
                if (getClientId() != 0) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(username, clientId);
                    FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
                    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    loggedIn = true;
                    adminLog = false;
                    return "/Inner/Client/Welcome.xhtml?faces-redirect=true";
                } else {
                    return navigationBean.toLogin();
                }
            } else {
                return navigationBean.toLogin();
            }
        } else {
            setClientId(dg.authAdmin(username, password));
            mg.setClientId(String.valueOf(clientId));
            if (loggedIn == false) {
                if (getClientId() != 0) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(username, clientId);
                    FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
                    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    loggedIn = true;
                    adminLog = true;
                    return "/Inner/Admin/AdminPage.xhtml?faces-redirect=true";
                } else {
                    return navigationBean.toLogin();
                }
            } else {
                return navigationBean.toLogin();
            }
        }

    }

    public String logAdmin() throws ClassNotFoundException, SQLException {
        setClientId(dg.authAdmin(username, password));
        if (loggedIn == false) {
            if (getClientId() != 0) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(username, clientId);
                // Set login ERROR
                FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                loggedIn = true;
                return navigationBean.redirectToAdmin();
            } else {
                return navigationBean.toLogin();
            }
        } else {
            return navigationBean.toLogin();
        }
    }

    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;
        setUsername(null);
        System.out.println("Reached doLogout");
        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        dg.clearLog();

        return navigationBean.toIndex();
    }

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

    /**
     * @return the clientId
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the adminCheck
     */
    public String getAdminCheck() {
        return adminCheck;
    }

    /**
     * @param adminCheck the adminCheck to set
     */
    public void setAdminCheck(String adminCheck) {
        this.adminCheck = adminCheck;
    }

    /**
     * @return the adminLog
     */
    public boolean isAdminLog() {
        return adminLog;
    }

    /**
     * @param adminLog the adminLog to set
     */
    public void setAdminLog(boolean adminLog) {
        this.adminLog = adminLog;
    }
}

