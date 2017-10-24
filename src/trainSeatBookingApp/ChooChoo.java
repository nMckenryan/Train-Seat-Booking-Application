package trainSeatBookingApp;

public class ChooChoo extends TrainOperator {
	private Seat requestedSeat = new Seat();
	private Seat searchSeat = new Seat();
	public GrandeFloorGrid floorPlan = new GrandeFloorGrid();


	//BOOKING FIRST CLASS SEATS
	public Seat bookFirstClass(TrainJourney sampleFirstJourn, SeatType sampleFirstSeat) {
		floorPlan = (GrandeFloorGrid) sampleFirstJourn.getTrainOp().floorPlan;
		requestedSeat.setSeatType(sampleFirstSeat);

		for(int colCount = 0; colCount < floorPlan.getGridCols(); colCount++) {
			for(int rowCount = 0; rowCount < floorPlan.getGridRows(); rowCount++) {
				searchSeat.setSeatPos(new Seat.SeatPosition(colCount, rowCount));

				//BOOKS REQUESTED FIRST CLASS SEAT IF AVAILABLE
				if (requestedSeat.getSeatType() == floorPlan.queryAvailableFirstClassSeat(searchSeat.getSeatType()).getSeatType()) {
					System.out.println("First Class Seat Booked! " + searchSeat);
					searchSeat.setBooked(true);
				}
				//IF REQUESTED SEAT IS UNAVAILABLE, WILL TRY AND BOOK AN ENTIRE ROW IN ECONOMY
				else if (requestedSeat.getSeatType() == floorPlan.queryAvailableFirstClassSeat(searchSeat.getSeatType()).getSeatType() && searchSeat.isBooked() == true) {
					System.out.println("Sorry, there are no seats of that type available in First Class. \n Booking a Row in Economy...");

					Seat initialSeat = floorPlan.queryAvailableEconomySeat(SeatType.WINDOW);
					Seat[] freeSeats = new Seat[floorPlan.getGridCols()];
					int freeRow = 99;

					if (initialSeat != null) {
						for(colCount = 0; colCount < floorPlan.getGridCols(); colCount++) {
							for(rowCount = 0; rowCount < floorPlan.getGridRows(); rowCount++) {
								searchSeat.setSeatPos(new Seat.SeatPosition(colCount, rowCount));
								requestedSeat.setSeatPos(new Seat.SeatPosition('A', rowCount));

								//FINDS WINDOW SEAT TO BEGIN SEARCH
								while(rowCount < floorPlan.getLastRow()) { //LOOP RUNS WHILE THERE ARE STILL ROWS AVAILABLE.
									if(floorPlan.getSeat(floorPlan.indexToCol(colCount), rowCount).isBooked() == false) { //LOOP CANCELS IF A SEAT IN A ROW IS BOOKED
										freeSeats[colCount] = (floorPlan.getSeat(floorPlan.indexToCol(colCount), rowCount));
										freeRow = rowCount;
									}
									else {
										rowCount = floorPlan.getLastRow();
										freeRow = 99;
									}
								}
							}
						}
						//IF ENTIRE ROW IS AVAILABLE, ROW IS BOOKED
						Seat.SeatPosition endSeat = new Seat.SeatPosition(floorPlan.getLastCol(), freeRow);
						if (freeSeats[floorPlan.getGridCols()].getSeatPos() == endSeat) {
							for(int count = 0; count < floorPlan.getGridCols(); count++) {
								floorPlan.getSeat(floorPlan.indexToCol(count), freeRow).setBooked(true);
							}
						}
					}
				}

				//IF ALL ELSE FAILS, BOOKING CANNOT BE MADE
				else{
					System.out.println("Sorry, This Booking could not be made.");
					searchSeat = null;
				} 
			}  	
		} return searchSeat;
	} 


	//BOOKING ECONOMY SEATS
	public Seat bookEconomy(TrainJourney sampleEcoJourn, SeatType sampleEcoSeat) {
		floorPlan = (GrandeFloorGrid) sampleEcoJourn.getTrainOp().floorPlan;
		requestedSeat.setSeatType(sampleEcoSeat);

		for(int colCount = 0; colCount < floorPlan.getGridCols(); colCount++) {
			for(int rowCount = 0; rowCount < floorPlan.getGridRows(); rowCount++) {
				searchSeat.setSeatPos(new Seat.SeatPosition(colCount, rowCount));

				//BOOKS REQUESTED ECONOMY CLASS SEAT IF AVAILABLE,
				if (requestedSeat.getSeatType() == floorPlan.queryAvailableEconomySeat(searchSeat.getSeatType()).getSeatType()) {
					searchSeat.setBooked(true);
					System.out.println("Economy Class Seat Booked! " + searchSeat);
				}
				//ELSE BOOKING CANNOT BE MADE
				else{
					System.out.println("Sorry, This Booking could not be made.");
					searchSeat = null;
				} 
			}
		} return searchSeat;
	} 

	//CONSTRUCTORS
	public ChooChoo() {
		this.operatorName = "TrainWay";
		this.floorPlan = new GrandeFloorGrid();
		this.floorPlan.initialiseFloorGrid();
	}

	public ChooChoo(Seat requestedSeat, Seat searchSeat) {
		super();
		this.operatorName = "TrainWay";
		this.requestedSeat = requestedSeat;
		this.searchSeat = searchSeat;
		this.floorPlan = new GrandeFloorGrid();
		this.floorPlan.initialiseFloorGrid();
	}
}
