package trainSeatBookingApp;

public class Seat {
	private SeatType seatT;
	private boolean booked;
	private String seatClass;
	public SeatPosition seatPos;

	//SEATPOSITION OBJECT
	public static final class SeatPosition{
		public char seatCol;
		public int seatRow;

		public SeatPosition(int sCol, int sRow) {
			char sChar = (char) sCol;
			this.seatCol = sChar;
			this.seatRow = sRow;
		}

		public SeatPosition() {
			this.seatCol = '%';
			this.seatRow = 99;
		}

		@Override
		public String toString() {
			return seatCol + Integer.toString(seatRow);
		}	
	}

	//CONSTRUCTOR
	public Seat(SeatType seatT, boolean booked, String seatClass, SeatPosition seatPos) {
		this.seatT = seatT;
		this.booked = booked;
		this.seatClass = seatClass;
		this.seatPos = seatPos;
	}

	public Seat() {
		this.seatT = SeatType.DEFAULT;
		this.booked = false;
		this.seatClass = "Null";
		this.seatPos = new SeatPosition();
	}


	//GET N SET METHODS
	public SeatType getSeatType() {
		return seatT;
	}


	public void setSeatType(SeatType setSeat) {
		this.seatT = setSeat;
	}


	public boolean isBooked() {
		return booked;
	}


	public void setBooked(boolean booked) {
		this.booked = booked;
	}


	public String getSeatClass() {
		return seatClass;
	}


	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public SeatPosition getSeatPos() {
		return seatPos;
	}

	public void setSeatPos(SeatPosition seatPos) {
		this.seatPos = seatPos;
	}


	@Override
	public String toString() {
		if(booked == true) {
			return seatClass + " " + seatT + " seat at: " + seatPos + " is Booked";
		}
		else {
			return seatClass + " " + seatT + " seat at: " + seatPos + " is not Booked";
		}
	}

}
