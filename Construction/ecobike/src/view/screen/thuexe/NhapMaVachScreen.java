package view.screen.thuexe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.NhapMaVachController;
import controller.XemXeDangThueController;
import entity.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import view.screen.BaseScreenHandler;
import view.screen.HomeScreen;
import view.screen.ThongBaoScreen;

public class NhapMaVachScreen extends BaseScreenHandler implements Initializable{
	
	@FXML
	private TextField barcodeXe;
	
	public void Submit(ActionEvent event) {
		String barcode = barcodeXe.getText();
		Bike bike = NhapMaVachController.getBikeWithId(barcode);
		Bike bikeDangThue = XemXeDangThueController.layXeDangThue(Configs.USER_ID);
		if (bikeDangThue != null) {
			String message = "Bạn đang thuê một xe khác";
			try {
				ThongBaoScreen screen = new ThongBaoScreen(stage, Configs.THONG_BAO, message);
				screen.setScreenTitle("Bạn đang thuê một xe khác");
				screen.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		if (bike != null) {
			try {
				ThanhToanScreen screen = new ThanhToanScreen(stage, Configs.THANH_TOAN, bike);
				screen.setScreenTitle("Xác nhận thanh toán");
				screen.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			// chuyen sang loi
			String message = "Không tìm thấy barcode thỏa mãn";
			try {
				ThongBaoScreen screen = new ThongBaoScreen(stage, Configs.THONG_BAO, message);
				screen.setScreenTitle("Không tìm thấy xe thỏa mãn");
				screen.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public NhapMaVachScreen(Stage stage, String screenPath, int id) throws IOException {
		super(stage, screenPath);
		String idd = id + "";
		barcodeXe.setText(idd);
	}	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
