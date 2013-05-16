
public class King extends Piece{
	
	public King(String side){
		this.side = side;
	}
	
	public boolean isValidCapture(int origRow, int origCol, int destRow, int destCol){
		return isValidMove(origRow, origCol, destRow, destCol);
	}
	
	public boolean isValidMove(int origRow, int origCol, int destRow, int destCol){
		if(Math.abs(destRow - origRow) == 1 || Math.abs(destCol - origCol) == 1){
			hasMoved = true;
			return true;
		}
		return false;
	}
}
