
package Utility_Bean;

import Connection_Bean.DataGatherer;
import DB_Bean.Product;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="manager")
@ViewScoped
public class Manager {
    
    DataGatherer dg = new DataGatherer();
    private String category;
    private boolean highToLow;
    private boolean lowToHigh;
  
    
    public List<Product> getProductList() throws ClassNotFoundException, SQLException{
        return dg.getRandomProduct();
    }
    
    public String view(){
        return "view";
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the highToLow
     */
    public boolean isHighToLow() {
        return highToLow;
    }

    /**
     * @param highToLow the highToLow to set
     */
    public void setHighToLow(boolean highToLow) {
        this.highToLow = highToLow;
    }

    /**
     * @return the lowToHigh
     */
    public boolean isLowToHigh() {
        return lowToHigh;
    }

    /**
     * @param lowToHigh the lowToHigh to set
     */
    public void setLowToHigh(boolean lowToHigh) {
        this.lowToHigh = lowToHigh;
    }
}






