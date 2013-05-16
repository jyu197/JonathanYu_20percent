
public class Board{
	Piece[][] board;
	int turnNumber = 0;
	//if turn number is even or 0, it is white's turn
	
	public Board(){
		this.board = new Piece[8][8];
		this.setBoard();
	}
	
	public void castle(int origRow, int origCol, int destRow, int destCol){
		if(Math.abs(destCol - origCol) != 2) return;
		if(board[origRow][origCol].getSide().equals("white") &&  !board[origRow][origCol].hasMoved()){
			if(destCol == 6){
				if(isEmpty(7,6) && isEmpty(7,5)){
					if(board[7][7] instanceof Rook && !board[7][7].hasMoved()){
						board[destRow][destCol] = board[origRow][origCol];
						board[destRow][destCol- 1] = board[7][7];
						
						board[origRow][origCol] = null;
						board[7][7] = null;
						
						board[destRow][destCol].moved();
						board[destRow][destCol - 1].moved();
						turnNumber++;
					}
				}
			}
			else if(destCol == 2){
				if(isEmpty(7,1) && isEmpty(7,2) && isEmpty(7,3)){
					if(board[7][0] instanceof Rook && !board[7][0].hasMoved()){
						board[destRow][destCol] = board[origRow][origCol];
						board[destRow][destCol+ 1] = board[7][0];
						
						board[origRow][origCol] = null;
						board[7][0] = null;
						
						board[destRow][destCol].moved();
						board[destRow][destCol + 1].moved();
						turnNumber++;
					}
				}
			}
		}
		else if(board[origRow][origCol].getSide().equals("black") &&  !board[origRow][origCol].hasMoved()){
			if(destCol == 6){
				if(isEmpty(0,6) && isEmpty(0,5)){
					if(board[0][7] instanceof Rook && !board[0][7].hasMoved()){
						board[destRow][destCol] = board[origRow][origCol];
						board[destRow][destCol- 1] = board[0][7];
						
						board[origRow][origCol] = null;
						board[0][7] = null;
						
						board[destRow][destCol].moved();
						board[destRow][destCol - 1].moved();
					}
				}
			}
			else if(destCol == 2){
				if(isEmpty(0,1) && isEmpty(0,2) && isEmpty(0,3)){
					if(board[0][0] instanceof Rook && !board[0][0].hasMoved()){
						board[destRow][destCol] = board[origRow][origCol];
						board[destRow][destCol+ 1] = board[0][0];
						
						board[origRow][origCol] = null;
						board[0][0] = null;
						
						board[destRow][destCol].moved();
						board[destRow][destCol + 1].moved();
					}
				}
			}
		}
	}
	
	public String checkForWinner(){
		boolean whiteHasKing = false;
		boolean blackHasKing = false;
		for(Piece[] i: board){
			for(Piece j: i){
				if(j instanceof King){
					if(j.getSide().equals("white")){
						whiteHasKing = true;
					}
					else{
						blackHasKing = true;
					}
				}
			}
		}
		
		if(whiteHasKing == false) return "black";
		else if(blackHasKing == false) return "white";
		else return "";
	}
	
	public int getCol(Piece p){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if(board[i][j] == p){
					return j;
				}
			}
		}
		return -1;
	}
	
	public int getRow(Piece p){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if(board[i][j] == p){
					return i;
				}
			}
		}
		return -1;
	}
	
	public boolean isEmpty(int row, int col){
		if(board[row][col] == null && isValid(row, col))return true;
		return false;
	}
	
	/*public Piece isChecked(){
		Piece whiteKing = null;
		Piece blackKing = null;
		for(Piece[] i: board){
			for(Piece j: i){
				if(j instanceof King){
					if(j.getSide().equals("white")) whiteKing = j;
					else blackKing = j;
				}
			}
		}
		for(Piece[] i: board){
			for(Piece j: i){
				if(j.getSide().equals("white")){
					if(j.isValidCapture(getRow(j), getCol(j), getRow(whiteKing), getCol(whiteKing))){
						return j;
						//white king is in check
						//returns the piece that is checking
					}
				}
				else{
					if(j.isValidCapture(getRow(j), getCol(j), getRow(blackKing), getCol(blackKing))){
						return j;
						//black king is in check
						//returns the piece that is checking
					}
				}
			}
		}
		return null;
	}
	
	public String isCheckMate(){
		Piece checkedKing = null;
		Piece checker = isChecked();
		
		if(checker.getSide().equals("white")){
			for(Piece[] i: board){
				for(Piece j: i){
					if(j instanceof King && j.getSide().equals("white")){
						checkedKing = j;
					}
				}
			}
		}
		else{
			for(Piece[] i: board){
				for(Piece j: i){
					if(j instanceof King && j.getSide().equals("black")){
						checkedKing = j;
					}
				}
			}
		}
		//determines which king is being checked
		
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if(checkedKing.isValidMove(getRow(checkedKing), getCol(checkedKing), i, j)){
					if(!checker.isValidMove(getRow(checker), getCol(checker), i, j)){
						return "";
					}
				}
			}
		}
		return checkedKing.getSide();
	}
	
	
	public boolean isStalemate(){
		if(turnNumber % 2 == 0){
			
		}
	}
	*/
	
	public boolean isKingNextToKing(int origRow, int origCol, int destRow, int destCol){
		//TODO Fix this for loop and the array index out of bound errors it throws
		for(int i = destCol - 1; i < destCol + 2; i++){
			for(int j = destRow - 1; j < destRow + 2; j++){
				if(!isValid(j, i)) continue;
				if(j == origRow && i == origCol) continue;
				if(board[j][i] instanceof King)return true;
			}
		}
		return false;
		/*
		if(board[destRow][destCol+1] instanceof King) return true;
		if(board[destRow+1][destCol] instanceof King) return true;
		if(board[destRow][destCol-1] instanceof King) return true;
		if(board[destRow-1][destCol] instanceof King) return true;
		if(board[destRow+1][destCol+1] instanceof King) return true;
		if(board[destRow-1][destCol-1] instanceof King) return true;
		if(board[destRow+1][destCol-1] instanceof King) return true;
		if(board[destRow-1][destCol+1] instanceof King) return true;
		return false;
		*/
	}
	
	public boolean isPathClear(int origRow, int origCol, int destRow, int destCol){
		//returns false if there is a piece in the way of the move that will stop the move
		Piece current = board[origRow][origCol];
		if(current instanceof Knight) return true;
		else if(current instanceof Pawn){
			if(current.getSide().equals("white")){
				if(origRow - destRow == 2){
					if(!isEmpty(destRow + 1, destCol)) return false;
					else return true;
				}
			}
			else{
				if(destRow - origRow == 2){
					if(!isEmpty(destRow - 1, destCol)) return false;
					else return true;
				}
			}
			if(Math.abs(origRow - destRow) == 1) return true;
		}
		else if(current instanceof King)return true;
		else if(current instanceof Rook){
			if(destRow > origRow){
				for(int i = origRow + 1 ; i < destRow; i++){
					if(!isEmpty(i, destCol)) return false;
				}
			}
			else if(origRow > destRow){
				for(int i = destRow + 1; i < origRow; i++){
					if(!isEmpty(i, destCol)) return false;
				}
			}
			else if(destCol > origCol){
				for(int i = origCol + 1; i < destCol; i++){
					if(!isEmpty(destRow, i)) return false;
				}
			}
			else if(origCol > destCol){
				for(int i = destCol + 1; i < origCol; i++){
					if(!isEmpty(destRow, i)) return false;
				}
			}
			return true;
		}
		else if(current instanceof Bishop){
			if(destRow > origRow && destCol > origCol){
				for(int i = origRow + 1, j = origCol + 1; i < destRow && j < destCol; i++, j++){
					if(!isEmpty(i, j))return false;
				}
			}
			else if(destRow > origRow && destCol < origCol){
				for(int i = origRow + 1, j = origCol - 1; i < destRow && j > destCol; i++, j--){
					if(!isEmpty(i, j))return false;
				}
			}
			else if(destRow < origRow && destCol > origCol){
				for(int i = origRow - 1, j = origCol + 1; i > destRow & j < destCol; i--, j++){
					if(!isEmpty(i, j))return false;
				}
			}
			else if(destRow < origRow && destCol < origCol){
				for(int i = origRow - 1, j = origCol - 1; i > destRow && j > destCol; i--, j--){
					if(!isEmpty(i, j))return false;
				}
			}
			return true;
		}
		else if(current instanceof Queen){
			if(destRow == origRow || destCol == origCol){
				if(destRow > origRow){
					for(int i = origRow + 1 ; i < destRow; i++){
						if(!isEmpty(i, destCol)) return false;
					}
				}
				else if(origRow > destRow){
					for(int i = destRow + 1; i < origRow; i++){
						if(!isEmpty(i, destCol)) return false;
					}
				}
				else if(destCol > origCol){
					for(int i = origCol + 1; i < destCol; i++){
						if(!isEmpty(destRow, i)) return false;
					}
				}
				else if(origCol > destCol){
					for(int i = destCol + 1; i < origCol; i++){
						if(!isEmpty(destRow, i)) return false;
					}
				}
				return true;
			}
			else if(destRow != origRow && destCol != origCol){
				if(destRow > origRow && destCol > origCol){
					for(int i = origRow + 1, j = origCol + 1; i < destRow && j < destCol; i++, j++){
						if(!isEmpty(i, j))return false;
					}
				}
				else if(destRow > origRow && destCol < origCol){
					for(int i = origRow + 1, j = origCol - 1; i < destRow && j > destCol; i++, j--){
						if(!isEmpty(i, j))return false;
					}
				}
				else if(destRow < origRow && destCol > origCol){
					for(int i = origRow - 1, j = origCol + 1; i > destRow & j < destCol; i--, j++){
						if(!isEmpty(i, j))return false;
					}
				}
				else if(destRow < origRow && destCol < origCol){
					for(int i = origRow - 1, j = origCol - 1; i > destRow && j > destCol; i--, j--){
						if(!isEmpty(i, j))return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean isValid(int row, int col){
		if(row > 7 || col > 7 || row < 0 || col < 0) return false;
		return true;
	}
	
	public boolean movePiece(int origRow, int origCol, int destRow, int destCol){
		if(!isValid(origRow, origCol) || !isValid(destRow, destCol)) return false;//invalid piece or invalid destination
		
		if(isEmpty(origRow, origCol)) return false;//no piece selected
		
		if(board[origRow][origCol] == board[destRow][destCol]) return false; //same spot selected
		
		if(turnNumber % 2 == 0){
			if(board[origRow][origCol].getSide() == "black") return false;
		}
		else{
			if(board[origRow][origCol].getSide() == "white") return false;
		}
		//above construct check to make sure the right side is moving
		
		if(board[origRow][origCol] instanceof King){
			//does special checks/moves for kings
			//TODO need to account for situation where king castles next to opposing king which is invalid
			if(isKingNextToKing(origRow, origCol, destRow, destCol)) return false;
			castle(origRow, origCol, destRow, destCol);
		}
		
		if(isEmpty(destRow, destCol)){
			if(board[origRow][origCol].isValidMove(origRow, origCol, destRow, destCol)){
				if(!isPathClear(origRow, origCol,destRow, destCol)) return false;
				board[destRow][destCol] = board[origRow][origCol];
				board[origRow][origCol] = null;
				if(board[destRow][destCol] instanceof Pawn) upgradePawn(destRow, destCol);//sees if the pawn needs to upgrade to a queen
				board[destRow][destCol].moved();
				turnNumber++;
				return true;
			}
			return false; //not a valid move
		}
		else{
			if(board[origRow][origCol].getSide().equals(board[destRow][destCol].getSide())) return false; //can't capture own pieces
			if(board[origRow][origCol].isValidCapture(origRow, origCol, destRow, destCol)){
				if(!isPathClear(origRow, origCol,destRow, destCol)) return false;
				board[destRow][destCol] = board[origRow][origCol];
				board[origRow][origCol] = null;
				board[destRow][destCol].moved();
				turnNumber++;
				return true;
			}
			return false;//not a valid capture
		}
	}

	 public void setBoard(){
		 board[7][0] = new Rook("white");
		 board[7][1] = new Knight("white");
		 board[7][2] = new Bishop("white");
		 board[7][4] = new King("white");
		 board[7][3] = new Queen("white");
		 board[7][5] = new Bishop("white");
		 board[7][6] = new Knight("white");
		 board[7][7] = new Rook("white");
		 
		 for(int i = 0; i < 8; i++){
			 board[6][i] = new Pawn("white");
			 board[1][i] = new Pawn("black");
		 }
		 
		 board[0][0] = new Rook("black");
		 board[0][1] = new Knight("black");
		 board[0][2] = new Bishop("black");
		 board[0][4] = new King("black");
		 board[0][3] = new Queen("black");
		 board[0][5] = new Bishop("black");
		 board[0][6] = new Knight("black");
		 board[0][7] = new Rook("black");
	}
	 
	public String[][] toDisplay(){
		String[][] boardWithPieces = new String[8][8];
		for(Piece[] i: board){
			for(Piece j: i){
				if(j instanceof Pawn) boardWithPieces[getRow(j)][getCol(j)] = "P";
				if(j instanceof King) boardWithPieces[getRow(j)][getCol(j)] = "Ki";
				if(j instanceof Knight) boardWithPieces[getRow(j)][getCol(j)] = "Kn";
				if(j instanceof Bishop) boardWithPieces[getRow(j)][getCol(j)] = "B";
				if(j instanceof Queen) boardWithPieces[getRow(j)][getCol(j)] = "Q";
				if(j instanceof Rook) boardWithPieces[getRow(j)][getCol(j)] = "R";
			}
		}
		return boardWithPieces;
	}
	 
	 public void upgradePawn(int destRow, int destCol){
		 if(destRow == 0){
				board[destRow][destCol] = new Queen("white");
			}
		else if(destRow == 7){
			board[destRow][destCol] = new Queen("black");
		}
	 }
}
