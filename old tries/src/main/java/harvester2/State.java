package harvester2;

public interface State {

	Cell moveToNewPositionAndGetIt(CornField field) throws CantGoThereException;
}