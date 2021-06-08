package view.screen.thuexe;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import bll.BikeBLL;
import controller.XemBaiXeController;
import controller.XemXeDangThueController;
import entity.Bike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Configs;
import view.screen.BaseScreenHandler;

public class XemXeThueScreen extends BaseScreenHandler implements Initializable {
	@FXML
	private VBox infor;
	
	public XemXeThueScreen(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);

		/*
		 * if (ThanhToanController.validate(xeId) == false) { c }
		 */
		
		Bike bike = XemXeDangThueController.layXeDangThue(Configs.USER_ID);
		Timestamp thoiDiemThue = XemXeDangThueController.layThoiDiemThue(Configs.USER_ID);
		// ten xe
		// gia thue moi gio
		// pin con lai
		// gia coc xe

		
		
		Text y, y2, y3, y4, y5, y6;
		y = new Text("Mã số xe "+ bike.getBikeId() + " tên xe " + bike.getBikeName());
		y2 = new Text("Lượng pin còn lại :" + (int) (bike.getPin() * 100) + "%");
		y3 = new Text("Giá thuê mỗi giờ " + (int)bike.getCategory().getCostPerHour() + Configs.CURRENCY);
		y4 = new Text("Mô tả về xe " + bike.getCategory().getNPedals() + " và có "+ bike.getCategory().getNSeats() + " chỗ ngồi  "+ bike.getCategory().getDescription());
		y5 = new Text("Tiền cọc " + (int) (bike.getPrice() * 0.3) + Configs.CURRENCY);
		y6 = new Text("Thời điểm bắt đầu thuê " + thoiDiemThue.toString().substring(0,  16));
		y.setFont(Configs.REGULAR_FONT);
		y2.setFont(Configs.REGULAR_FONT);
		y3.setFont(Configs.REGULAR_FONT);
		y4.setFont(Configs.REGULAR_FONT);
		y5.setFont(Configs.REGULAR_FONT);
		y6.setFont(Configs.REGULAR_FONT);
		infor.getChildren().addAll(y, y2, y3, y4, y5, y6);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
