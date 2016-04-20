package ccc.picturePuzzle.board;

public class Block {
	final int length;
	private final String color;

	public Block(int length, String color) {
		this.length = length;
		this.color = color;
	}

	public int getLength() {
		return length;
	}

	public String getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "Block [length=" + length + ", color=" + color + "]";
	}
}