package br.com.caelum.ingresso.conversor;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

public class YearMonthConverter implements Converter<String, YearMonth> {

	@Override
	public YearMonth convert(String texto) {
		return YearMonth.parse(texto, DateTimeFormatter.ofPattern("MM/yyyy"));
	}

}
