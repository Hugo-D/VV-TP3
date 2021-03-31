package fr.istic.vv;

class Date implements Comparable<Date> {
	
	private class DateNotValidException extends Exception {
		/**
		 * Je sais pas ce que c'est, mais Eclipse m'a demandé de rajouter ça pour enlever un Warning
		 */
		private static final long serialVersionUID = 1L;

		@SuppressWarnings("unused")
		public DateNotValidException() {
			super("La date n'est pas valide");
		}
		
		public DateNotValidException(String s) {
			super(s);
		}
	}
	
	private int m_day, m_month, m_year;
	private int[] m_daysPerMonth;

    public Date(int day, int month, int year) throws DateNotValidException { 
    	if(!isValidDate(day, month, year)) throw new DateNotValidException("Les 3 entiers ne forment pas une date valide.");
    	m_day = day;
    	m_month = month;
    	m_year = year;
    	for(int i=0; i<12; ++i) {
    		if((i % 2 == 0 && i <= 6) || (i % 2 != 0 && i > 6)) {
    			m_daysPerMonth[i] = 31;
    		} else if(i == 1) {
    	    	if(isLeapYear(year)) {
    	    		m_daysPerMonth[i] = 29;
    	    	} else  {
    	    		m_daysPerMonth[i] = 28;
    	    	}
    		} else {
	    		m_daysPerMonth[i] = 30;
    		}
    	}
    }

    public static boolean isValidDate(int day, int month, int year) { 
    	int[] daysPerMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
    	if(isLeapYear(year)) {
    		daysPerMonth[1] = 29;
    	}
    	return 1 <= day && day <= daysPerMonth[month-1];
    }

    public static boolean isLeapYear(int year) {
    	return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }

    public Date nextDate() {
    	int nextDay = m_day + 1;
    	int nextMonth = m_month;
    	int nextYear = m_year;
    	if(nextDay > m_daysPerMonth[m_month-1]) {
    		nextMonth += 1;
    		nextDay = 1;
    		if(nextMonth > 12) {
    			nextYear += 1;
    			nextMonth = 1;
    		}
    	}
    	try {
			return new Date(nextDay, nextMonth, nextYear);
		} catch (DateNotValidException e) {
			e.printStackTrace();
		}
    	return null;
    }

    public Date previousDate() {
    	int prevDay = m_day - 1;
		int prevMonth = m_month;
		int prevYear = m_year;
		if(prevDay < 1) {
			prevMonth -= 1;
			if(prevMonth < 1) {
				prevYear -= 1;
				prevMonth = 12;
			}
			prevDay = m_daysPerMonth[prevMonth-1];
		}
		try {
			return new Date(prevDay, prevMonth, prevYear);
		} catch (DateNotValidException e) {
			e.printStackTrace();
		}
		return null;
	}

    public int compareTo(Date other) throws NullPointerException {
    	if(other == null) {
    		throw new NullPointerException("La date de comparaison renseignée est nulle");
    	}
    	if(this.m_year < other.m_year) return -1;
    	else if(this.m_year == other.m_year) {
    		if(this.m_month < other.m_month) return -1;
    		else if(this.m_month == other.m_month) {
    			if(this.m_day < other.m_day) return -1;
    			else if(this.m_day == other.m_day) return 0;
    			else return 1;
    		} else return 1;
    	} else return 1;
    }

}