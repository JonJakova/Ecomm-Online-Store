package Connection_Bean;

import DB_Bean.Product;
import DB_Bean.Category;
import DB_Bean.Client;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataGatherer extends DB_Connection{
    
    public DataGatherer(){
        super();
    }
    
    public boolean auth(String username, String password) throws ClassNotFoundException, SQLException{
      connect();
      query="select * from client where username = '"+username+"' and password = '"+password+"'";
      stm=conn.createStatement();
      rs=stm.executeQuery(query);
      if(rs.next()){
          client= new Client();
          client.setId(rs.getInt("id"));
          client.setUsername(rs.getString("username"));
          client.setFirstname(rs.getString("firstname"));
          client.setLastname(rs.getString("lastname"));
          client.setAge(rs.getInt("age"));
          client.setPhoneNum(rs.getString("phone_num"));
          client.setAddress(rs.getString("address"));
          setClient(client);
          closeConn();
          return true;
      }
      else{
          closeConn();
          return false;
      }
    }
    
//    public boolean authAdmin(String username, String password) throws ClassNotFoundException, SQLException{
//        connect();
//        query="select * from admin where username = '"+username+"' and password = '"+password+"'";
//      stm=conn.createStatement();
//      rs=stm.executeQuery(query);
//      if(rs.next()){
//          closeConn();
//          return true;
//      }
//      else{
//          closeConn();
//          return false;
//      }
//    }
//    
     private void getProductFromDB(String query) throws ClassNotFoundException, SQLException{
        connect();
        stm=conn.createStatement();
        rs=stm.executeQuery(query);
        productList = new ArrayList<>();
        while(rs.next()){ 
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
     
     public List<Category> getCategyList() throws ClassNotFoundException, SQLException{
         query="select * from category";
         connect();
         stm=conn.createStatement();
         rs=stm.executeQuery(query);
         categoryList = new ArrayList<>();
         while(rs.next()){
             Category c = new Category();
             c.setId(rs.getInt("id"));
             c.setCategoryName(rs.getString("category_name"));
             c.setCategoryPic(rs.getString("category_pic"));
             categoryList.add(c);
         }
         closeConn();
         return categoryList;
     }
    
    public List<Product> getRandomProduct() throws ClassNotFoundException, SQLException{
        query="select * from product order by RAND() LIMIT 3";
        getProductFromDB(query);
        return productList;
    }
    
    public List<Product> filter(String category, boolean hightToLow, boolean lowToHigh) throws ClassNotFoundException, SQLException{
        connect();
        if(category!=null){
            if(hightToLow==true ){
                query="select * from product p inner join category c on p.category_name = c.category_name where p.category_name = '"+category+"' order by p.price";
                getProductFromDB(query);
                return productList;
            }
            else if(lowToHigh==true){
                query="select * from product p inner join category c on p.category_name = c.category_name where p.category_name = '"+category+"' order by p.price";
                getProductFromDB(query);
                return productList;
            }
            else{
                query="select * from product p inner join category c on p.category_name = c.category_name where p.category_name = '"+category+"'";
                getProductFromDB(query);
                return productList;
            }
        }
        else if(lowToHigh==true){
                query="select * from product p inner join category c on p.category_name = c.category_name where p.category_name = '"+category+"' order by p.price";
                getProductFromDB(query);
                return productList;
            }
        else if(hightToLow==true){
                query="select * from product p inner join category c on p.category_name = c.category_name where p.category_name = '"+category+"' order by p.price";
                getProductFromDB(query);
                return productList;
            }
        else{
            System.err.println("Missing selected category");
            return null;
        }
    }
}










