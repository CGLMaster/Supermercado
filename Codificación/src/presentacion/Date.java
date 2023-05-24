package presentacion;

import java.util.Calendar;

public class Date {
	int year, month, day;
	java.util.Date date;

	public Date(int y, int m, int d) {
		year = y;
		month = m;
		day = d;
		date = new java.util.Date();
	}

	public int getDia() {
		return day;
	}

	public int getMes() {
		return month;
	}

	public int getAno() {
		return year;
	}

	@SuppressWarnings("deprecation")
	public Date(java.sql.Date date) {
		this(date.getYear(), date.getMonth(), date.getDay());
	}

	public String toString() {
		String strday = Integer.toString(day), strmonth = Integer.toString(month);

		if (day < 10) {
			strday = "0" + Integer.toString(day);
		}
		if (month < 10) {
			strmonth = "0" + Integer.toString(month);
		}

		return Integer.toString(year) + "/" + strmonth + "/" + strday;
	}

	public boolean checkDate() {
		Calendar aux = Calendar.getInstance();
		int currDay = aux.get(Calendar.DAY_OF_MONTH);
		int currMonth = aux.get(Calendar.MONTH) + 1;
		int currYear = aux.get(Calendar.YEAR);
		
		if(this.year <0 || this.month > 12|| this.month < 1){
			return false;
		}
		
		aux.set(Calendar.MONTH, this.month - 1);
		aux.set(Calendar.YEAR, this.year);
		int maxDay = aux.getActualMaximum(Calendar.DAY_OF_MONTH);

		if (this.day > maxDay || this.day < 1 || this.year < currYear) {
			return false;
		}

		if (this.year > currYear || this.month > currMonth) {
			return true;
		}
		return this.day > currDay;
	}

}
