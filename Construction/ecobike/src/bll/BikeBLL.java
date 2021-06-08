package bll;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Bike;

public class BikeBLL {
	Connection conn = utils.ConnectDB.getConnection();
	StationBLL stationBll = new StationBLL();
	CategoryBLL categoryBll = new CategoryBLL();
    public List<Bike> getSQL(String sql){
        try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            ArrayList<Bike> qq = new ArrayList<>();
            while (rs.next()) {
                Bike p = new Bike();
                p.setBikeName(rs.getString("bikeName"));
                p.setBikeId(rs.getInt("bikeId"));
                p.setStatus(rs.getInt("status"));
                p.setPin(rs.getFloat("pin"));
                int stationId = rs.getInt("stationId");
                p.setStation(stationBll.find(stationId));
                int categoryId = rs.getInt("categoryId");
                p.setCategory(categoryBll.find(categoryId));
                p.setPrice(rs.getInt("price"));
                qq.add(p);
            }
            return qq;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Bike> getAllProducts() {
        String sql = "select * from bike";
        List<Bike> qq = new ArrayList<>();
        qq= getSQL(sql);
        return qq;
    }
    
    public void update(int id, String key, String value) {
    	try {
    		Statement sttm = conn.createStatement();
            sttm.execute("update bike set " + key + "=" + value + " where bikeId = " + String.valueOf(id));
    	} catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Bike find(int id) {
        String sql = "select * from bike where bikeId= "+id;
        List<Bike> qq = new ArrayList<>();
        qq= getSQL(sql);
        return (qq.isEmpty() ? null : qq.get(0));
    }
    
    public Bike find(String id) {
        String sql = "select * from bike where bikeId= "+id;
        List<Bike> qq = new ArrayList<>();
        qq= getSQL(sql);
        return (qq.isEmpty() ? null : qq.get(0));
    }
}
