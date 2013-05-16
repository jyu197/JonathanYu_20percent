
public abstract class Piece {
	protected String side;
	protected boolean hasMoved = false;
	
	public String getSide(){
		return side;
	}
	
	public boolean hasMoved(){
		return hasMoved;
	}
	
	public abstract boolean isValidCapture(int origRow, int origCol, int destRow, int destCol);
	
	public abstract boolean isValidMove(int origRow, int origCol, int destRow, int destCol);
	
	public void moved(){
		this.hasMoved = true;
	}
}
