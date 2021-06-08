package view.screen.thuexe;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import controller.ThanhToanController;
import entity.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Configs;
import view.screen.BaseScreenHandler;
import view.screen.ThongBaoScreen;

public class ThanhToanScreen extends BaseScreenHandler implements Initializable{

	@FXML
	private VBox infor;
	private Bike bike;
	
	public void SubmitOk(ActionEvent event) {
		String message = ThanhToanController.thanhToanThueXe(bike);
		try {
			ThongBaoScreen screen = new ThongBaoScreen(stage, Configs.THONG_BAO, message);
			screen.setScreenTitle("Thông báo");
			screen.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public ThanhToanScreen(Stage stage, String screenPath, Bike bike) throws IOException {
		super(stage, screenPath);
		this.bike = bike;
		
		// ten xe
		// gia thue moi gio
		// pin con lai
		// gia coc xe
		
		Timestamp thoiDiemHienTai = new Timestamp(System.currentTimeMillis());
		
		
		Text y,y1,y2,y3,y4,y5, y6;
		y = new Text("Mã số xe "+ bike.getBikeId() + " tên xe " + bike.getBikeName());
		y1 = new Text("Bãi xe số "+ bike.getStation().getStationId() + " địa chỉ "+ bike.getStation().getAddress());
		y2 = new Text("Lượng pin còn lại :" + (int) (bike.getPin() * 100) + "%");
		y3 = new Text("Giá thuê mỗi giờ " + (int)bike.getCategory().getCostPerHour() + Configs.CURRENCY);
		y4 = new Text("Mô tả về xe " + bike.getCategory().getNPedals() + " và có "+ bike.getCategory().getNSeats() + " chỗ ngồi  "+ bike.getCategory().getDescription());
		y5 = new Text("Tiền cọc " + ThanhToanController.tinhTienThueXe(bike) + Configs.CURRENCY);
		y6 = new Text("Thời điểm bắt đầu thuê " + thoiDiemHienTai.toString().substring(0,  16));
		y.setFont(Configs.REGULAR_FONT);
		y1.setFont(Configs.REGULAR_FONT);
		y2.setFont(Configs.REGULAR_FONT);
		y3.setFont(Configs.REGULAR_FONT);
		y4.setFont(Configs.REGULAR_FONT);
		y5.setFont(Configs.REGULAR_FONT);
		y5.setFont(Configs.REGULAR_FONT);
		y6.setFont(Configs.REGULAR_FONT);
		infor.getChildren().addAll(y, y1, y2, y3, y4, y5, y6);
	
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
