package ccc.drones.drone;

public class Drone {

	private Integer droneId;

	private final Double weightInKg = 2.0;
	private final Double lengthInMeter = 1.0;
	private final Double widthInMeter = 1.0;
	private final Double heightInMeter = 0.5;

	private final Double veolicityMaxInMeterPerSecond = 14.0;

	private final Double rotorCount = 8.0;
	private final Double diameterOfRotorInMeter = 0.25;
	private Double densityOfAir = 1.225;
	private final Double rotorConstant = 0.015;
	private Double gravityConstant = 9.80665;

	private DroneController droneController = new DroneController();

	public Drone(Integer droneId) {
		this.droneId = droneId;
	}

	public void flyToZCoordinate(double z) {
		droneController.sendDroneToMinZ(this, z);
	}

	public Double getThrottleToOvercomeGravity() {

		return getThrottleForThrust(gravityConstant);
	}

	public Double getThrottleForThrust(double thrust) {

		double P = Math.sqrt((Math.pow(thrust / rotorCount, 3))
				/ ((Math.PI / 2) * Math.pow(diameterOfRotorInMeter, 2) * densityOfAir));

		return Math.pow(P, 1 / 3.2) / (Math.pow(rotorConstant, 1 / 3.2) * 10);
	}

	public Double getThrustForThrottle(double throttle) {

		Double powerOfRotor = rotorConstant * Math.pow((throttle * 10), 3.2);

		Double thrust = Math.cbrt(
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