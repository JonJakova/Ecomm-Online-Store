
package Connection_Bean;

import DB_Bean.Client;
import java.sql.SQLException;
import sun.jvm.hotspot.utilities.soql.SOQLException;

public class DataSender extends DB_Connection{
    
    public DataSender(){
        super();
    }
    
    public void buyItem(int productId) throws ClassNotFoundException, SOQLException, SQLException{
        for(int i=0;i<cartList.size();i++){
            if(cartList.get(i).getId()==productId){
                selectedProduct=cartList.get(i);
                cartList.remove(i);
            }
        }
        connect();
        query="insert into transaction user_id, item, status, price values (?,?,?,?)";
        pstm=conn.prepareStatement(query);
        pstm.setInt(1, client.getId());
        pstm.setString(2, selectedProduct.getName());
        pstm.setBoolean(3, true);
        pstm.setFloat(4, selectedProduct.getPrice());
    }
}




