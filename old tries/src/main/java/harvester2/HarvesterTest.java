package harvester2;

import org.testng.annotations.Test;

public class HarvesterTest {

	public void testLevel3() {
		// 3 4 1 1 S
		CornField field = new CornField(3, 4, new Cell(1, 1));
		HarvesterSMController harvesterSMController = new HarvesterSMControllerSerpentine(field, Direction.SOUTH);
		String harvest = harvesterSMController.harvest();
		System.out.println(harvest);
		System.out.println(field.toString());

		// 2 5 1 5 S
		field = new CornField(2, 5, new Cell(1, 5));
		harvesterSMController = new HarvesterSMControllerSerpentine(field, Direction.SOUTH);
		harvest = harvesterSMController.harvest();
		System.out.println(harvest);
		System.out.println(field.toString());

		// 5 2 5 2 N
		field = new CornField(5, 2, new Cell(5, 2));
		harvesterSMController = new HarvesterSMControllerSerpentine(field, Direction.NORTH);
		harvest = harvesterSMController.harvest();
		System.out.println(harvest);
		System.out.println(field.toString());

		// 23 12 23 1 N
		field = new CornField(23, 12, new Cell(23, 1));
		harvesterSMController = new HarvesterSMControllerSerpentine(field, Direction.NORTH);
		harvest = harvesterSMController.harvest();
		System.out.println(harvest);
		System.out.println(field.toString());
	}

	public void testLevel4() {

		// 3 4 1 4 S Z
		CornField field = new CornField(3, 4, new Cell(1, 4));
		HarvesterSMController harvesterSMController = new HarvesterSMControllerCircular(field, Direction.SOUTH);
		String harvest = harvesterSMController.harvest();
		System.out.println(harvest);
		System.out.println(field.toString());

		// 2 5 2 1 N S
		field = new CornField(2, 5, new Cell(2, 1));
		harvesterSMController = new HarvesterSMControllerSerpentine(field, Direction.NORTH);
		harvest = harvesterSMController.harvest();
		System.out.println(harvest);
		System.out.println(field.toString());

		// 5 2 5 2 N Z
		field = new CornField(5, 2, new Cell(5, 2));
		harvesterSMController = new HarvesterSMControllerCircular(field, Direction.NORTH);
		harvest = harvesterSMController.harvest();
		System.out.println(harvest);
		System.out.println(field.toString());

		// 23 12 23 1 N Z
		field = new CornField(23, 12, new Cell(23, 1));
		harvesterSMController = new HarvesterSMControllerCircular(field, Direction.NORTH);
		harvest = harvesterSMController.harvest();
		System.out.println(harvest);
		System.out.println(field.toString());
	}

	@Test
	public void testLevel5() {

		// todo write split controller

		CornField field;
		HarvesterSMController harvesterSMController;

		// s: 1 5 2 6 3 7 4 8 16 12 15 11 14 10 13 9 17 18 19 20
		// 5 4 1 1 O S 2
		field = new CornField(5, 4, new Cell(1, 1));
		harvesterSMController = new HarvesterSMControllerSerpentine(field, Direction.EAST);
		String harvest = harvesterSMController.harvest();
		System.out.println(harvest);
		System.out.println(field.toString());

		// z: 13 17 14 18 15 19 16 20 8 4 7 3 6 2 5 1 9 10 11 12
		// 5 4 4 1 O Z 2
		field = new CornField(5, 4, new Cell(5, 1));
		harvesterSMController = new HarvesterSMControllerCircular(field, Direction.EAST);
		harvest = harvesterSMController.harvest();
		System.out.println(harvest);
		System.out.println(field.toString());
		//
		// // 10 10 10 10 W S 1
		// field = new CornField(10, 10, new Cell(10, 10));
		// harvesterSMController = new HarvesterSMControllerSerpentine(field,
		// Direction.WEST, 1);
		// harvest = harvesterSMController.harvest();
		// System.out.println(harvest);
		// System.out.println(field.toString());
		//
		// // 10 10 10 10 W S 2
		// field = new CornField(10, 10, new Cell(10, 10));
		// harvesterSMController = new HarvesterSMControllerSerpentine(field,
		// Direction.WEST, 2);
		// harvest = harvesterSMController.harvest();
		// System.out.println(harvest);
		// System.out.println(field.toString());
		//
		// // 17 9 17 1 N Z 2
		// field = new CornField(17, 9, new Cell(17, 1));
		// harvesterSMController = new HarvesterSMControllerCircular(field,
		// Direction.NORTH);
		// harvest = harvesterSMController.harvest();
		// System.out.println(harvest);
		// System.out.println(field.toString());
	}
}