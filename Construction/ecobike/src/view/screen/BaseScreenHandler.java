package view.screen;

import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.util.Hashtable;
import java.util.List;

import controller.ThanhToanController;
import controller.XemXeDangThueController;
import entity.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import view.screen.baixe.KQTimKiemScreen;
import view.screen.thuexe.XemXeThueScreen;
import view.screen.traxe.TraXeScreen;

public class BaseScreenHandler extends FXMLScreenHandler {

	private Scene scene;
	private BaseScreenHandler prev;
	protected final Stage stage;
	protected Hashtable<String, String> messages;

	private BaseScreenHandler(String screenPath) throws IOException {
		super(screenPath);
		this.stage = new Stage();
	}

	public void setPreviousScreen(BaseScreenHandler prev) {
		this.prev = prev;
	}

	public BaseScreenHandler getPreviousScreen() {
		return this.prev;
	}

	public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
		super(screenPath);
		this.stage = stage;
	}

	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.content);
		}
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public void setScreenTitle(String string) {
		this.stage.setTitle(string);
	}

	public void forward(Hashtable messages) {
		this.messages = messages;
	}
	
	@FXML
    private TextField searchText;
	
	public void Search(ActionEvent event) {
    	String search = searchText.getText();
		/*
		 * if search ko valid
		 */
    	try {
			// initialize the scene
			KQTimKiemScreen screen = new KQTimKiemScreen(Configs.primaryStage, Configs.KQUA_TIMKIEM_PATH, search);
			screen.setScreenTitle("Kết quả tìm kiếm '"+search+"'");
			screen.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}    	
    }
	
	public void TraXee(ActionEvent event) {
		Bike bike = XemXeDangThueController.layXeDangThue(Configs.USER_ID);
		
		if (bike == null) {
			String message = "Bạn chưa thuê xe nào";
			
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
			TraXeScreen screen = new TraXeScreen(stage, Configs.TRA_XE_PATH);
			screen.setScreenTitle("Trả xe");
			screen.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void XemXEDangThue(ActionEvent event) {
		Bike bike = XemXeDangThueController.layXeDangThue(Configs.USER_ID);
		
		if (bike == null) {
			String message = "Bạn chưa thuê xe nào";
			
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
			XemXeThueScreen screen = new XemXeThueScreen(stage, Configs.XEM_XE_DANG_THUE);
			screen.setScreenTitle("Xem xe đang thuê");
			screen.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ReturnHome(MouseEvent event) {
		try {
			HomeScreen screen = new HomeScreen(stage, Configs.MAIN_PATH);
			screen.setScreenTitle("Home screen");
			screen.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
