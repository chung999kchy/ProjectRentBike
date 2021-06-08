package view.screen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ThongBaoScreen extends BaseScreenHandler implements Initializable{
	@FXML
	private Text noticeText;

	public ThongBaoScreen(Stage stage, String screenPath, String message) throws IOException {
		super(stage, screenPath);
		noticeText.setText(message);
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
