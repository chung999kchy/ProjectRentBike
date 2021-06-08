package bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Category;

public class CategoryBLL {
	Connection conn = utils.ConnectDB.getConnection();
    public List<Category> getSQL(String sql){
        try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            ArrayList<Category> qq = new ArrayList<>();
            while (rs.next()) {
                Category p = new Category();
                p.setCategoryId(rs.getInt("categoryId"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setCostPerHour(rs.getFloat("cost_per_hour"));
                p.setNPedals(rs.getInt("n_pedals"));
                p.setNSeats(rs.getInt("n_seats"));
                qq.add(p);
            }
            return qq;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Category> getAllProducts() {
        String sql = "select * from category";
        List<Category> qq = new ArrayList<>();
        qq= getSQL(sql);
        return qq;
    }
    
//    public List<Product> getCategoryProduct(int id) {
//        String sql = "select * from product where category_id="+id;
//        List<Product> qq = new ArrayList<>();
//        qq= getSQL(sql);
//        return qq;
//    }
    
    public Category find(int id) {
        String sql = "select * from category where categoryId= "+id;
        List<Category> qq = new ArrayList<>();
        qq= getSQL(sql);
        Category p = qq.get(0);
        return p;
    }
    
//    public List<Product> findBySearch(String key) {
//        String sql = "select * from product where name like '%"+key+"%'";
//        List<Product> qq = new ArrayList<>();
//        qq= getSQL(sql);  
//        return qq;
//    }
}
