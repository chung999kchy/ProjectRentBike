package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the rent database table.
 * 
 */
@Embeddable
public class RentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int userId;

	@Column(insertable=false, updatable=false)
	private int bikeId;

	public RentPK() {
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBikeId() {
		return this.bikeId;
	}
	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RentPK)) {
			return false;
		}
		RentPK castOther = (RentPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.bikeId == castOther.bikeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.bikeId;
		
		return hash;
	}
}