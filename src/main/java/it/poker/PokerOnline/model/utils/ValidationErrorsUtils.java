package it.poker.PokerOnline.model.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.validation.FieldError;

public class ValidationErrorsUtils {
	
	/**
	 * @param la lista di errori non modificabile
	 * @return una nuova lista di errori ordinata
	 */
	public static List<FieldError> sorted(List<FieldError> errors) {

		List<FieldError> errorsSorted = new ArrayList<>();
		errorsSorted.addAll(errors);

		Collections.sort(errorsSorted, new Comparator<FieldError>() {

			@Override
			public int compare(FieldError o1, FieldError o2) {
				int i = o1.getField().compareTo(o2.getField());
				if (i == 0) {
					return o1.getDefaultMessage().compareTo(o2.getDefaultMessage());
				}

				return i;
			}

		});
		return errorsSorted;
	}

}
