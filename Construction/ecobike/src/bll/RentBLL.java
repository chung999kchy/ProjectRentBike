package bll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Rent;
import entity.RentPK;
import entity.Bike;
import entity.User;
import java.sql.Timestamp;

public class RentBLL {
	Connection conn = utils.ConnectDB.getConnection();
	BikeBLL bikeBLL = new BikeBLL();
	UserBLL userBLL = new UserBLL();
    public List<Rent> getSQL(String sql){
        try {
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            ArrayList<Rent> qq = new ArrayList<>();
            while (rs.next()) {
                Rent p = new Rent();
                RentPK rentPK = new RentPK();
                rentPK.setUserId(rs.getInt("userId"));
                rentPK.setBikeId(rs.getInt("bikeId"));
                Bike bike = bikeBLL.find(rs.getInt("bikeId"));
                User user = userBLL.find(rs.getInt("userId"));
                
                p.setId(rentPK);
                p.setBike(bike);
                p.setUser(user);
                p.setStartTime(rs.getTimestamp("start_time"));
                p.setEndTime(rs.getTimestamp("end_time"));
                
                qq.add(p);
            }
            return qq;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Rent> getAllProducts() {
        String sql = "select * from rent";
        List<Rent> qq = new ArrayList<>();
        qq= getSQL(sql);
        return qq;
    }
    
    public void insert(int userId, int bikeId) {
    	try {
    		Statement sttm = conn.createStatement();
    		String sql = "insert into `rent` (userId, bikeId, start_time, end_time) values";
    		Timestamp thisTime = new Timestamp(System.currentTimeMillis());
    		sql = sql + " (" + String.valueOf(userId) + "," + String.valueOf(bikeId) + "," + "?" + "," + "?" + ")";
    		PreparedStatement ps=conn.prepareStatement(sql);
    		ps.setTimestamp(1, thisTime);
    		ps.setTimestamp(2, thisTime);
    		ps.execute();
    	} catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void delete(int bikeId) {
    	try {
    		Statement sttm = conn.createStatement();
    		String sql = "delete from rent where bikeId = " + String.valueOf(bikeId);
    		sttm.execute(sql);
    	} catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
