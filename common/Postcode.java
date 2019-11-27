package common;

import java.io.Serializable;

public class Postcode extends Model implements Serializable{

	private static final long serialVersionUID = -4314931141516985480L;
	private String name;
	private Double distance;
	
	public Postcode(String name, Double distance) {
		this.name = name;
		this.distance = distance;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public double getDistance() {
		return distance;
	}
}
