package models;

public interface Draggable {

	public void select(int x, int y);
	public void unselect();
	public void drag(int x, int y);
	
}
