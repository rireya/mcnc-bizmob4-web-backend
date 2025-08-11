package com.mcnc.bizmob.web.global.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

	/**
	 *  LocalDateTime 형식의 날짜 데이터를 받아서 String 형식으로 변환
	 * @param parseDate
	 * @return LocalDate
	 */
	public static String convertDateToStr(LocalDateTime parseDate, String pattern) {
		DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern(pattern);
		return parseDate != null ? dayFormatter.format(parseDate) : "";
	}
	
	/**
	 * @param parseDate
	 * @return LocalDate
	 */
	public static String convertDateToStrYYYYMMDD(LocalDateTime parseDate) {
		return convertDateToStr(parseDate, "YYYYMMDD");
	}
	
	/**
	 * String 형식의 시간 데이터를 받아서 LocalTime 형식으로 변환
	 * @param parseTime HHmmss(SSS)
	 * @return LocalTime
	 */
	public LocalTime parseStrToLocalTime(String parseTime) {
	    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
	    return LocalTime.parse(parseTime, timeFormatter);
	}

	/**
	 *  String 형식의 날짜 데이터를 받아서 LocalDate 형식으로 변환
	 * @param parseDate
	 * @return LocalDate
	 */
	public LocalDate paresStrToLocalDate(String parseDate) {
	    DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	    return LocalDate.parse(parseDate, dayFormatter);
	}
	
}
