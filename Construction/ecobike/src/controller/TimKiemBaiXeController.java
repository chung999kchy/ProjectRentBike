package controller;

import java.util.ArrayList;
import java.util.List;

import bll.StationBLL;
import entity.Station;

public class TimKiemBaiXeController {
	
	/**
	 * 
	 * @param keySearch : từ khóa để tìm kiếm bãi xe
	 * @return : list các bãi xe mà địa chỉ có chứa keySearch
	 */
	public static List<Station> timKiemBaiXe(String keySearch) {
		StationBLL stationBLL = new StationBLL();
		
		keySearch = keySearch.toLowerCase();
		
		List<Station> allStations = stationBLL.getAllProducts();
		
		List<Station> resStations = new ArrayList<>();
		
		for (Station station : allStations) {
			String lowerCaseAddress = station.getAddress().toLowerCase();
			if (lowerCaseAddress.contains(keySearch)) {
				resStations.add(station);
			}
		}	
		
		return resStations;
	}	
}
