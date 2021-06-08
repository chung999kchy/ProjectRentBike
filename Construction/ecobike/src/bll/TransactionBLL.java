package bll;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Transaction;
import entity.User;

public class TransactionBLL {
	Connection conn = utils.ConnectDB.getConnection();
	UserBLL userBLL = new UserBLL();
    public List<Transaction> getSQL(String sql){
        try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            ArrayList<Transaction> qq = new ArrayList<>();
            while (rs.next()) {
                Transaction p = new Transaction();
                User user = userBLL.find(rs.getInt("userId"));
                
                p.setUser(user);
                p.setTransactionId(rs.getInt("transactionId"));
                p.setTotalPayment(rs.getFloat("total_payment"));
                p.setTime(rs.getTimestamp("time"));
                p.setBikeName(rs.getString("bike_name"));
                p.setRentDuration(rs.getString("rent_duration"));
                qq.add(p);
            }
            return qq;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Transaction> getAllProducts() {
        String sql = "select * from transaction";
        List<Transaction> qq = new ArrayList<>();
        qq= getSQL(sql);
        return qq;
    }
    
//    public List<Product> getCategoryProduct(int id) {
//        String sql = "select * from product where category_id="+id;
//        List<Product> qq = new ArrayList<>();
//        qq= getSQL(sql);
//        return qq;
//    }
    
    public Transaction find(int id) {
        String sql = "select * from transaction where transactionId= "+id;
        List<Transaction> qq = new ArrayList<>();
        qq= getSQL(sql);
        Transaction p = qq.get(0);
        return p;
    }
    
//    public List<Product> findBySearch(String key) {
//        String sql = "select * from product where name like '%"+key+"%'";
//        List<Product> qq = new ArrayList<>();
//        qq= getSQL(sql);  
//        return qq;
//    }
}
