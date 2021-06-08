package checkout;

import java.util.List;
import bll.RentBLL;
import entity.Rent;

public class Test {
	public static void main(String[] args) {
		RentBLL rentBLL = new RentBLL();
		List<Rent> allRents = rentBLL.getAllProducts();
		for (Rent rent : allRents) {
			System.out.println(rent.getStartTime().toString());
			System.out.println(rent.getId().getBikeId());
			System.out.println(rent.getId().getUserId());
		}
		System.out.println("----------");
		rentBLL.insert(1, 2);
		allRents = rentBLL.getAllProducts();
		for (Rent rent : allRents) {
			System.out.println(rent.getStartTime().toString());
			System.out.println(rent.getId().getBikeId());
			System.out.println(rent.getId().getUserId());
		}
	}
}
