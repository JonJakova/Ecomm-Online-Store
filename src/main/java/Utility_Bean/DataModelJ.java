
package Utility_Bean;

import Connection_Bean.DataGatherer;
import DB_Bean.Product;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "utility")
@ViewScoped
public class DataModelJ extends DataGatherer implements Serializable{
   
    private DataModel model;
    private Product product;
    
    public DataModelJ() throws ClassNotFoundException, SQLException{
        model = new ListDataModel(getRandomProduct());
    }
    
    public String view(){
        product = (Product) model.getRowData();
        return "view";
    }
    
    public DataModel getModel(){
        return model;
    }
    
    public Product getProduct(){
        return product;
    }
}



