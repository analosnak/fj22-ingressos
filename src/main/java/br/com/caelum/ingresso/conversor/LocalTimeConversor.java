package br.com.caelum.ingresso.conversor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

public class LocalTimeConversor implements Converter<String, LocalTime> {

	@Override
	public LocalTime convert(String source) {
		return LocalTime.parse(source, DateTimeFormatter.ofPattern("HH:mm"));
	}

}
