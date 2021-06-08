package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int categoryId;

	@Column(name="cost_per_hour")
	private float costPerHour;

	private String description;

	@Column(name="n_pedals")
	private int nPedals;

	@Column(name="n_seats")
	private int nSeats;

	private String name;

	//bi-directional many-to-one association to Bike
	@OneToMany(mappedBy="category")
	private List<Bike> bikes;

	public Category() {
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public float getCostPerHour() {
		return this.costPerHour;
	}

	public void setCostPerHour(float costPerHour) {
		this.costPerHour = costPerHour;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNPedals() {
		return this.nPedals;
	}

	public void setNPedals(int nPedals) {
		this.nPedals = nPedals;
	}

	public int getNSeats() {
		return this.nSeats;
	}

	public void setNSeats(int nSeats) {
		this.nSeats = nSeats;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Bike> getBikes() {
		return this.bikes;
	}

	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}

	public Bike addBike(Bike bike) {
		getBikes().add(bike);
		//bike.setCategory(this);

		return bike;
	}

	public Bike removeBike(Bike bike) {
		getBikes().remove(bike);
		//bike.setCategory(null);

		return bike;
	}

}