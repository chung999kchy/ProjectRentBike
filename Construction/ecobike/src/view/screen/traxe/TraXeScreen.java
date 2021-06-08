package view.screen.traxe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bll.BikeBLL;
import bll.StationBLL;
import entity.Bike;
import entity.Station;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import view.screen.BaseScreenHandler;
import view.screen.ThongBaoScreen;

public class TraXeScreen extends BaseScreenHandler implements Initializable{
	@FXML
	private TextField barcodeXe;
	
	@FXML
	private TextField barcodeBaiXe;
	
	public void SubmitTra(ActionEvent event) {
		String codeXe = barcodeXe.getText();
		String codeBaiXe = barcodeBaiXe.getText();
		
		BikeBLL bikeBLL = new BikeBLL();
		StationBLL stationBLL = new StationBLL();
		
		Bike bike = bikeBLL.find(codeXe);
		Station station = stationBLL.find(codeBaiXe);
		
		if (bike == null || station == null || bike.getStatus() != 1) {
			String message = "Mã vạch của xe hoặc bãi xe không đúng";
			
			try {
				ThongBaoScreen screen = new ThongBaoScreen(stage, Configs.THONG_BAO, message);
				screen.setScreenTitle("Thông báo");
				screen.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		try {
			TraXeDetailScreen screen = new TraXeDetailScreen(stage, Configs.RESULT_TRA_XE_PATH, bike, station);
			screen.setScreenTitle("Thông tin trả xe");
			screen.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public TraXeScreen(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		

		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
