
package Connection_Bean;

import DB_Bean.Product;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="manager")
public class Manager {
    
    DataGatherer dg = new DataGatherer();
    
    public List<Product> getProductList() throws ClassNotFoundException, SQLException{
        return dg.getRandomProduct();
    }
}


