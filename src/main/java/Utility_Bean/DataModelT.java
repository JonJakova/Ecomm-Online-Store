package Utility_Bean;

import Connection_Bean.DataGatherer;
import DB_Bean.Transaction;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "utilityT")
@SessionScoped
public class DataModelT extends DataGatherer implements Serializable {

    private DataModel model;
    private Transaction transaction;

    public DataModelT() throws ClassNotFoundException, SQLException {
        model = new ListDataModel(getTransactions(client.getId()));
    }

    public String view() {
        transaction = (Transaction) model.getRowData();
        return "/Inner/Client/transactionInfo.xhtml?faces-redirect=true";
    }

    public DataModel getModel() {
        return model;
    }

    public Transaction getTransaction() {
        return transaction;
    }

}

