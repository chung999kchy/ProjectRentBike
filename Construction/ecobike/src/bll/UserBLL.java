package bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.User;

public class UserBLL {
	Connection conn = utils.ConnectDB.getConnection();
    public List<User> getSQL(String sql){
        try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            ArrayList<User> qq = new ArrayList<>();
            while (rs.next()) {
                User p = new User();
                p.setUserId(rs.getInt("userId"));
                p.setName(rs.getString("name"));
                qq.add(p);
            }
            return qq;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<User> getAllProducts() {
        String sql = "select * from user";
        List<User> qq = new ArrayList<>();
        qq= getSQL(sql);
        return qq;
    }
    
//    public List<Product> getCategoryProduct(int id) {
//        String sql = "select * from product where category_id="+id;
//        List<Product> qq = new ArrayList<>();
//        qq= getSQL(sql);
//        return qq;
//    }
    
    public User find(int id) {
        String sql = "select * from user where userId= "+id;
        List<User> qq = new ArrayList<>();
        qq= getSQL(sql);
        User p = qq.get(0);
        return p;
    }
    
//    public List<Product> findBySearch(String key) {
//        String sql = "select * from product where name like '%"+key+"%'";
//        List<Product> qq = new ArrayList<>();
//        qq= getSQL(sql);  
//        return qq;
//    }
}
