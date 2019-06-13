
package Connection_Bean;

import java.sql.SQLException;


public class DataGatherer extends DB_Connection{
    
    public DataGatherer(){
        super();
        
    }
    
//    DataGatherer dg = new DataGatherer();
    
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

   
}






