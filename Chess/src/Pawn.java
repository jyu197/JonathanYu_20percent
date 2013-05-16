
public class Pawn extends Piece{
	
	public Pawn(String side){
		this.side = side;
	}
	
	public boolean isValidCapture(int origRow, int origCol, int destRow, int destCol){
		if(side.equals("white")){
			if(Math.abs(origCol - destCol) == 1 && origRow - destRow == 1) return true;
		}
		else if(side.equals("black")){
			if(Math.abs(origCol - destCol) == 1 && destRow - origRow ==1) return true;
		}
		
		return false;
	}
	
	public boolean isValidMove(int origRow, int origCol, int destRow, int destCol){
		if(origCol != destCol) return false;
		
		if(side.equals("white")){
			if(origRow == 6){
				if(origRow - destRow == 2) return true;
			}
			
			if(origRow - destRow == 1) return true;
		}
		else if(side.equals("black")){
			if(origRow == 1){
				if(destRow - origRow == 2) return true;
			}
			
			if(destRow - origRow == 1) return true;
		}
		
		return false;
	}
}
