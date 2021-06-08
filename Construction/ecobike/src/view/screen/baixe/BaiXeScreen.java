package view.screen.baixe;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import bll.BikeBLL;
import controller.XemBaiXeController;
import entity.Bike;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Configs;
import view.screen.BaseScreenHandler;
import view.screen.HomeScreen;
import view.screen.thuexe.NhapMaVachScreen;

public class BaiXeScreen extends BaseScreenHandler implements Initializable {
	
	@FXML
	private ScrollPane result;
	
	public BaiXeScreen(Stage stage, String screenPath, int id) throws IOException {
		super(stage, screenPath);
		List<Bike> bikes = XemBaiXeController.getBikesWithStationId(id);
		if (bikes.size() == 0) {
			Text t = new Text();
			t.setText("Không có xe trong bãi");
			t.setFont(Configs.REGULAR_FONT);
			t.setLayoutX(50);
			t.setLayoutY(100);
			result.setContent(t);
		}  else {
			int idx = 0;
			Pane pane = new Pane();
			for (Bike bike : bikes) {
				VBox hBox = new VBox();
				Text y = new Text();
				Text y1 = new Text();
				Text y2 = new Text();
				Text y3 = new Text();
				Button but = new Button();
				y.setFont(Configs.REGULAR_FONT);
				String tt = "khả dụng";
				if (bike.getStatus() == 0) tt = "không khả dụng";
				y.setText("Xe số " + bike.getBikeId() + " : "+ bike.getBikeName() + " bãi xe số "+ bike.getStation().getStationId()+ " địa chỉ " + bike.getStation().getAddress());
				y1.setText("Giá thuê mỗi giờ : " + bike.getCategory().getCostPerHour() + Configs.CURRENCY + "                       "+ "Loại xe "+bike.getCategory().getNPedals() + " bàn đạp và "+ bike.getCategory().getNSeats() +" chỗ ngồi.");
				y3.setText( "Trạng thái " + tt);
				y2.setText("Số pin còn lại : "+ (int)(bike.getPin()*100) + "%" );
				but.setText("Thuê xe");
				but.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try {
							NhapMaVachScreen screen = new NhapMaVachScreen(stage, Configs.NHAP_MA_VACH, bike.getBikeId());
							screen.setScreenTitle("Nhập mã vạch của xe xe");
							screen.show();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				hBox.setPadding(new Insets(20, 20, 10, 10));
				hBox.setPrefWidth(750);
				hBox.setLayoutX(25);
				hBox.setLayoutY(150*idx);
				hBox.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				hBox.getChildren().addAll(y, y1, y2, but);
				pane.getChildren().add(hBox);
				idx++;
			}
			result.setContent(pane);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}