package ccc.drone.drone;

public class Status {

	private double x, y, z, vx, vy, vz, rx, ry, rz;

	Status(String statusAsString) {

		String[] split = statusAsString.split(" ");
		this.z = Double.parseDouble(split[0]);

		// this.x = Double.parseDouble(split[0]);
		// this.y = Double.parseDouble(split[1]);
		// this.z = Double.parseDouble(split[2]);
		// this.vx = Double.parseDouble(split[3]);
		// this.vy = Double.parseDouble(split[4]);
		// this.vz = Double.parseDouble(split[5]);
		// this.rx = Double.parseDouble(split[6]);
		// this.ry = Double.parseDouble(split[7]);
		// this.rz = Double.parseDouble(split[8]);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public double getVz() {
		return vz;
	}

	public double getRx() {
		return rx;
	}

	public double getRy() {
		return ry;
	}

	public double getRz() {
		return rz;
	}

	@Override
	public String toString() {
		return "Status [x=" + x + ", y=" + y + ", z=" + z + ", vx=" + vx + ", vy=" + vy + ", vz=" + vz + ", rx=" + rx
				+ ", ry=" + ry + ", rz=" + rz + "]";
	}

	public static Status success() {
		return null;
	}
}