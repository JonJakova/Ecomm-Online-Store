package Utility_Bean;

import Connection_Bean.DataGatherer;
import Connection_Bean.DataSender;
import DB_Bean.Category;
import DB_Bean.Product;
import DB_Bean.Transaction;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "manager")
@SessionScoped
public class Manager extends DataGatherer implements Serializable {

    DataGatherer dg = new DataGatherer();
    DataSender ds = new DataSender();
    EditModel em = new EditModel();
    private final long serial = 35234423534534345L;
    private UIComponent button;
    private Transaction transaction;
    private String productId;
    private String productName;
    private String productPrice;
    private String clientId;
    private String transactionId;
    private String selectedCategery;
    private String hightOrLow;
    private String yesOrNo;

    public void buyProduct() throws ClassNotFoundException, SQLException {
        ds.buyItem(productName, client.getId(), productPrice, Integer.parseInt(clientId), client.getAddress());
    }

    public void viewCategory() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        selectedCategery = request.getParameter("selectedCategory");
    }

    public void viewProduct() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        productId = request.getParameter("productId");
        clientId = request.getParameter("clientId");
    }

    public void viewTransaction() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        transactionId = request.getParameter("transactionId");
    }

    public List<Product> displayRandomProducts() throws ClassNotFoundException, SQLException {
        return dg.getRandomProduct();
    }

    public Product displaySelectedProduct() throws ClassNotFoundException, SQLException {
        Product p = getSelectedProduct(productId);
        setProductName(p.getName());
        setProductPrice(String.valueOf(p.getPrice()));
        setClientId(String.valueOf(p.getUserId()));
        return p;
    }

    public List<Product> displayAccountProduct() throws ClassNotFoundException, SQLException {
        return dg.getAccountProduct(client.getId());
    }

    public List<Product> displayFilter() throws ClassNotFoundException, SQLException {
        return dg.filter(selectedCategery, hightOrLow, productId);
    }

    public List<Transaction> displayTransaction() throws ClassNotFoundException, SQLException {
        return dg.getTransactions(Integer.parseInt(clientId));
    }

    public List<Category> displayCategories() throws ClassNotFoundException, SQLException {
        return dg.getCategyList();
    }

    public void searchProduct() throws ClassNotFoundException, SQLException {
        List<Product> prd = new ArrayList<>();
        prd = dg.getAllProducts();
        for (int i = 0; i < prd.size(); i++) {
            if (prd.get(i).getName().equals(productName)) {
                setProductId(prd.get(i).getId().toString());
            }
        }
    }

    /**
     * @return the hightOrLow
     */
    public String getHightOrLow() {
        return hightOrLow;
    }

    /**
     * @param hightOrLow the hightOrLow to set
     */
    public void setHightOrLow(String hightOrLow) {
        this.hightOrLow = hightOrLow;
    }

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the selectedCategery
     */
    public String getSelectedCategery() {
        return selectedCategery;
    }

    /**
     * @param selectedCategery the selectedCategery to set
     */
    public void setSelectedCategery(String selectedCategery) {
        this.selectedCategery = selectedCategery;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the productPrice
     */
    public String getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice the productPrice to set
     */
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return the transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * @param transaction the transaction to set
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * @return the button
     */
    public UIComponent getButton() {
        return button;
    }

    /**
     * @param button the button to set
     */
    public void setButton(UIComponent button) {
        this.button = button;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the yesOrNo
     */
    public String getYesOrNo() {
        return yesOrNo;
    }

    /**
     * @param yesOrNo the yesOrNo to set
     */
    public void setYesOrNo(String yesOrNo) {
        this.yesOrNo = yesOrNo;
    }
}



