package ccc.drone.drone;

public class Drone {

	public final static double HOVERING_THROTTLE = 0.569052216728;

	private Integer droneId;

	private Double weightInKg = 2.0;
	private Double lengthInMeter = 1.0;
	private Double widthInMeter = 1.0;
	private Double heightInMeter = 0.5;
	private Double rotorCount = 8.0;
	private Double diameterOfRotorInMeter = 0.25;
	private Double veolicityMaxInMeterPerSecond = 14.0;

	private DroneController droneController = new DroneController();

	public Drone(Integer droneId) {
		this.droneId = droneId;
	}

	public void flyToZCoordinate(double z) {
		droneController.sendDroneToMinZ(this.droneId, z);
	}

	public Double getThrustForThrottle(double throttle) {

		double powerOfRotor = 0.015 * Math.pow((throttle * 10), 3.2);

		double thrust = Math.cbrt(
				(Math.PI / 2) * diameterOfRotorInMeter * diameterOfRotorInMeter * 1.225 * powerOfRotor * powerOfRotor);

		return thrust * 8;
	}

	public Integer getDroneId() {
		return droneId;
	}

	public Double getWeightInKg() {
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

	public Double getRotorCount() {
		return rotorCount;
	}

	public Double getDiameterOfRotorInMeter() {
		return diameterOfRotorInMeter;
	}

	public Double getVeolicityMaxInMeterPerSecond() {
		return veolicityMaxInMeterPerSecond;
	}
}