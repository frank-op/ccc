package ccc.harvester.scenarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import ccc.harvester.OutputFormatter;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;
import ccc.harvester.steps.HarvestStep;
import ccc.harvester.steps.HarvestStep.Alignment;

public class SerpentineScenario extends Scenario {

	public SerpentineScenario(List<HarvestStep> steps) {
		super(steps);
	}

	@Override
	public String executeSteps(CornField field, int row, int column) {
		return executeStepsSerpentine(field, field.getCell(row, column));
	}

	private String executeStepsSerpentine(CornField field, Cell startCell) {
		List<Cell> cells = executeStepsSerpentineAndGetCells(field, startCell);
		return OutputFormatter.getFormattedContent(cells);
	}

	private List<Cell> executeStepsSerpentineAndGetCells(CornField field, Cell startCell) {
		LinkedHashSet<Cell> cells = new LinkedHashSet<>();

		iterateScenarioSerpentine(field, cells, startCell);
		return new ArrayList<>(cells);
	}

	private void iterateScenarioSerpentine(CornField field, Collection<Cell> cells, Cell currentCell) {
		for (HarvestStep harvestStep : getSteps()) {

			List<Cell> resultOfStep = harvestStep.doIt(currentCell);

			resultOfStep = fixEmptyCells(field, harvestStep, resultOfStep);

			if (resultOfStep.isEmpty()) {
				return;
			}
			currentCell = resultOfStep.get(resultOfStep.size() - 1);
			cells.addAll(resultOfStep);
		}
		if (cells.size() >= field.size()) {
			return;
		} else {
			iterateScenarioSerpentine(field, cells, currentCell);
		}
	}

	private List<Cell> fixEmptyCells(CornField field, HarvestStep harvestStep, List<Cell> resultOfStep) {

		if (Scenario.isFixEmptyCells()) {
			if (harvestStep.getMowers() > 1) {
				if (harvestStep.getAlignment() == Alignment.VERTICAL && resultOfStep.size() == field.getRows()
						|| harvestStep.getAlignment() == Alignment.HORIZONTAL
								&& resultOfStep.size() == field.getColumns()) {

					List<Cell> temp = new ArrayList<>();
					for (Cell cell : resultOfStep) {
						temp.add(cell);
						temp.add(new Cell(0, 0, 0, field));
					}
					resultOfStep = temp;
				}
			}
		}
		return resultOfStep;
	}
}