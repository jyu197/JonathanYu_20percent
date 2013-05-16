
public class Bishop extends Piece{
	
	public Bishop(String side){
		this.side = side;
	}
	
	public boolean isValidCapture(int origRow, int origCol, int destRow, int destCol){
		return isValidMove(origRow, origCol, destRow, destCol);
	}
	
	public boolean isValidMove(int origRow, int origCol, int destRow, int destCol){
		if(destRow == origRow || destCol == origCol) return false;
		if(Math.abs(destRow - origRow) == Math.abs(destCol - origCol)) return true;
		return false;
	}
}
