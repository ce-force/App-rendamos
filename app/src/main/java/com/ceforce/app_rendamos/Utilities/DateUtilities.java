package com.ceforce.app_rendamos.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtilities {

    int finalDays[][];

    public DateUtilities() {

        finalDays = getRangeInDays();

    }

    public int dateDiff(String date1, String date2) {

        int diff = -1;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            Date firstDate = sdf.parse(date1);
            Date secondDate = sdf.parse(date2);

            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            diff = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return diff;

    }

    private int getTotalDays(int months, int days) {

        return months * 30 + days;

    }

    private int[][] getRangeInDays() {

        int initArrMonth[] = {1, 3, 5, 7, 9, 10, 11, 13, 15, 17, 19, 21, 23, 25, 28, 31, 34, 39, 45, 51, 57};
        int initArrDay[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 16, 16, 16, 0, 0, 0, 0};

        int finalArrMonth[] = {2, 4, 6, 8, 9, 10, 12, 14, 16, 18, 20, 22, 25, 28, 31, 34, 38, 44, 50, 56, 66};
        int finalArrDay[] = {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 15, 15, 15, 15, 30, 30, 30, 30, 30};

        int initDays[] = new int[21];
        int finalDays[] = new int[21];

        for (int i = 0; i < 21; i++) {

            initDays[i] = getTotalDays(initArrMonth[i], initArrDay[i]);
            finalDays[i] = getTotalDays(finalArrMonth[i], finalArrDay[i]);


        }

        int finalArr[][] = {initDays, finalDays};

        return finalArr;


    }

    private int getInd(int dateDiff) {

        for (int i = 0; i < 21; i++) {

            if (dateDiff >= this.finalDays[0][i] && dateDiff < this.finalDays[1][i]) {

                return i;

            }

        }

        return -1;

    }

    public String getASQ(String birthDate, int preWeeks) {


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(c);

        int dateDiff = dateDiff(birthDate, today) - preWeeks*7;

        int ind = getInd(dateDiff);

        switch (ind) {

            case 0:
                return "2 Meses ASQ-3";

            case 1:
                return "4 Meses ASQ-3";

            case 2:
                return "6 Meses ASQ-3";

            case 3:
                return "8 Meses ASQ-3";

            case 4:
                return "9 Meses ASQ-3";

            case 5:
                return "10 Meses ASQ-3";

            case 6:
                return "12 Meses ASQ-3";

            case 7:
                return "14 Meses ASQ-3";

            case 8:
                return "16 Meses ASQ-3";

            case 9:
                return "18 Meses ASQ-3";

            case 10:
                return "20 Meses ASQ-3";

            case 11:
                return "22 Meses ASQ-3";

            case 12:
                return "24 Meses ASQ-3";

            case 13:
                return "27 Meses ASQ-3";

            case 14:
                return "30 Meses ASQ-3";

            case 15:
                return "33 Meses ASQ-3";

            case 16:
                return "36 Meses ASQ-3";

            case 17:
                return "42 Meses ASQ-3";

            case 18:
                return "48 Meses ASQ-3";

            case 19:
                return "54 Meses ASQ-3";

            case 20:
                return "60 Meses ASQ-3";

        }

        return null;

    }

    public String getASQ(String birthDate, String applicationDate, int preWeeks) {


        int dateDiff = dateDiff(birthDate, applicationDate) - preWeeks*7;

        int ind = getInd(dateDiff);

        switch (ind) {

            case 0:
                return "2 Meses ASQ-3";

            case 1:
                return "4 Meses ASQ-3";

            case 2:
                return "6 Meses ASQ-3";

            case 3:
                return "8 Meses ASQ-3";

            case 4:
                return "9 Meses ASQ-3";

            case 5:
                return "10 Meses ASQ-3";

            case 6:
                return "12 Meses ASQ-3";

            case 7:
                return "14 Meses ASQ-3";

            case 8:
                return "16 Meses ASQ-3";

            case 9:
                return "18 Meses ASQ-3";

            case 10:
                return "20 Meses ASQ-3";

            case 11:
                return "22 Meses ASQ-3";

            case 12:
                return "24 Meses ASQ-3";

            case 13:
                return "27 Meses ASQ-3";

            case 14:
                return "30 Meses ASQ-3";

            case 15:
                return "33 Meses ASQ-3";

            case 16:
                return "36 Meses ASQ-3";

            case 17:
                return "42 Meses ASQ-3";

            case 18:
                return "48 Meses ASQ-3";

            case 19:
                return "54 Meses ASQ-3";

            case 20:
                return "60 Meses ASQ-3";

        }

        return "No existe un ASQ que corresponda";

    }

    public String getASQ(String birthDate) {


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(c);

        int dateDiff = dateDiff(birthDate, today);

        int ind = getInd(dateDiff);

        switch (ind) {

            case 0:
                return "2 Meses ASQ-3";

            case 1:
                return "4 Meses ASQ-3";

            case 2:
                return "6 Meses ASQ-3";

            case 3:
                return "8 Meses ASQ-3";

            case 4:
                return "9 Meses ASQ-3";

            case 5:
                return "10 Meses ASQ-3";

            case 6:
                return "12 Meses ASQ-3";

            case 7:
                return "14 Meses ASQ-3";

            case 8:
                return "16 Meses ASQ-3";

            case 9:
                return "18 Meses ASQ-3";

            case 10:
                return "20 Meses ASQ-3";

            case 11:
                return "22 Meses ASQ-3";

            case 12:
                return "24 Meses ASQ-3";

            case 13:
                return "27 Meses ASQ-3";

            case 14:
                return "30 Meses ASQ-3";

            case 15:
                return "33 Meses ASQ-3";

            case 16:
                return "36 Meses ASQ-3";

            case 17:
                return "42 Meses ASQ-3";

            case 18:
                return "48 Meses ASQ-3";

            case 19:
                return "54 Meses ASQ-3";

            case 20:
                return "60 Meses ASQ-3";

        }

        return null;

    }

    public String getASQ(String birthDate, String applicationDate) {


        int dateDiff = dateDiff(birthDate, applicationDate);

        int ind = getInd(dateDiff);

        switch (ind) {

            case 0:
                return "2 Meses ASQ-3";

            case 1:
                return "4 Meses ASQ-3";

            case 2:
                return "6 Meses ASQ-3";

            case 3:
                return "8 Meses ASQ-3";

            case 4:
                return "9 Meses ASQ-3";

            case 5:
                return "10 Meses ASQ-3";

            case 6:
                return "12 Meses ASQ-3";

            case 7:
                return "14 Meses ASQ-3";

            case 8:
                return "16 Meses ASQ-3";

            case 9:
                return "18 Meses ASQ-3";

            case 10:
                return "20 Meses ASQ-3";

            case 11:
                return "22 Meses ASQ-3";

            case 12:
                return "24 Meses ASQ-3";

            case 13:
                return "27 Meses ASQ-3";

            case 14:
                return "30 Meses ASQ-3";

            case 15:
                return "33 Meses ASQ-3";

            case 16:
                return "36 Meses ASQ-3";

            case 17:
                return "42 Meses ASQ-3";

            case 18:
                return "48 Meses ASQ-3";

            case 19:
                return "54 Meses ASQ-3";

            case 20:
                return "60 Meses ASQ-3";

        }

        return "No existe un ASQ que corresponda";

    }



}