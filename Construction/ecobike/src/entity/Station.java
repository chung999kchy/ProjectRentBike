package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the station database table.
 * 
 */
@Entity
@NamedQuery(name="Station.findAll", query="SELECT s FROM Station s")
public class Station implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int stationId;

	private String address;

	//bi-directional many-to-one association to Bike
	@OneToMany(mappedBy="station")
	private List<Bike> bikes;

	public Station() {
	}

	public int getStationId() {
		return this.stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Bike> getBikes() {
		return this.bikes;
	}

	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}

	public Bike addBike(Bike bike) {
		getBikes().add(bike);
		//bike.setStation(this);

		return bike;
	}

	public Bike removeBike(Bike bike) {
		getBikes().remove(bike);
		//bike.setStation(null);

		return bike;
	}

}