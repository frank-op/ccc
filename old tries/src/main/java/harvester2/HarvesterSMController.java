package harvester2;

public abstract class HarvesterSMController {

	private CornField field;
	private HarvesterSM harvesterSM;
	private Direction direction;

	public HarvesterSMController(CornField field, Direction direction) {
		this.direction = direction;
		this.setField(field);
		setHarvesterSM(new HarvesterSM());
	}

	public abstract String harvest();

	public CornField getField() {
		return field;
	}

	public void setField(CornField field) {
		this.field = field;
	}

	public HarvesterSM getHarvesterSM() {
		return harvesterSM;
	}

	public void setHarvesterSM(HarvesterSM harvesterSM) {
		this.harvesterSM = harvesterSM;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}