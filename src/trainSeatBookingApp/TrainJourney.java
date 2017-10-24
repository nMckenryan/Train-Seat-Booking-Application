package trainSeatBookingApp;

/**
 * The TrainSeatBooking Application books a seat on a train journey according to the customer's desires 
 * and the particular train's policies. First, the user selects which Train Operator they would like to use,
 * Then they are asked which Journey from said Train Operator they would like go travel on. In this final menu, 
 * The users can book seats in First or Economy Class, and can request a type of seat (e.g. Window, Aisle or Middle).
 *
 * @author  Nigel McKenzie-Ryan
 * @version 1.0
 * @completed 18/09/2017 
 */

import java.util.Scanner;

public class TrainJourney {
	private String startCity;
	private String destinationCity;
	private String departureTime;
	private String journeyNum;
	private TrainOperator trainOp;

	public TrainJourney(String sCity, String dCity, String dTime, String journeyNum, TrainOperator trainOp) {
		this.startCity = sCity;
		this.destinationCity = dCity;
		this.departureTime = dTime;
		this.journeyNum = journeyNum;
		this.trainOp = trainOp;
	}

	public TrainJourney() {
		this.startCity = "Starting City Not Set";
		this.destinationCity = "Destination not Set";
		this.departureTime = "Departure time not Set";
		this.journeyNum = "N/A";
		this.trainOp = new TrainWay();
	}

	public TrainOperator getTrainOp() {
		return trainOp;
	}

	public void setTrainOp(TrainOperator trainOp) {
		this.trainOp = trainOp;
	}

	public FloorGrid getFloorGrid() {
		return this.trainOp.floorPlan;
	}

	public Seat[][] getSeatGrid() {
		return getFloorGrid().seatGrid;
	}

	@Override
	public String toString() {
		return "Journey: " + journeyNum + " from: " + startCity + " to: " + destinationCity + " Departs: "
				+ departureTime;
	}	

	//MAIN 
	public static void main(String[] args)
	{
		int opChoose = 0;
		boolean bookingContinue = true;

		TrainWay trainway = new TrainWay();
		ChooChoo choochoo = new ChooChoo();
		Scanner input = new Scanner(System.in);
		TrainJourney firstJourney = new TrainJourney();

		//PROMPTING FOR TRAIN OPERATOR (Recursive)
		do { 
			System.out.println("Which train operator would you like to travel with?\n 1: TrainWay \n 2: ChooChoo\n");
			opChoose = input.nextInt();
			if(opChoose == 1) {
				firstJourney.trainOp = trainway;
			}
			else if(opChoose == 2) {
				firstJourney.trainOp = choochoo;
			}
		}
		while(opChoose != 1 && opChoose != 2);
		opChoose = 0;

		TrainJourney JourneyA = new TrainJourney("Berlin", "Warsaw", "19:39", "A7009", firstJourney.trainOp);
		TrainJourney JourneyB = new TrainJourney("Moscow", "Berlin", "19:44", "B4390", firstJourney.trainOp);

		//CHOOSING THE TRAIN JOURNEY (Recursive)
		do {
			System.out.println("Which train journey would you like to book a seat on? \n" + "1: " + JourneyA + "\n2: " + JourneyB);
			opChoose = input.nextInt();
			if(opChoose == 1) {
				firstJourney = JourneyA;
			}
			else if(opChoose == 2) {
				firstJourney = JourneyB;
			} 
		}
		while(opChoose != 1 && opChoose != 2);
		System.out.println("Booking Seats for: " + firstJourney);
		opChoose = 0;

		boolean continueFunc = true;

		while(continueFunc) {
			do { //RECURSIVE FUNCTION FOR BOOKING MENU
				System.out.println(firstJourney.getFloorGrid()); //GETTING FLOORGRID
				System.out.println("\n1. Book in First Class \n2. Book in Economy Class \n3. Show Floor Grid \n4. Exit\n");
				opChoose = input.nextInt();
				int seatChoose = 99;
				switch (opChoose) {
				//BOOKING A FIRST CLASS SEAT
				case 1:
					do {
						System.out.println("\nWhich Seat Type? \n1. WINDOW \n2. MIDDLE\n3. AISLE\n");
						seatChoose = input.nextInt();
						if(seatChoose == 1) {
							firstJourney.trainOp.bookFirstClass(firstJourney, SeatType.WINDOW);
						}
						else if(seatChoose == 2) {
							firstJourney.trainOp.bookFirstClass(firstJourney, SeatType.MIDDLE);
						}
						else if(seatChoose == 3) {
							firstJourney.trainOp.bookFirstClass(firstJourney, SeatType.AISLE);
						}
						else {
							seatChoose = 4;
							break;
						}
					}
					while(seatChoose != 1 && seatChoose != 2 && seatChoose != 3 && seatChoose != 4);
					opChoose = 0;
					break;
					//BOOKING AN ECONOMY SEAT
				case 2:
					do {
						System.out.println("\nWhich Seat Type? \n1. WINDOW \n2. MIDDLE\n3. AISLE\n");
						seatChoose = input.nextInt();
						if(seatChoose == 1) {
							firstJourney.trainOp.bookEconomy(firstJourney, SeatType.WINDOW);
						}
						else if(seatChoose == 2) {
							firstJourney.trainOp.bookEconomy(firstJourney, SeatType.MIDDLE);
						}
						else if(seatChoose == 3) {
							firstJourney.trainOp.bookEconomy(firstJourney, SeatType.AISLE);
						}
					}
					while(seatChoose != 1 && seatChoose != 2 && seatChoose != 3);
					opChoose = 0; //resets Recursion loop
					break;
					//QUITTING THE PROGRAM (note: no case 3, loops naturally)
				case 4:
					continueFunc = false;
					break;
				}
			} while(opChoose != 1 && opChoose != 2 && opChoose != 4);
			System.out.println("GoodBye!");
			input.close(); //Closing the Scanner. End Program.
			System.exit(0);
		}
	}
}
