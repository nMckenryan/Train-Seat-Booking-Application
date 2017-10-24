package trainSeatBookingApp;

import trainSeatBookingApp.Seat.SeatPosition;

public class PetiteFloorGrid extends FloorGrid {

	public PetiteFloorGrid() {
		super();
		this.gridCols = 7;
		this.gridRows = 10;
		this.midRows = 3;
		this.firstClassRows = 4;
		this.seatGrid = new Seat[7][10];
		for(int i = 0; i < gridCols; i++) {
			for(int j= 0; j < gridRows; j++) {
				this.seatGrid[i][j] = new Seat(SeatType.DEFAULT, false, "No Class", new Seat.SeatPosition(i, j));
			}
		}
	}

	public void initialiseFloorGrid() {
		for(int x = 0; x < gridCols; x++) {
			for(int y = 0; y < gridRows; y++) {
				this.seatGrid[x][y] = new Seat(SeatType.DEFAULT, false, "", new SeatPosition(x, y)); //POPULATING THE ARRAY WITH DEFAULT SEATS
				//SETTING FIRST CLASS ROWS
				if(y < firstClassRows) {
					this.seatGrid[x][y].setSeatClass("First Class");
				}
				//SETTING WINDOW SEAT (First and Last Rows)
				if(this.seatGrid[x][y] == this.seatGrid[x][gridRows - 1] || this.seatGrid[x][y] == this.seatGrid[0][y]) {
					this.seatGrid[x][y].setSeatType(SeatType.WINDOW);
				}
				//SETTING MIDDLE SEATS (Center Row)
				else if(this.seatGrid[x][y] == this.seatGrid[x][midRows]) {
					this.seatGrid[x][y].setSeatType(SeatType.MIDDLE);
				}
				//ALL OTHER ROWS ARE AISLES
				else {
					this.seatGrid[x][y].setSeatType(SeatType.AISLE);
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