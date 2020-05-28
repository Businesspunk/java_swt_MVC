package helper;

import java.text.*;
import java.util.Date;

public class DateEngine {

	public static Date create(int year, int month, int day) {
		Date date = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
		String dateString = String.join("-", String.valueOf(year), String.valueOf(month), String.valueOf(day));
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			System.out.println("Ошибка с парсингом даты");
		}

		return date;
	}

	public static Date create(int[] Date) {
		return create(Date[0], Date[1], Date[2]);
	}

	public static Date create(String strDate) {

		String[] arrayDate = strDate.split("-");
		int[] arrayInt = new int[3];

		for (int i = 0; i < arrayDate.length; i++) {
			arrayInt[i] = Integer.parseInt(arrayDate[i]);
		}

		return create(arrayInt);
	}

	public static boolean compare(Date d1, Date d2) {
		return d1.compareTo(d2) == 0;
	}
}
