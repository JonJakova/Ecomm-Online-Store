
package Utility_Bean;

import Connection_Bean.DataGatherer;
import Connection_Bean.DataSender;
import DB_Bean.Category;
import DB_Bean.Product;
import com.sun.net.httpserver.HttpServer;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionListener;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name="manager")
@SessionScoped
public class Manager{
    
    DataGatherer dg = new DataGatherer();
    DataSender ds = new DataSender();
    private String productId;
    private String selectedCategery;
    private String hightOrLow;
    
    public List<Product> getProductList() throws ClassNotFoundException, SQLException{
        return dg.getRandomProduct();
    }
    
    public void buyProduct() throws ClassNotFoundException, SQLException{
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        productId = request.getParameter("productId");
        System.out.println("Product ID is: "+productId);
        ds.buyItem(productId);
    }
    
    public void viewCategory(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        selectedCategery = request.getParameter("selectedCategory");
        System.out.println("Category ID is: "+selectedCategery);
    }
    
    public List<Product> displayCategory() throws ClassNotFoundException, SQLException{
        return dg.filter(selectedCategery, null);
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
}


















