package com.star.sud.mapping.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final String UPLOADTIME_DATE_FORMAT = "yyyyMMddHHmmss";
	public static final String API_DATE_FORMAT = "yyyy-MM-dd";

	public static String getCurrentDateInStr() {
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(API_DATE_FORMAT);
		return sdf.format(date);
	}

	public static String getCurrentDateInStr(String format) {
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}

	public static String getUploadTime() {
		SimpleDateFormat df = new SimpleDateFormat(DateUtil.UPLOADTIME_DATE_FORMAT);
		return df.format(new Date());
	}

	public static String getFutureDateInYears(Integer year) {
		Calendar date = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(API_DATE_FORMAT);
		date.add(Calendar.YEAR, year);
		date.add(Calendar.DAY_OF_MONTH, -1);
		return sdf.format(date.getTime());
	}

	public static Date getDateFromString(String input) throws ParseException {
		Date date = new SimpleDateFormat(DateUtil.API_DATE_FORMAT).parse(input);
		return date;
	}

	public static String getDateInStringFmInputDate(Date input) {
		SimpleDateFormat sdf = new SimpleDateFormat(API_DATE_FORMAT);
		return sdf.format(input);
	}

	public static String getDateInStringFmInputDate(Date input, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(input);
	}

	public static Date getDateFromString(String input, String format) throws ParseException {
		Date date = new SimpleDateFormat(format).parse(input);
		return date;
	}

	public static String getDateInStringFormatFmInputDateString(String input) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.API_DATE_FORMAT);
		return sdf.format(sdf.parse(input));
	}

	public static String getDateInStringFormatFmInputDateString(String input, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(sdf.parse(input));
	}

	public static String getFutureDateInStrWithJava8(int years, int months, int days) {
		LocalDate ld = LocalDate.now();
		ld = ld.plusYears(years).plusMonths(months).plusDays(days);
		String date = DateTimeFormatter.ofPattern(API_DATE_FORMAT).format(ld);
		return date;
	}

	public static Date getDateByYear(int year, int dayOfMonth) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, year);
		cal.add(Calendar.DAY_OF_MONTH, dayOfMonth);
		Date date = cal.getTime();
		System.out.println(date);
		return date;
	}

	public static Date getFutureDateOf1Year() {
		return getDateByYear(1, -1);
	}

	public static String getFutureDateOf1YearWithStr() {
		return getFutureDateInStrWithJava8(1, 0, -1);
	}

	public static int getYearDifference2(Date fromDate, Date toDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int d1 = Integer.parseInt(sdf.format(fromDate));
		if (toDate == null)
			toDate = new Date();
		int d2 = Integer.parseInt(sdf.format(toDate));
		int age = (d2 - d1) / 10000;
		return age;
	}

	public static int getYearDifference(Date fromDate, Date toDate) {
		Calendar fmDt = Calendar.getInstance();
		fmDt.setTimeInMillis(fromDate.getTime());
		Calendar toDt = Calendar.getInstance();
		toDt.setTimeInMillis(toDate != null ? toDate.getTime() : System.currentTimeMillis());
		return toDt.get(Calendar.YEAR) - fmDt.get(Calendar.YEAR);
	}

	public static int getNoOfYearsFmDob(String dob) throws ParseException {
		Date db = getDateFromString(dob);
		return getYearDifference(db, null);
	}

	public static Date getCurrentDateWithBegining() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		return date.getTime();
	}

	public static Boolean checkInputDtAfterCurrentDt(String input) throws ParseException {
		Date inputDate = DateUtil.getDateFromString(input);
		Date currentDate = DateUtil.getCurrentDateWithBegining();
		return currentDate.after(inputDate);
	}

	public static Boolean checkInputDtBeforeCurrentDt(String input) throws ParseException {
		Date inputDate = DateUtil.getDateFromString(input);
		Date currentDate = DateUtil.getCurrentDateWithBegining();
		return inputDate.before(currentDate);
	}

	public static Boolean checkInputDtEqualsCurrentDt(String input) throws ParseException {
		Date inputDate = DateUtil.getDateFromString(input);
		Date currentDate = DateUtil.getCurrentDateWithBegining();
		return currentDate.equals(inputDate);
	}

	public static Boolean checkDate2BeforeDate1(String date1, String date2) throws ParseException {
		Date firstDate = DateUtil.getDateFromString(date1);
		Date secondDate = DateUtil.getDateFromString(date2);
		return secondDate.before(firstDate);
	}

	public static Boolean checkDate1BeforeDate2(String date1, String date2) throws ParseException {
		Date firstDate = DateUtil.getDateFromString(date1);
		Date secondDate = DateUtil.getDateFromString(date2);
		return firstDate.before(secondDate);
	}

	public static Boolean checkDate1EqualsDate2(String date1, String date2) throws ParseException {
		Date firstDate = DateUtil.getDateFromString(date1);
		Date secondDate = DateUtil.getDateFromString(date2);
		return firstDate.equals(secondDate);
	}

	public static Boolean checkDate1AfterDate2(String date1, String date2) throws ParseException {
		Date firstDate = DateUtil.getDateFromString(date1);
		Date secondDate = DateUtil.getDateFromString(date2);
		return firstDate.after(secondDate);
	}

	public static Boolean checkDate2AfterDate1(String date1, String date2) throws ParseException {
		Date firstDate = DateUtil.getDateFromString(date1);
		Date secondDate = DateUtil.getDateFromString(date2);
		return secondDate.after(firstDate);
	}

	public static Boolean checkDatePattern(String data) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(DateUtil.API_DATE_FORMAT);
			format.setLenient(false);
			format.parse(data);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static Boolean checkDatePattern(String data, String inputFormat) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(inputFormat);
			format.setLenient(false);
			format.parse(data);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

}
