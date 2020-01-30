package Utility_Bean;

import Connection_Bean.DataGatherer;
import DB_Bean.Product;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "utilityP")
@SessionScoped
public class DataModelP extends DataGatherer implements Serializable {

    private final long serial = 35234423534534345L;
    private DataModel model;
    private Product product;

    public DataModelP() throws ClassNotFoundException, SQLException {
        model = new ListDataModel(getRandomProduct());
    }

    public String view() {
        product = (Product) model.getRowData();
        return "productPage";
    }

    public DataModel getModel() {
        return model;
    }

    public Product getProduct() {
        return product;
    }
}

