package ccc.drones.sim;

public class Status {

	private Double x, y, z, vx, vy, vz, rx, ry, rz;

	public Status(String statusAsString) {

		String[] split = statusAsString.split(" ");

		this.x = Double.parseDouble(split[0]);
		this.y = Double.parseDouble(split[1]);
		this.z = Double.parseDouble(split[2]);
		this.vx = Double.parseDouble(split[3]);
		this.vy = Double.parseDouble(split[4]);
		this.vz = Double.parseDouble(split[5]);
		this.rx = Double.parseDouble(split[6]);
		this.ry = Double.parseDouble(split[7]);
		this.rz = Double.parseDouble(split[8]);
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

	public Double getVx() {
		return vx;
	}

	public Double getVy() {
		return vy;
	}

	public Double getVz() {
		return vz;
	}

	public Double getRx() {
		return rx;
	}

	public Double getRy() {
		return ry;
	}

	public Double getRz() {
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