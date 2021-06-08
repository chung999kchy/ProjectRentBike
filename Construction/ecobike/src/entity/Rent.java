package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rent database table.
 * 
 */
@Entity
@NamedQuery(name="Rent.findAll", query="SELECT r FROM Rent r")
public class Rent implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RentPK id;

	@Column(name="end_time")
	private Timestamp endTime;

	@Column(name="start_time")
	private Timestamp startTime;

	//bi-directional many-to-one association to Bike
	@ManyToOne
	@JoinColumn(name="bikeId")
	private Bike bike;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	public Rent() {
	}

	public RentPK getId() {
		return this.id;
	}

	public void setId(RentPK id) {
		this.id = id;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Bike getBike() {
		return this.bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}