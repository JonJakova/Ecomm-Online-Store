package Connection_Bean;

import DB_Bean.Product;
import DB_Bean.Category;
import DB_Bean.Client;
import DB_Bean.Transaction;
import com.sun.jdi.ClassNotLoadedException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataGatherer extends DB_Connection {

    public DataGatherer() {
        super();
    }

    public int auth(String username, String password) throws ClassNotFoundException, SQLException {
        connect();
        query = "select * from client where username = '" + username + "' and password = '" + password + "'";
        stm = conn.createStatement();
        rs = stm.executeQuery(query);
        if (rs.next()) {
            client = new Client();
            client.setId(rs.getInt("id"));
            client.setUsername(rs.getString("username"));
            client.setFirstname(rs.getString("firstname"));
            client.setLastname(rs.getString("lastname"));
            client.setAge(rs.getInt("age"));
            client.setPhoneNum(rs.getString("phone_num"));
            client.setAddress(rs.getString("address"));
            closeConn();
            return client.getId();
        } else {
            closeConn();
            return 0;
        }
    }

    public int authAdmin(String username, String password) throws ClassNotFoundException, SQLException {
        connect();
        query = "select * from admin where username = '" + username + "' and password = '" + password + "'";
        stm = conn.createStatement();
        rs = stm.executeQuery(query);
        if (rs.next()) {
            client = new Client();
            client.setId(rs.getInt("id"));
            client.setUsername(rs.getString("username"));
            closeConn();
            return client.getId();
        } else {
            closeConn();
            return 0;
        }
    }

    public void clearLog() {
        client.setId(null);
    }

    private void getProductFromDB(String query) throws ClassNotFoundException, SQLException {
        connect();
        stm = conn.createStatement();
        rs = stm.executeQuery(query);
        productList = new ArrayList<>();
        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setCategoryName(rs.getString("category_name"));
            p.setUserId(rs.getInt("user_id"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getFloat("price"));
            p.setStockNum(rs.getInt("stock_num"));
            p.setDescription(rs.getString("description"));
            p.setImgUrl(rs.getString("img_url"));
            productList.add(p);
        }
        closeConn();
    }

    public List<Category> getCategyList() throws ClassNotFoundException, SQLException {
        query = "select * from category";
        connect();
        stm = conn.createStatement();
        rs = stm.executeQuery(query);
        categoryList = new ArrayList<>();
        while (rs.next()) {
            Category c = new Category();
            c.setId(rs.getInt("id"));
            c.setCategoryName(rs.getString("category_name"));
            c.setCategoryPic(rs.getString("category_pic"));
            categoryList.add(c);
        }
        closeConn();
        return categoryList;
    }

    public List<Product> getAllProducts() throws ClassNotFoundException, SQLException {
        query = "select * from product";
        getProductFromDB(query);
        return productList;
    }

    public List<Product> getRandomProduct() throws ClassNotFoundException, SQLException {
        query = "select * from product order by RAND() LIMIT 6";
        getProductFromDB(query);
        return productList;
    }

    public List<Product> getAccountProduct(int id) throws ClassNotFoundException, SQLException {
        query = "select * from product where user_id = '" + id + "'";
        getProductFromDB(query);
        return productList;
    }

    public Product getSelectedProduct(String id) throws ClassNotFoundException, SQLException {
        query = "select * from product where id = '" + id + "'";
        getProductFromDB(query);
        selectedProduct = productList.get(0);
        //return productList.get(productList.size()-1);
        return selectedProduct;
    }

    public List<Product> filter(String category, String highOrLow, String prodId) throws ClassNotFoundException, SQLException {
        connect();
        if (highOrLow.isEmpty()) {
            query = "select * from product where category_name = '" + category + "'";
        } else {
            if (highOrLow.equals("high")) {
                query = "select * from product where category_name = '" + category + "' order by price DESC";
            } else {
                query = "select * from product where category_name = '" + category + "' order by price ASC";
            }
        }
        getProductFromDB(query);
        return productList;
    }

    public List<Transaction> getTransactions(int clientId) throws ClassNotFoundException, SQLException {
        connect();
        query = "select * from transaction where user_id = '" + clientId + "'";
        stm = conn.createStatement();
        rs = stm.executeQuery(query);
        transactionList = new ArrayList<>();
        while (rs.next()) {
            Transaction ts = new Transaction();
            ts.setId(rs.getInt("id"));
            ts.setUserId(rs.getInt("user_id"));
            ts.setItem(rs.getString("item"));
            ts.setStatus(rs.getBoolean("status"));
            ts.setPrice(rs.getFloat("price"));
            ts.setAddress(rs.getString("address"));
            ts.setTimestamp(rs.getDate("timestamp"));
            transactionList.add(ts);
        }
        closeConn();
        return transactionList;
    }
}


