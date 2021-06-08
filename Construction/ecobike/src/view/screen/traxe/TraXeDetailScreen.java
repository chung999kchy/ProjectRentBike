package view.screen.traxe;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import bll.BikeBLL;
import entity.Bike;
import entity.Station;
import controller.ThanhToanController;
import controller.XemXeDangThueController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Configs;
import view.screen.BaseScreenHandler;
import view.screen.ThongBaoScreen;

public class TraXeDetailScreen extends BaseScreenHandler implements Initializable{
	
	@FXML
	private VBox infor;
	private Bike bike;
	private Station station;
	
	public void SubmitTra(ActionEvent event) {
		// Xu ly tra xe -- Controller
		
		String message = ThanhToanController.thanhToanTraXe(bike, station);   // if not : Tráº£ xe tháº¥t báº¡i
		try {
			ThongBaoScreen screen = new ThongBaoScreen(stage, Configs.THONG_BAO, message);
			screen.setScreenTitle("ThÃ´ng bÃ¡o quan trá»�ng");
			screen.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TraXeDetailScreen(Stage stage, String screenPath, Bike bike, Station station) throws IOException {
		super(stage, screenPath);
		
		this.bike = bike;
		this.station = station;
		
		Timestamp thoiDiemThue = XemXeDangThueController.layThoiDiemThue(Configs.USER_ID);
		Timestamp thoiDiemHienTai = new Timestamp(System.currentTimeMillis());
		
		/*
		 * if (ThanhToanController.validate(xeId) == false) { c }
		 */
		
		// ten xe
		// gia thue moi gio
		// pin con lai
		// gia coc xe

		Text y,y1,y2,y3,y4,y5, y6, y7;
		y = new Text("Mã số xe "+ bike.getBikeId() + " tên xe " + bike.getBikeName());
		y1 = new Text("Bãi xe số "+ bike.getStation().getStationId() + " địa chỉ "+ bike.getStation().getAddress());
		y2 = new Text("Lượng pin còn lại :" + (int) (bike.getPin() * 100) + "%");
		y3 = new Text("Giá thuê mỗi giờ " + (int)bike.getCategory().getCostPerHour() + Configs.CURRENCY);
		y4 = new Text("Mô tả về xe " + bike.getCategory().getNPedals() + " và có "+ bike.getCategory().getNSeats() + " chỗ ngồi  "+ bike.getCategory().getDescription());
		y5 = new Text("Tiền được hoàn " + (ThanhToanController.tinhTienThueXe(bike) - ThanhToanController.tinhTienTraXe(bike)) + Configs.CURRENCY);
		y6 = new Text("Thời điểm bắt đầu thuê " + thoiDiemThue.toString().substring(0,  16));
		y7 = new Text("Thời điểm hiện tại " + thoiDiemHienTai.toString().substring(0,  16));
		y.setFont(Configs.REGULAR_FONT);
		y1.setFont(Configs.REGULAR_FONT);
		y2.setFont(Configs.REGULAR_FONT);
		y3.setFont(Configs.REGULAR_FONT);
		y4.setFont(Configs.REGULAR_FONT);
		y5.setFont(Configs.REGULAR_FONT);
		y6.setFont(Configs.REGULAR_FONT);
		y7.setFont(Configs.REGULAR_FONT);
		infor.getChildren().addAll(y, y1, y2, y3, y4, y5, y6, y7);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
