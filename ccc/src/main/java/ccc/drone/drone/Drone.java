package ccc.drone.drone;

public class Drone {

	private Integer droneId;

	private Integer weightInKg = 2;

	private Double lengthInMeter = 1.0;
	private Double widthInMeter = 1.0;
	private Double heightInMeter = 0.5;

	private Integer rotorCount = 8;

	private Double diameterOfRotorInMeter = 0.25;

	private Integer veolicityMaxInMeterPerSecond = 14;

	public Drone(Integer droneId) {
		this.droneId = droneId;
	}

	public Double getThrust(double d) {

		double powerOfRotor = 0.015 * Math.pow((d * 10), 3.2);

		double thrust = Math.cbrt(
				(Math.PI / 2) * diameterOfRotorInMeter * diameterOfRotorInMeter * 1.225 * powerOfRotor * powerOfRotor);

		return thrust;
	}

	public Integer getDroneId() {
		return droneId;
	}

	public Integer getWeightInKg() {
		return weightInKg;
	}

	public Double getLengthInMeter() {
		return lengthInMeter;
	}

	public Double getWidthInMeter() {
		return widthInMeter;
	}

	public Double getHeightInMeter() {
		return heightInMeter;
	}

	public Integer getRotorCount() {
		return rotorCount;
	}

	public Double getDiameterOfRotorInMeter() {
		return diameterOfRotorInMeter;
	}

	public Integer getVeolicityMaxInMeterPerSecond() {
		return veolicityMaxInMeterPerSecond;
	}
}