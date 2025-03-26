package com.projeto.mentorr.util;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;

public class Util {

	public static String gerarHashUnico() {
		return UUID.randomUUID().toString();
	}

	public static String retornarDiaEnumFormatado(DayOfWeek dia) {
		return StringUtils.capitalize(dia.getDisplayName(TextStyle.FULL, new Locale("pt", "BR")));
	}
	
	public static String resumirTexto(String texto) {
		Stream<String> linhas = texto.lines().limit(3);
		return linhas.collect(Collectors.joining(" ")) + "...";
	}

}
