package common;

import java.io.Serializable;

public class Supplier extends Model implements Serializable{
	
	private static final long serialVersionUID = -7213276821022762444L;
	private String name;
	private Double distance;
	
	public Supplier (String name, Double distance) {
		this.name = name;
		this.distance = distance;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public Double getDistance() {
		return distance;
	}
}
