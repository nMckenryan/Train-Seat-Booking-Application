package trainSeatBookingApp;

//import java.util.Arrays;

public abstract class FloorGrid {
	protected int gridCols;
	protected int gridRows;
	protected int midRows;
	protected int firstClassRows;
	protected Seat[][] seatGrid; //Creates Grid. First array is Columns, Second is Rows.
	public abstract void initialiseFloorGrid();

	//CONSTRUCTORs
	public FloorGrid(int cols, int rows, int firstClassRows) {
		this.seatGrid = new Seat[cols][rows];
		this.firstClassRows = firstClassRows;
	}

	public FloorGrid() {
		this.seatGrid = new Seat[10][10];
		this.firstClassRows = 4;
	}

	//RETRIEVES INDIVIDUAL SEAT
	public Seat getSeat(char col, int row) {
		Seat gotSeat = seatGrid[indexToCol(col)][row];
		return gotSeat;
	}

	//GETTING ADJACENT SEATS (Columns)
	public Seat getLeft(Seat lSeat) {
		if(lSeat.getSeatPos().seatCol > 0 && lSeat.getSeatPos().seatCol < gridCols) {
			Seat.SeatPosition leftSeatPos = lSeat.getSeatPos();
			leftSeatPos.seatCol -= 1; //Gets column one to the left of selected seat.
			lSeat.setSeatPos(leftSeatPos);
			return lSeat;
		}
		else {
			return null;
		}
	}

	public Seat getRight(Seat rSeat) {
		if(convertColToIndex(rSeat.getSeatPos().seatCol) > 0 && convertColToIndex(rSeat.getSeatPos().seatCol) < gridCols) {
			Seat.SeatPosition rightSeatPos = rSeat.getSeatPos();
			rightSeatPos.seatCol += 1; //Gets one column to the right of selected seat.
			rSeat.setSeatPos(rightSeatPos);
			return rSeat;
		}
		else {
			return null;
		}	
	}

	//GETTING THE LAST ROW AND LAST COLUMN
	public char getLastCol() {
		char lastCol = (char) seatGrid.length;
		return lastCol;
	}

	public int getLastRow() {
		int firstMax = seatGrid.length;
		int lastRow = seatGrid[firstMax].length;
		return lastRow;
	}

	//GET SEAT GRID
	public Seat[][] getSeatGrid() {
		return seatGrid;
	}



	//CONVERTING ROWS AND COLUMNS TO WORKABLE INTS
	public int convertRowToIndex(int row) {
		int rIndex = row - 1;
		return rIndex;
	}

	public int convertColToIndex(char col) {
		int cIndex = Character.getNumericValue(col);
		return cIndex;
	}

	public char indexToCol(int col) {
		char iCol = (char)col;
		return iCol;
	}

	//QUERYING AVAILABLE ECO SEATS
	public Seat queryAvailableEconomySeat(SeatType seatType) {
		Seat econSeat = new Seat();
		econSeat.setSeatType(seatType);
		for(int x = 0; x < gridCols; x++) {
			for(int y = 0; y < gridRows; y++) {
				if(econSeat.getSeatType() == seatGrid[x][y].getSeatType() && seatGrid[x][y].getSeatClass() == "Economy" && seatGrid[x][y].isBooked() == true) {
					econSeat.setSeatType(null);
				}
			}
		} return econSeat;
	}

	//QUERYING AVAILABLE FIRST CLASS SEATS 
	public Seat queryAvailableFirstClassSeat(SeatType seatType) {
		Seat firstSeat = new Seat();
		firstSeat.setSeatType(seatType);
		for(int x = 0; x < gridCols; x++) {
			for(int y = 0; y < gridRows; y++) {
				if(firstSeat.getSeatType() == seatGrid[x][y].getSeatType() && seatGrid[x][y].getSeatClass() == "First Class" && seatGrid[x][y].isBooked() != false) {
					firstSeat.setSeatType(null);
				}
			}
		} return firstSeat;
	}

	public String getSeatClassFromGrid(int cols, int rows) { //gets Seat Class from Grid Position
		return this.seatGrid[indexToCol(cols)][rows].getSeatClass();
	}

	public SeatType getSeatTypeFromGrid(int cols, int rows) { //gets SeatType from Grid Position
		return this.seatGrid[indexToCol(cols)][rows].getSeatType();
	}

	public String bookedCheck(int cols, int rows) { //checks if Seat is booked.
		if(this.seatGrid[indexToCol(cols)][rows].isBooked() == true) {
			return "X";
		}
		else {
			return " ";
		}
	}

	@Override
	public String toString() { 
		String gridString = "START "; //DECLARES A gridString BASE STRING, WHICH IS ADDED TO AS THE LOOP PROGRESSES

		//GETTING LAST COLUMN AND LAST ROW
		int lastCol = convertColToIndex(getLastCol());
		int lastRow = getLastRow();

		for(int cols = 0; cols < lastCol; cols++) {
			for(int rows = 0; rows < lastRow; rows++) {

				//int colRowCount = lastRow * lastCol;

				// CARRIAGE RETURN AT END OF ROW
				if(rows % lastRow == 0) {
					gridString += " \n";
				}
				/* 
				//ADDING COLUMN GUIDE
				if(rows == 0) {
					while(colRowCount > 0) {
						gridString += this.indexToCol(cols) + " ";
					}
					gridString += "/n";
				}
				//ADDING ROW GUIDE 
				if(cols == 0) {
					while(colRowCount > 0) {
						gridString += this.indexToCol(cols);
						colRowCount--;
					}
					gridString += " | ";
				}
				 */

				//DISPLAYING SEAT TYPES ACCORDING TO THEIR SEAT CLASS (First Class has Capitals, Economy has LowerCase)
				if(getSeatClassFromGrid(cols, rows) == "First Class") {
					if(getSeatTypeFromGrid(cols, rows) == SeatType.WINDOW) {
						gridString += " W [" + bookedCheck(cols, rows)  + "] ";
					}
					else if(getSeatTypeFromGrid(cols, rows) == SeatType.MIDDLE) {
						gridString += " M [" + bookedCheck(cols, rows)  + "] ";
					}
					else {
						gridString += " A [" + bookedCheck(cols, rows)  + "] ";
					}
				}
				else {
					if(getSeatTypeFromGrid(cols, rows) == SeatType.WINDOW) {
						gridString += " w [" + bookedCheck(cols, rows)  + "] ";
					}
					else if(getSeatTypeFromGrid(cols, rows) == SeatType.MIDDLE) {
						gridString += " m [" + bookedCheck(cols, rows)  + "] ";
					}
					else {
						gridString += " a [" + bookedCheck(cols, rows)  + "] ";
					}
				}
			}
		}
		return gridString;
	}


}
