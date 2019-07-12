package Connection_Bean;

import DB_Bean.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataGatherer extends DB_Connection{
    
    public DataGatherer(){
        super();
    }
    
    public boolean auth(String username, String password) throws ClassNotFoundException, SQLException{
      connect();
      query="select * from admin where username = '"+username+"' and password = '"+password+"'";
      stm=conn.createStatement();
      rs=stm.executeQuery(query);
      if(rs.next()){
          closeConn();
          return true;
      }
      else{
          closeConn();
          return false;
      }
    }
    
    public List<Product> getRandomProduct() throws ClassNotFoundException, SQLException{
        connect();
        query="select * from product order by RAND() LIMIT 3";
        stm=conn.createStatement();
        rs=stm.executeQuery(query);
        productList = new ArrayList<>();
        while(rs.next()){ 
            Product p = new Product();
            p.setCategoryName(rs.getString("category_name"));
            p.setUserId(rs.getInt("user_id"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getFloat("price"));
            p.setStockNum(rs.getInt("stock_num"));
            p.setDescription(rs.getString("description"));
            p.setImgUrl(rs.getString("img_url"));
            productList.add(p);
        }
        return productList;
    }
    
    public List<Product> filter() throws ClassNotFoundException, SQLException{
        
        return null;
    }
}



