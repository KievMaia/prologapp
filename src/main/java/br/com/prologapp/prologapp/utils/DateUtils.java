package br.com.prologapp.prologapp.utils;

import java.time.Duration;

public class DateUtils {

	/**
	 * Formata {@link Duration} para {@link String} no formato: HH:mm:ss.
	 *
	 * @param duration {@link Duration}: a data a ser convertida em {@link String}
	 * @return {@link String}
	 */
	public static String formatDuration(Duration duration) {
		long seconds = duration.getSeconds();
		long absSeconds = Math.abs(seconds);
		String positive = String.format("%d:%02d:%02d", absSeconds / 3600, (absSeconds % 3600) / 60, absSeconds % 60);
		return seconds < 0 ? "-" + positive : positive;
	}
}
