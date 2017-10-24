package trainSeatBookingApp;

public abstract class TrainOperator {
	public String operatorName;
	public FloorGrid floorPlan;

	public abstract Seat bookFirstClass(TrainJourney sampleFirstJourn, SeatType sampleFirstSeat);
	public abstract Seat bookEconomy(TrainJourney sampleEcoJourn, SeatType sampleEcoSeat);

	@Override
	public String toString() {
		return "Welcome to the " + operatorName + " Railway Booking System!";
	}
}
