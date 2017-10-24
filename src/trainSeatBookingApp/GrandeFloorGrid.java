package trainSeatBookingApp;

public class GrandeFloorGrid extends FloorGrid {
	private int gridCols = 9;
	private int gridRows = 12;
	private int midRows = 5;
	private int firstClassRows = 6;
	public Seat[][] seatGrid = new Seat[gridCols][gridRows];

	public void initialiseFloorGrid() {
		for(int x = 0; x < gridCols; x++) {
			for(int y = 0; y < gridRows; y++) {
				this.seatGrid[x][y] = new Seat();
				//SETTING WINDOW SEAT (Outer Rows)
				if(seatGrid[x][y] == seatGrid[x][gridRows - 1] || seatGrid[x][y] == seatGrid[0][y]) {
					seatGrid[x][y].setSeatType(SeatType.WINDOW);
				}
				//SETTING MIDDLE SEATS (Center Row)
				else if(seatGrid[x][y] == seatGrid[x][midRows]) {
					seatGrid[x][y].setSeatType(SeatType.MIDDLE);
				}
				//ALL OTHER ROWS ARE AISLES
				else
				{
					seatGrid[x][y].setSeatType(SeatType.AISLE);
				}
				//SETTING FIRST CLASS ROWS
				if(x < firstClassRows) {
					this.seatGrid[x][y].setSeatClass("First Class");
				}
			}
		}
	}

	public int getGridCols() {
		return gridCols;
	}

	public int getGridRows() {
		return gridRows;
	}

	public int getMidRows() {
		return midRows;
	}

	public int getFirstClassRows() {
		return firstClassRows;
	}


}