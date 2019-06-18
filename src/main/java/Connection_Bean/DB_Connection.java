package Connection_Bean;

import DB_Bean.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class DB_Connection {

    private final String username="root";
    private final String password="";
    private final String db_name="ecomm";
    private final String url="jdbc:mysql://localhost:3306/"+db_name;
    private final String driver="com.mysql.jdbc.Driver";
    protected Connection conn;
    protected String query;
    protected Statement stm;
    protected PreparedStatement pstm;
    protected ResultSet rs;
    protected List<Product> productList;
    
    protected void connect() throws ClassNotFoundException{
        try{
            Class.forName(driver);
            conn=DriverManager.getConnection(url, username, password);
        }
        catch(SQLException e){
            System.err.println("Connection to DB unsuccessful "+e);
        }
    }
    
    protected void closeConn(){
        try{
            conn.close();
        }
        catch(SQLException e){
            System.err.println("DB not closed "+e);
        }
    }
   
}







