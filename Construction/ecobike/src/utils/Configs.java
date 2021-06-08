package utils;

import java.util.Map;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Configs {
	public static String CURRENCY = "VND";
	public static float PERCENT_VAT = 10;
	public static Stage primaryStage;
	// static resource
	public static final String IMAGE_PATH = "src/view/images";
	public static final String MAIN_PATH = "/view/fxml/home.fxml";
	public static final String NHAP_MA_VACH = "/view/fxml/NhapMaVach.fxml";
	public static final String THANH_TOAN = "/view/fxml/ThanhToan.fxml";
	public static final String RESULT_TRA_XE_PATH = "/view/fxml/ResultTraXe.fxml";
	public static final String SPLASH_SCREEN_PATH = "/view/fxml/splashScreen.fxml";
	public static final String TRA_XE_PATH = "/view/fxml/TraXe.fxml";
	public static final String KQUA_TIMKIEM_PATH = "/view/fxml/kquaTimkiem.fxml";
	public static final String GIAO_DIEN_BAI_XE_PATH = "/view/fxml/giaoDienBaiXe.fxml";
	public static final String THONG_BAO = "/view/fxml/ThongBao.fxml";
	public static final String XEM_XE_DANG_THUE = "/view/fxml/XemXeDangThue.fxml";
	public static Font REGULAR_FONT = Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 24);
	
	// static resoures for Interbank API
	public static final String BASE_URL = "https://ecopark-system-api.herokuapp.com";
	public static final String SECRET_KEY = "BAVQL5a7/+4=";
	public static final String APP_CODE = "BR5kO1iEVkk=";
	public static final String VERSION = "1.0.1";
	public static final Map<String, String> ERROR_CODE = Map.of(
		"00", "Giao dịch thành công",
		"01", "Thẻ không hợp lệ",
		"02", "Thẻ không đủ số dư",
		"03", "Internal Server Error",
		"04", "Giao dịch bị nghi ngờ gian lận",
		"05", "Không đủ thông tin giao dịch",
		"06", "Thiếu thông tin version",
		"07", "Amount không hợp lệ"
	);
	public static final int USER_ID=1;
	public static final String USERNAME = "CHUNGDACDAO";
}
