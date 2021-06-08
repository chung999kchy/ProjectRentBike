package controller;

import java.util.List;
import java.util.ArrayList;
import bll.BikeBLL;
import entity.Bike;

public class XemBaiXeController {
	

	/**
	 * 
	 * @param stationId : id của bãi xe cần xem chi tiết
	 * @return: List các xe trong bãi xe ứng với stationId
	 */
	static public List<Bike> getBikesWithStationId(int stationId) {
		BikeBLL bikeBLL = new BikeBLL();
		List<Bike> bikes = bikeBLL.getAllProducts();
		
		List<Bike> resBikes = new ArrayList<>();
		
		for (Bike bike : bikes) {
			if (bike.getStation().getStationId() == stationId && bike.getStatus() == 0) {
				resBikes.add(bike);
			}
		}
		
		return resBikes;
	}
}
