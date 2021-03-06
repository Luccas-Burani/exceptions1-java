package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation() {

	}

	public Reservation(Integer rooNumber, Date checkIn, Date checkOut) {
		if(!checkOut.after(checkIn))
		{
			throw new DomainException("Check-out date must be after check-in date.");
		}
		
		this.roomNumber = rooNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRooNumver() {
		return roomNumber;
	}

	public void setRooNumver(Integer rooNumver) {
		this.roomNumber = rooNumver;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public void updateDates(Date newCheckIn, Date newCheckOut) {
		
		Date now = new Date();
		
		if(newCheckIn.before(now) || newCheckOut.before(now))
		{
			throw new DomainException("Reservation dates for update must be future dates.");
		}
		if(!newCheckOut.after(newCheckIn))
		{
			throw new DomainException("Check-out date must be after check-in date.");
		}
		
		checkIn = newCheckIn;
		checkOut = newCheckOut;	
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Room ");
		sb.append(roomNumber);
		sb.append(", Check-In: ");
		sb.append(sdf.format(checkIn));
		sb.append(", Check-Out: ");
		sb.append(sdf.format(checkOut));
		sb.append(", ");
		sb.append(duration());
		sb.append(" Nights.");

		return sb.toString();

	}

}
