package controller;

import java.sql.Timestamp;
import java.util.List;

import bll.BikeBLL;
import bll.RentBLL;
import entity.Bike;
import entity.Rent;

public class XemXeDangThueController {
	/**
	 * 
	 * @param userId : xem xe đang thuê của user có userId
	 * @return : trả về đối tượng xe mà người đó đang thuê, null nếu người đó đang không thuê xe nào
	 */
	public static Bike layXeDangThue(int userId) {
		RentBLL rentBLL = new RentBLL();
		BikeBLL bikeBLL = new BikeBLL();
		List<Rent> allRents = rentBLL.getAllProducts();
		
		Rent dangThue = null;
		for (Rent rent : allRents) {
			if (rent.getId().getUserId() == userId) {
				dangThue = rent;
				break;
			}
		}
		if (dangThue == null) return null;
		return bikeBLL.find(dangThue.getId().getBikeId());
	}
	
	/**
	 * 
	 * @param userId: xem thời thuê xe đang thuê của user có userId
	 * @return : java.sql.Timestamp thời điểm bắt đầu thuê xe đang thuê của user
	 */
	public static Timestamp layThoiDiemThue(int userId) {
		RentBLL rentBLL = new RentBLL();
		List<Rent> allRents = rentBLL.getAllProducts();
		for (Rent rent : allRents) {
			if (rent.getId().getUserId() == userId) {
				return rent.getStartTime();
			}
		}
		return null;
	}
}
