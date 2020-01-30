package Utility_Bean;

import Connection_Bean.DataGatherer;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

@ViewScoped
@ManagedBean(name = "search")
public class SearchFilter extends DataGatherer {

    private Map<String, String> productData;
    private String searchString;
    private String product;

    public SearchFilter() throws ClassNotFoundException, SQLException {
        super();
        productData = new HashMap<String, String>() {
            {
                getAllProducts();
                for (int i = 0; i < productList.size(); i++) {
                    put(productList.get(i).getId().toString().toLowerCase(), productList.get(i).getName().toString());
                }
            }
        };
    }

    public void updateProduct(AjaxBehaviorEvent event) {
        product = productData.get(searchString);
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getProduct() {
        return product;
    }

}

