package trainSeatBookingApp;

public class TrainWay extends TrainOperator{
	private Seat requestedSeat = new Seat();
	private Seat searchSeat = new Seat();
	public PetiteFloorGrid floorPlan = new PetiteFloorGrid();

	//BOOKING FIRST CLASS SEATS
	public Seat bookFirstClass(TrainJourney sampleFirstJourn, SeatType sampleFirstSeat) {
		floorPlan = (PetiteFloorGrid) sampleFirstJourn.getTrainOp().floorPlan; //check this line
		requestedSeat.setSeatType(sampleFirstSeat);

		for(int colCount = 0; colCount < 7; colCount++) {
			for(int rowCount = 0; rowCount < 10; rowCount++) {
				searchSeat.setSeatPos(new Seat.SeatPosition(colCount, rowCount));

				//BOOKS REQUESTED FIRST CLASS SEAT IF AVAILABLE
				if (searchSeat.getSeatType() == floorPlan.queryAvailableFirstClassSeat(requestedSeat.getSeatType()).getSeatType()) {
					System.out.println("First Class Seat Booked! " + searchSeat);
					searchSeat.setBooked(true);
				}
				//IF REQUESTED SEAT IS UNAVAILABLE, WILL TRY AND FIND ANOTHER FIRST CLASS SEAT
				else if (requestedSeat.getSeatType() == floorPlan.queryAvailableFirstClassSeat(searchSeat.getSeatType()).getSeatType() && searchSeat.isBooked() == true) {
					System.out.println("Sorry, there are no seats of that type available in First Class. \n Booking another seat in First Class...");
					colCount = 0;
					rowCount = 0;
					requestedSeat.setSeatPos(new Seat.SeatPosition(colCount, rowCount));

					//IF FIRST CLASS IS ALL BOOKED, WILL TRY AND FIND ECONOMY WINDOW SEAT
					if(requestedSeat.getSeatType() == searchSeat.getSeatType() && searchSeat.isBooked() == true) {
						System.out.println("Sorry, First Class is fully booked.\n Booking a window seat in Economy Class...");
						requestedSeat.setSeatType(SeatType.WINDOW);
						//queryAvailableEconomySeat(requestedSeat.getSeatType());
					}
					//IF ALL ELSE FAILS, BOOKING CANNOT BE MADE
					else{
						System.out.println("Sorry, This Booking could not be made.");
						searchSeat = null;
					} 
				}  	
			}
		} return searchSeat;
	}

	//BOOKING ECONOMY SEATS
	public Seat bookEconomy(TrainJourney sampleEcoJourn, SeatType sampleEcoSeat) {
		floorPlan = (PetiteFloorGrid) sampleEcoJourn.getTrainOp().floorPlan;
		requestedSeat.setSeatType(sampleEcoSeat);

		for(int colCount = 0; colCount < floorPlan.getGridCols(); colCount++) {
			for(int rowCount = 0; rowCount < floorPlan.getGridRows(); rowCount++) {
				searchSeat.setSeatPos(new Seat.SeatPosition(colCount, rowCount));

				//BOOKS REQUESTED ECONOMY CLASS SEAT IF AVAILABLE
				if (requestedSeat.getSeatType() == floorPlan.queryAvailableEconomySeat(searchSeat.getSeatType()).getSeatType()) {
					searchSeat.setBooked(true);
					System.out.println("Economy Class Seat Booked! " + searchSeat);
				}
				//IF REQUESTED SEAT IS UNAVAILABLE, WILL TRY AND FIND A FIRST CLASS WINDOW SEAT
				else if(requestedSeat.getSeatType() == searchSeat.getSeatType() && searchSeat.isBooked() == true) {
					System.out.println("Sorry, There are no seats of that type available in Economy.\n Booking a window seat in First Class...");
					requestedSeat.setSeatType(SeatType.WINDOW);
					floorPlan.queryAvailableFirstClassSeat(requestedSeat.getSeatType());
				}
				else{
					System.out.println("Sorry, This Booking could not be made.");
					searchSeat = null;
				} 
			}  	
		} return searchSeat;
	} 

	//CONSTRUCTORS
	public TrainWay() {
		this.operatorName = "TrainWay";
		this.requestedSeat = new Seat();
		this.searchSeat = new Seat();
		this.floorPlan = new PetiteFloorGrid();
		this.floorPlan.initialiseFloorGrid();
	}

	public TrainWay(Seat requestedSeat, Seat searchSeat) {
		super();
		this.operatorName = "TrainWay";
		this.requestedSeat = requestedSeat;
		this.searchSeat = searchSeat;
		this.floorPlan = new PetiteFloorGrid();
		this.floorPlan.initialiseFloorGrid();
	}
}
