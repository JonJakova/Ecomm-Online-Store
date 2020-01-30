package Utility_Bean;

import Connection_Bean.DataGatherer;
import DB_Bean.Category;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "utilityC")
@SessionScoped
public class DataModelC extends DataGatherer implements Serializable {

    private DataModel model;
    private Category category;

    public DataModelC() throws ClassNotFoundException, SQLException {
        model = new ListDataModel(getCategyList());
    }

    public String view() {
        category = (Category) model.getRowData();
        return "filteredList";
    }

    public DataModel getModel() {
        return model;
    }

    public Category getCategory() {
        return category;
    }
}

