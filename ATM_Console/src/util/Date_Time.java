package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Date_Time {
	public String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy [hh:mm]");
		String date = sdf.format(new Date());
//		System.out.println(date);
		return date.toString();
	}

	public static void main(String[] args) {
		System.out.println(new Date_Time().getDateTime());
	}
}