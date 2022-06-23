package com.challenge.careerswtich.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesUtil {
	
	public static String dateTimeToString(Date fechaHora) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(fechaHora);
        } catch (Exception e) {
            return null;
        }
    }

}
