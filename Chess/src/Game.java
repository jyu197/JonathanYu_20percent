import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class Game extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable table;
	Board board;
	boolean sourceClicked = false;
	int sourceRow = 0;
	int sourceCol = 0;
	int destRow;
	int destCol;
	
	public Game(){
		setLayout(new FlowLayout());

		board = new Board();
		
		table = new JTable(8,8){
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column){
				return false;
			};
		};
		table.setSize(new Dimension(1000, 1000));
		table.setFillsViewportHeight(true);
		table.setRowHeight(75);
		table.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		
		TableCellRenderer tcr = table.getDefaultRenderer(String.class);
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)tcr;
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.setCellSelectionEnabled(true);
		
		table.setGridColor(Color.BLACK);
		
		drawBoard();
		
		add(table);
		table.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent evt){
				if(sourceClicked == false){
					sourceRow = table.rowAtPoint(evt.getPoint());
					sourceCol = table.columnAtPoint(evt.getPoint());
					System.out.println("Source: " + sourceRow + " " + sourceCol);
					if(table.getValueAt(sourceRow, sourceCol) == null){
						return;
					}
					
					sourceClicked = true;
					System.out.println("Source clicked true");
				}
				else{
					destRow = table.rowAtPoint(evt.getPoint());
					destCol = table.columnAtPoint(evt.getPoint());
					System.out.println("Dest: " + destRow + " " + destCol);
					if(!board.movePiece(sourceRow, sourceCol, destRow, destCol)){
						System.out.println("asdlfkj adskl");
						//table.setCellSelectionEnabled(true);
						//table.setRowSelectionInterval(sourceRow, sourceRow);
						//table.setColumnSelectionInterval(sourceCol, sourceCol);
						//return;
					}
					sourceClicked = false;
					System.out.println("Source clicked false");
					System.out.println();
					drawBoard();
					table.clearSelection();
				}
			}
		});
		
	}
	
	public void drawBoard(){
		String[][] boardWithPieces = board.toDisplay();
		for(int i = 0; i < boardWithPieces.length; i++){
			for(int j = 0; j < boardWithPieces[i].length; j++){
				table.setValueAt(boardWithPieces[i][j], i, j);
			}
		}
	}
	
	public static void main(String[] args){
		Game gui = new Game();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(1000,1000);
		gui.setVisible(true);
		gui.setTitle("Chess");
	}
}
