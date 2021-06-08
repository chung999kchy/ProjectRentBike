package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import org.json.JSONException;

import bll.BikeBLL;
import bll.RentBLL;
import checkout.CreditCard;
import checkout.InterbankController;
import entity.Bike;
import entity.Rent;
import entity.Station;
import utils.Configs;

public class ThanhToanController {
	
	/**
	 * 
	 * @param bike : xe cần được tính giá thuê xe
	 * @return giá thuê xe của xe nhập vào
	 */
	public static int tinhTienThueXe(Bike bike) {
		int giaXe = bike.getPrice();
		int giaThueXe = (int) (giaXe * 0.3); 
		return giaThueXe;
	}
	
	/**
	 * 
	 * @param bike thanh toán thuê xe bike và cập nhập lại database tương ứng
	 * @return Trạng thái của giao dịch
	 */
	public static String thanhToanThueXe(Bike bike) {
		if (bike.getStatus() == 1) {
			return "Xe đã được thuê";
		}
		
		InterbankController interbankController = new InterbankController();
		
		String errorCode = null;
		try {
			System.out.println("Da vao thanh toan controller");
			errorCode = interbankController.payRental(new CreditCard(), tinhTienThueXe(bike), "thue Xe");
		} catch (JSONException | IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(errorCode.equals("00"));
		
		if (errorCode.equals("00")) {
			BikeBLL bikeBLL = new BikeBLL();
			bikeBLL.update(bike.getBikeId(), "status", "1");
			System.out.println("updated bikeId" + bike.getBikeId() + "to 1");
			RentBLL rentBLL = new RentBLL();
			rentBLL.insert(Configs.USER_ID, bike.getBikeId());
		}
		
		return Configs.ERROR_CODE.get(errorCode);
	}
	
	/**
	 * 
	 * @param bike : xe cần được tính giá hoàn trả xe
	 * @return giá hoàn trả xe của xe nhập vào
	 */
	public static int tinhTienTraXe(Bike bike) {
		RentBLL rentBLL = new RentBLL();
		List<Rent> allRents = rentBLL.getAllProducts();
		Rent dangThue = null;
		for (Rent rent : allRents) {
			if (rent.getId().getBikeId() == bike.getBikeId()) {
				dangThue = rent;
				break;
			}
		}
		
		Timestamp startTime = dangThue.getStartTime();
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		
		int soPhutDaThue = endTime.getMinutes() - startTime.getMinutes(); 
		
		System.out.println("So phut da thue : " + soPhutDaThue);
		
		if (soPhutDaThue < 10) {
			return 0;
		} else if(soPhutDaThue < 30) {
			return 10000;
		} else {
			return (int) (10000 + (soPhutDaThue - 30) / 15 * bike.getCategory().getCostPerHour());
		}
	}
	
	/**
	 * 
	 * @param bike thanh toán trả xe bike và cập nhập lại database tương ứng
	 * @return Trạng thái của giao dịch
	 */
	public static String thanhToanTraXe(Bike bike, Station station) {
		InterbankController interbankController = new InterbankController();
		
		String errorCode = null;
		try {
			errorCode = interbankController.refund(new CreditCard(), tinhTienThueXe(bike) - tinhTienTraXe(bike), "tra Xe");
		} catch (JSONException | IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (errorCode.equals("00")) {
			BikeBLL bikeBLL = new BikeBLL();
			bikeBLL.update(bike.getBikeId(), "status", "0");
			bikeBLL.update(bike.getBikeId(), "stationId", String.valueOf(station.getStationId()));
			RentBLL rentBLL = new RentBLL();
			rentBLL.delete(bike.getBikeId());
//			TransactionBLL transactionBLL = new TransactionBLL();
		}
		
		return Configs.ERROR_CODE.get(errorCode);
	}
}
