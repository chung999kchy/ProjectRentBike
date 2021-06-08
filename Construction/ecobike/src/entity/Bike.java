package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the bike database table.
 * 
 */
@Entity
@NamedQuery(name="Bike.findAll", query="SELECT b FROM Bike b")
public class Bike implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int bikeId;

	private String bikeName;

	private float pin;

	private int status;
	
	private Category category;

	private Station station;
	
	private int price;

	//bi-directional many-to-one association to Rent
	@OneToMany(mappedBy="bike")
	private List<Rent> rents;

	public Bike() {
	}

	public int getBikeId() {
		return this.bikeId;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public String getBikeName() {
		return this.bikeName;
	}

	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}

	public float getPin() {
		return this.pin;
	}

	public void setPin(float pin) {
		this.pin = pin;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public List<Rent> getRents() {
		return this.rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	public Rent addRent(Rent rent) {
		getRents().add(rent);
		rent.setBike(this);
		return rent;
	}

	public Rent removeRent(Rent rent) {
		getRents().remove(rent);
		rent.setBike(null);

		return rent;
	}

}