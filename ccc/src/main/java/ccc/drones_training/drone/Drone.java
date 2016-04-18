package ccc.drones_training.drone;

public class Drone {

	private Integer droneId;

	private final Double weightInKg = 2.0;
	private final Double lengthInMeter = 1.0;
	private final Double widthInMeter = 1.0;
	private final Double heightInMeter = 0.5;

	private final Double veolicityMaxInMeterPerSecond = 14.0;

	private final Double rotorCount = 8.0;
	private final Double diameterOfRotorInMeter = 0.25;
	private final Double densityOfAir = 1.225;
	private final Double rotorConstant = 0.015;
	private final Double gravityConstant = 9.80665;

	public Drone(Integer droneId) {
		this.droneId = droneId;
	}

	public Double getThrottleToOvercomeGravity() {

		return getThrottleForThrust(gravityConstant);
	}

	public Double getThrottleForThrust(Double thrust) {

		double P = Math.sqrt((Math.pow(thrust / rotorCount, 3))
				/ ((Math.PI / 2) * Math.pow(diameterOfRotorInMeter, 2) * densityOfAir));

		return Math.pow(P, 1 / 3.2) / (Math.pow(rotorConstant, 1 / 3.2) * 10);
	}

	public Double getThrustForThrottle(Double throttle) {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((droneId == null) ? 0 : droneId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Drone other = (Drone) obj;
		if (droneId == null) {
			if (other.droneId != null)
				return false;
		} else if (!droneId.equals(other.droneId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Drone [droneId=" + droneId + "]";
	}
}