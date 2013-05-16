
public class Knight extends Piece{
	
	public Knight(String side){
		this.side = side;
	}
	
	public boolean isValidCapture(int origRow, int origCol, int destRow, int destCol){
		return isValidMove(origRow, origCol, destRow, destCol);
	}
	
	public boolean isValidMove(int origRow, int origCol, int destRow, int destCol){
		if(Math.abs(destRow - origRow) == 2 && Math.abs(destCol - origCol) == 1) return true;
		else if(Math.abs(destCol - origCol) == 2 && Math.abs(destRow - origRow) == 1) return true;
		else return false;
	}
}
