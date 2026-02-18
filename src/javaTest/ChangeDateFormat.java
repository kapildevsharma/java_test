package javaTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeDateFormat {

    public static List<String> changeDateFormat1(List<String> dates) {

		List<String> newDateList = new ArrayList<String>();
		for (String oldDate : dates) {
			if (oldDate.length() == 10) {
				String[] arr;
				if (oldDate.contains("/")) {
					arr = oldDate.split("/");
                    newDateList.add(extracted(arr));
				}
				if (oldDate.contains("-")) {
					arr = oldDate.split("-");
                    newDateList.add(extracted(arr));
                }
			}else{
                newDateList.add(oldDate.substring(0,4) +"-" + oldDate.substring(4,6) +"-" + oldDate.substring(6,8));
            }
		}
		return newDateList;
	}

    private static String extracted(String[] arr) {
        String year = "";
        String month = "";
        String date = "";
        if (arr[0].length() == 4) {
            year = arr[0];
            month = arr[1];
            date = arr[2];
        }
        if (arr[0].length() == 2) {
            if (Integer.parseInt(arr[0]) > 12) {
                year = arr[2];
                month = arr[1];
                date = arr[0];
            } else {
                year = arr[2];
                month = arr[0];
                date = arr[1];
            }
        }
        return (year +"-" + month +"-" + date);
    }


    public static List<String> changeDateFormat(List<String> dates) {
		List<String> newDate = new ArrayList<String>();

		for (String oldDate : dates) {
			if (oldDate.contains("/")) {
                String dateFormat2 = "YYYY/MM/DD";
                if (isValiDate(dateFormat2, oldDate)) {
					String changed = changed(oldDate);
					newDate.add(changed);
				}
			} else if (oldDate.contains("-")) {
                String dateFormat1 = "YYYY-MM-DD";
                if (isValiDate(dateFormat1, oldDate)) {
					String changed = changed(oldDate);
					newDate.add(changed);
				}
			}else{
                newDate.add(oldDate);
            }

		}

		return newDate;
	}

	public static void main(String[] args) {
		List<String> dates = changeDateFormat(
				Arrays.asList("2010/03/30", "15/12/2016", "11-15-2012", "20130720"));
		System.out.println("changeDateFormat");
		for (String date : dates) {
			System.out.println(date);
		}

		System.out.println(" -------------------------------------");

		List<String> dateList = changeDateFormat1(
				Arrays.asList("2010/03/30", "05/02/2016", "15/12/2016", "11-15-2012", "20-10-2012", "20130720"));
		System.out.println("changeDateFormat1");

		for (String date : dateList) {
			System.out.println(date);
		}
	}

	private static boolean isValiDate(String dateFormat, String inputDateStr) {
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			sdf.parse(inputDateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	private static String changed(String date) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < date.length(); i++) {
			if (Character.isDigit(date.charAt(i))) {
				stringBuilder.append(date.charAt(i));
			}
		}
		return stringBuilder.toString();
	}
}