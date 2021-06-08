package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int transactionId;

	@Column(name="bike_name")
	private String bikeName;

	@Column(name="rent_duration")
	private String rentDuration;

	private Timestamp time;

	@Column(name="total_payment")
	private float totalPayment;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	public Transaction() {
	}

	public int getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getBikeName() {
		return this.bikeName;
	}

	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}

	public String getRentDuration() {
		return this.rentDuration;
	}

	public void setRentDuration(String rentDuration) {
		this.rentDuration = rentDuration;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public float getTotalPayment() {
		return this.totalPayment;
	}

	public void setTotalPayment(float totalPayment) {
		this.totalPayment = totalPayment;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}