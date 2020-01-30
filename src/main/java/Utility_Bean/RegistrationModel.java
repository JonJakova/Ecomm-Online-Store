package Utility_Bean;

import Connection_Bean.DataSender;
import Login_Bean.LoginBean;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean(name = "signup")
@RequestScoped
public class RegistrationModel {

    DataSender ds = new DataSender();
    LoginBean lb = new LoginBean();
    private UIComponent regButton;
    private String result;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String address;
    private int age;

    public String register() throws ClassNotFoundException, SQLException {
        result = ds.signup(firstname, lastname, username, password, age, phonenumber, address);
        if (result.equals("success")) {
            lb.setUsername(username);
            lb.setPassword(password);
            lb.setAdminCheck("false");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", null);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(regButton.getClientId(context), message);
            return lb.doLogin();
        } else if (result.equals("unsuccessful")) {
            FacesMessage message = new FacesMessage("Registration unsuccessful!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(regButton.getClientId(context), message);
            return "/createAccount.xhtml?faces-redirect=true";
        } else if (result.equals("username")) {
            FacesMessage message = new FacesMessage("Username already taken!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(regButton.getClientId(context), message);
            return "/createAccount.xhtml?faces-redirect=true";
        } else {
            return "/createAccount.xhtml?faces-redirect=true";
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber the phonenumber to set
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the regButton
     */
    public UIComponent getRegButton() {
        return regButton;
    }

    /**
     * @param regButton the regButton to set
     */
    public void setRegButton(UIComponent regButton) {
        this.regButton = regButton;
    }
}

