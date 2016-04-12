package ccc.harvester.scenarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import ccc.harvester.exec.ExecuteParams;
import ccc.harvester.exec.OutputFormatter;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;
import ccc.harvester.steps.HarvestStep;

public class SerpentineScenario extends Scenario {

	public SerpentineScenario(List<HarvestStep> steps) {
		super(steps);
	}

	@Override
	public String executeSteps(ExecuteParams params) {
		CornField field = new CornField(params.getFieldRows(), params.getFieldCols());

		System.out.println(field);

		Cell startCell = field.getCell(params.getStartCellRow(), params.getStartCellCol());

		List<Cell> cells = executeStepsSerpentineAndGetCells(field, startCell);
		String formattedContent = OutputFormatter.getFormattedContent(cells);
		System.out.println(formattedContent + "\n\n");
		return formattedContent;
	}

	private List<Cell> executeStepsSerpentineAndGetCells(CornField field, Cell startCell) {
		LinkedHashSet<Cell> cells = new LinkedHashSet<>();

		iterateScenarioSerpentine(field, cells, startCell);
		return new ArrayList<>(cells);
	}

	private void iterateScenarioSerpentine(CornField field, Collection<Cell> cells, Cell currentCell) {
		for (HarvestStep harvestStep : getSteps()) {

			List<Cell> resultOfStep = harvestStep.doIt(field, currentCell);

			currentCell = harvestStep.getLastCell();
			cells.addAll(resultOfStep);
			if (cells.size() >= field.size()) {
				return;
			}
		}
		iterateScenarioSerpentine(field, cells, currentCell);
	}
}