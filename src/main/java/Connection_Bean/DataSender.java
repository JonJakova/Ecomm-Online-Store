package Connection_Bean;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSender extends DB_Connection {

    public DataSender() {
        super();
    }

    public String signup(String firstname, String lastname, String username, String password, int age, String phonenumber, String address) throws ClassNotFoundException, SQLException {
        int i = 0;
        connect();
        query = "select * from client where username='" + username + "'";
        stm = conn.createStatement();
        rs = stm.executeQuery(query);
        if (rs.next()) {
            closeConn();
            return "username";
        } else {
            query = "insert into client (username, password, firstname, lastname, age, phone_num, address) values (?,?,?,?,?,?,?)";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, firstname);
            pstm.setString(4, lastname);
            pstm.setInt(5, age);
            pstm.setString(6, phonenumber);
            pstm.setString(7, address);
            i = pstm.executeUpdate();
            closeConn();
            if (i > 0) {
                return "success";
            } else {
                return "unsuccess";
            }
        }
    }

    public void buyItem(String productName, int clientId, String productPrice, int sClientId, String address) throws ClassNotFoundException, SQLException {
        System.out.println("ID from DataSenter (buyItem) " + productName + " " + clientId + " " + productPrice);
        connect();
        query = "insert into transaction (user_id, item, status, price, address) values (?,?,?,?,?)";
        pstm = conn.prepareStatement(query);
        pstm.setInt(1, clientId);
        pstm.setString(2, productName);
        pstm.setBoolean(3, true);
        pstm.setDouble(4, Double.parseDouble(productPrice));
        pstm.setString(5, address);
        pstm.execute();
        closeConn();

        connect();
        pstm = conn.prepareStatement(query);
        pstm.setInt(1, sClientId);
        pstm.setString(2, productName);
        pstm.setBoolean(3, false);
        pstm.setDouble(4, Double.parseDouble(productPrice));
        pstm.setString(5, address);
        pstm.execute();
        closeConn();

//        connect();
//        query="delete from product where name = '"+productName+"' and stock_num = 1;"
//                + " update product set stock_num = stock_num - 1 where name = '"+productName+"'";
//        pstm=conn.prepareStatement(query);
//        pstm.executeUpdate();
//        closeConn();
    }

    public void sellItem(String categoryName, int userId, String name, float price, int stockNumber, String description, String imgUrl) throws ClassNotFoundException, SQLException {
        System.out.println("Data from edit page: " + name + " " + price);
        connect();
        query = "insert into product (category_name, user_id, name, price, stock_num, description, img_url) values (?,?,?,?,?,?,?)";
        pstm = conn.prepareStatement(query);
        pstm.setString(1, categoryName);
        pstm.setInt(2, userId);
        pstm.setString(3, name);
        pstm.setFloat(4, price);
        pstm.setInt(5, stockNumber);
        pstm.setString(6, description);
        pstm.setString(7, imgUrl);
        pstm.execute();
        closeConn();
    }

    public void editItem(int id, String name, float price, int stockNumber, String description, String imgUrl) throws ClassNotFoundException, SQLException {
        System.out.println("Data from edit page: " + id + " " + name + " " + price + " " + stockNumber);
        connect();
        query = "update product set name = ?, price = ?, stock_num = ? where id = '" + id + "'";
        pstm = conn.prepareStatement(query);
        pstm.setString(1, name);
        pstm.setFloat(2, price);
        pstm.setInt(3, stockNumber);
        pstm.executeUpdate();
        closeConn();
    }
}




