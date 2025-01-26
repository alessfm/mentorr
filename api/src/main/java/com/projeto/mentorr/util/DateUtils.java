package com.projeto.mentorr.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

	public static LocalDate toLocalDate(String data) {
		if (data == null) {
			return null;
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		return LocalDate.parse(data, formatter);
	}

	public static LocalDate toLocalDate(Date data) {
		if (data == null) {
			return null;
		}

		return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static String formatarData(LocalDate data) {
		if (data == null) {
			return "";
		}

		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return data.format(formatters);
	}

}
