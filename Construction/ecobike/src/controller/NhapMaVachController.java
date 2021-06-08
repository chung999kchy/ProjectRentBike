package controller;

import bll.BikeBLL;
import entity.Bike;

public class NhapMaVachController {
	/**
	 * 
	 * @param bikeID : String, mã vạch của xe
	 * @return null nếu như mã vạch sai/ không tồn tại xe, nếu không trả về đối tượng bike ứng với bikeID
	 */
	public static Bike getBikeWithId(String bikeID) {
		if (!bikeID.matches("-?\\d+")) return null;
		BikeBLL bikeBLL = new BikeBLL();
		Bike bike = bikeBLL.find(Integer.parseInt(bikeID));
		return bike;
	}
}
