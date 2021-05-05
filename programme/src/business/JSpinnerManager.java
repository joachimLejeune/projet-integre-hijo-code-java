package business;

import javax.swing.*;

public class JSpinnerManager {

    public int getYEARJSPinner(JSpinner dateSpinner) {
        return Integer.valueOf(dateSpinner.getValue().toString().substring(25,29));
    }
    public int getMonthJSPinner(JSpinner dateSpinner) {
        String month = dateSpinner.getValue().toString().substring(4,7);
        switch (month){
            case "Jan" :
                return 0;
            case "Feb" :
                return 1;
            case "Mar" :
                return 2;
            case "Apr" :
                return 3;
            case "May" :
                return 4;
            case "Jun" :
                return 5;
            case "Jul" :
                return 6;
            case "Aug" :
                return 7;
            case "Sep" :
                return 8;
            case "Oct" :
                return 9;
            case "Nov" :
                return 10;
            case "Dec" :
                return 11;
        }
        return 118218;
    }
    public int getDayOfTheMonthJSPinner(JSpinner dateSpinner) {
        return Integer.valueOf(dateSpinner.getValue().toString().substring(8,10));
    }
}
