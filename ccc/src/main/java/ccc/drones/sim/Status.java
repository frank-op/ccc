package ccc.drones.sim;

import ccc.drones.drone.Drone;

public class Status {

	private final Double x, y, z, vx, vy, vz, rx, ry, rz;
	private final Double currentTime;
	private final Drone drone;

	public Status(String statusAsString, Double currentTime, Drone drone) {

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

		this.currentTime = currentTime;
		this.drone = drone;
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

	public Double getCurrentTime() {
		return currentTime;
	}

	public Drone getDrone() {
		return drone;
	}

	@Override
	public String toString() {
		return "Status [x=" + x + ", y=" + y + ", z=" + z + ", vx=" + vx + ", vy=" + vy + ", vz=" + vz + ", rx=" + rx
				+ ", ry=" + ry + ", rz=" + rz + ", currentTime=" + currentTime + ", drone=" + drone + "]";
	}
}