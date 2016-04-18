package ccc.drones_training.drone;

public class Target {

	private final Double x, y, z;

	public Target(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}

	public Double getZ() {
		return z;
	}

	@Override
	public String toString() {
		return "Target [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
}