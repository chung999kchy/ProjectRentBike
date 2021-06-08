package view.screen.baixe;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import bll.StationBLL;
import controller.TimKiemBaiXeController;
import entity.Station;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Configs;
import view.screen.BaseScreenHandler;
import view.screen.HomeScreen;

public class KQTimKiemScreen extends BaseScreenHandler implements Initializable {

	@FXML
	private ScrollPane result;

	public KQTimKiemScreen(Stage stage, String screenPath, String keySearch) throws IOException {
		super(stage, screenPath);
		List<Station> stations = TimKiemBaiXeController.timKiemBaiXe(keySearch);

		if (stations.size() == 0) {
			Text t = new Text();
			t.setText("Không tìm thấy bãi xe ");
			t.setFont(Configs.REGULAR_FONT);
			t.setLayoutX(50);
			t.setLayoutY(100);
			result.setContent(t);
		} else {
			int idx = 0;
			Pane pane = new Pane();
			for (Station station : stations) {
				HBox hBox = new HBox();
				Text y = new Text();
				Button but = new Button();
				y.setText("Bãi xe số " + station.getStationId() + " có địa chỉ là " + station.getAddress());
				but.setText("Xem chi tiết");
				y.setFont(Configs.REGULAR_FONT);
				but.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try {
							BaiXeScreen screen = new BaiXeScreen(stage, Configs.GIAO_DIEN_BAI_XE_PATH, station.getStationId());
							screen.setScreenTitle("Bãi xe số "+station.getStationId());
							screen.show();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				hBox.setPadding(new Insets(20, 20, 10, 10));
				hBox.setLayoutX(25);
				hBox.setLayoutY(50 * idx);
				hBox.getChildren().addAll(but, y);
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
