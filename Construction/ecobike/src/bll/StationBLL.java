package bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Station;

public class StationBLL {
	Connection conn = utils.ConnectDB.getConnection();
    public List<Station> getSQL(String sql){
        try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            ArrayList<Station> qq = new ArrayList<>();
            while (rs.next()) {
                Station p = new Station();
                p.setStationId(rs.getInt("stationId"));
                p.setAddress(rs.getString("address"));
                qq.add(p);
            }
            return qq;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Station> getAllProducts() {
        String sql = "select * from station";
        List<Station> qq = new ArrayList<>();
        qq= getSQL(sql);
        return qq;
    }
    
//    public List<Product> getCategoryProduct(int id) {
//        String sql = "select * from product where category_id="+id;
//        List<Product> qq = new ArrayList<>();
//        qq= getSQL(sql);
//        return qq;
//    }
    
    public Station find(int id) {
        String sql = "select * from station where stationId= "+id;
        List<Station> qq = new ArrayList<>();
        qq = getSQL(sql);
        return (qq.isEmpty() ? null : qq.get(0)); 
    }
    
    public Station find(String id) {
        String sql = "select * from station where stationId= "+id;
        List<Station> qq = new ArrayList<>();
        qq = getSQL(sql);
        return (qq.isEmpty() ? null : qq.get(0)); 
    }
    
//    public List<Product> findBySearch(String key) {
//        String sql = "select * from product where name like '%"+key+"%'";
//        List<Product> qq = new ArrayList<>();
//        qq= getSQL(sql);  
//        return qq;
//    }
}
