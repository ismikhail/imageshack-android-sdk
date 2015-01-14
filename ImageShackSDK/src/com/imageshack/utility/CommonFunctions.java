package com.imageshack.utility;

import java.util.ArrayList;

public class CommonFunctions {
	public static String arrayListToCsv(ArrayList<String> values) {
		StringBuilder csv = new StringBuilder();

		for (int i = 0; i < values.size(); i++) {
			csv.append(values.get(i));
			if (i < values.size() - 1) {
				csv.append(",");
			}
		}

		return csv.toString();
	}
}
