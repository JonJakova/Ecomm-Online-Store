
package Utility_Bean;

import Connection_Bean.DataGatherer;
import DB_Bean.Product;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "utility")
@SessionScoped
public class DataModelJ extends DataGatherer implements Serializable{
   
    private DataModel model;
    private Product product;
    
    public DataModelJ() throws ClassNotFoundException, SQLException{
        model = new ListDataModel(getRandomProduct());
    }
    
    public String view(){
        product = (Product) model.getRowData();
        return "productPage";
    }
    
    public DataModel getModel(){
        return model;
    }
    
    public Product getProduct(){
        return product;
    }
}











