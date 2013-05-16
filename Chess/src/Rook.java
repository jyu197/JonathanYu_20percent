
public class Rook extends Piece{
	
	public Rook(String side){
		this.side = side;
	}
	
	public boolean isValidCapture(int origRow, int origCol, int destRow, int destCol){
		return isValidMove(origRow, origCol, destRow, destCol);
	}
	
	public boolean isValidMove(int origRow, int origCol, int destRow, int destCol){
		if(destRow != origRow && destCol != origCol) return false;
		hasMoved = true;
		return true;
	}
}
