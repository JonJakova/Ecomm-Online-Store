package Utility_Bean;

import Connection_Bean.DataGatherer;
import Connection_Bean.DataSender;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "edit")
@RequestScoped
public class EditModel extends DataGatherer {

    DataSender ds = new DataSender();
    private int id;
    private int userId;
    private String categoryName;
    private String name;
    private float price;
    private int stockNumber;
    private String desciption;
    private String imgUrl;

    public EditModel() {

    }

    public void editProduct() throws ClassNotFoundException, SQLException {
        ds.editItem(id, name, price, stockNumber, desciption, imgUrl);
    }

    public void getDataProduct() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        setId(Integer.parseInt(request.getParameter("productId")));
    }

    public void addProduct() throws ClassNotFoundException, SQLException {
        ds.sellItem(categoryName, client.getId(), name, price, stockNumber, desciption, imgUrl);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the stockNumber
     */
    public int getStockNumber() {
        return stockNumber;
    }

    /**
     * @param stockNumber the stockNumber to set
     */
    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    /**
     * @return the desciption
     */
    public String getDesciption() {
        return desciption;
    }

    /**
     * @param desciption the desciption to set
     */
    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    /**
     * @return the imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl the imgUrl to set
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}

