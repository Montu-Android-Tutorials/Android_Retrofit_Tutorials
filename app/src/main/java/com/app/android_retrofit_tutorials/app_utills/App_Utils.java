package com.app.android_retrofit_tutorials.app_utills;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.telephony.PhoneNumberUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.VIBRATOR_SERVICE;

public class App_Utils {


    public String TAG = App_Utils.class.getSimpleName();


/*
    "yyyy.MM.dd G 'at' HH:mm:ss z" ---- 2001.07.04 AD at 12:08:56 PDT
    "hh 'o''clock' a, zzzz" ----------- 12 o'clock PM, Pacific Daylight Time
    "EEE, d MMM yyyy HH:mm:ss Z"------- Wed, 4 Jul 2001 12:08:56 -0700
    "yyyy-MM-dd'T'HH:mm:ss.SSSZ"------- 2001-07-04T12:08:56.235-0700
    "yyMMddHHmmssZ"-------------------- 010704120856-0700
    "K:mm a, z" ----------------------- 0:08 PM, PDT
    "h:mm a" -------------------------- 12:08 PM
    "EEE, MMM d, ''yy" ---------------- Wed, Jul 4, '01
*/




    public static double getAverageOfRatings(ArrayList<Float> ratingsListAvg) {
        return ratingsListAvg.stream().mapToDouble(val -> val).average().orElse(0.0);
    }



    public static double getRoundOfVal(double val) {
        DecimalFormat newFormat = new DecimalFormat("#.#");
        if (val != 0)
            return Double.parseDouble(newFormat.format(val));
        else
            return 0;
    }


    public static File getFilePath(String strFile) {
        File file;
        file = new File(strFile);
        return file;
    }



    public static String getRandomID() {
        String randomID;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        String date = df.format(c.getTime());
        Random rand = new Random();
        int min = 1000, max = 9999;

        int randomNum = rand.nextInt((max - min) + 1) + min;
        randomID = date + randomNum;

        return randomID;
    }


    public static float Calculate_GST(float gstAmt, float productAmt)
    {
        return Math.round((productAmt* gstAmt) / 100);
    }



    public static String getSysNextMonthsDate(int duration){

        Calendar currentMonth = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
/*
        System.out.println("Curret month of date : "
                + dateFormat.format(currentMonth.getTime()));
        // Increment month
        currentMonth.add(Calendar.DATE, duration);
        System.out.println("Next Date : "
                + dateFormat.format(currentMonth.getTime()));
*/

        return dateFormat.format(currentMonth.getTime())+"+00:00";
    }



    public static String convertFirstCap(String string) {
        StringBuilder sb = new StringBuilder(string);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }





    public static boolean getTimeFroChatting() {

        int timeOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        Log.d("TAG", "getTimeFroChatting: timeOfDay--->"+timeOfDay);
        if(timeOfDay >= 11 && timeOfDay <= 17){
            return true;
        }else
            return false;
    }





    // Vibrate for 150 milliseconds
    public static void shakeItBaby(Context context, int milliSec) {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) context.getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(milliSec, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator)context.getSystemService(VIBRATOR_SERVICE)).vibrate(milliSec);
        }
    }



    // Returns true if s is
    // a number else false
    public static boolean isNumber(String s)
    {
        for (int i = 0; i < s.length(); i++)
            if (Character.isDigit(s.charAt(i)) == false)
                return false;

        return true;
    }

    public static ArrayList getUniqueListValues(ArrayList<String> input) {
        return new ArrayList<String>(new LinkedHashSet<>(input));
    }

    public static String getSysDate(){
        DateFormat _dateFormat;
        String _todaysDate = "";

        _dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

        Calendar cal = Calendar.getInstance();
        _todaysDate = _dateFormat.format(cal.getTime());

//        System.out.println("timeStamp dateFormat-->"+_todaysDate);

        return _todaysDate+"+00:00";
    }


    public static String removeFirstChar(String str) {
        return str.substring(1);
    }

    public static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }



    public static String convertDate(String convertDate){
        Date date = null;
        SimpleDateFormat sdf, sdfDate;
        String strDate = "";

        try {
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            sdfDate = new SimpleDateFormat("yyyy MMMM dd");

            date = sdf.parse(convertDate);
            strDate = sdfDate.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        Log.d("TAG", "onCreate: strDate--->"+strDate);
        return strDate;
    }



    public static String convertTime(String convertDate){

        Date date = null;
        String strtime = "";
        SimpleDateFormat sdf, sdfDate, sdfTime;

        try {
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            sdfTime = new SimpleDateFormat("hh.mm");

            date = sdf.parse(convertDate);
            strtime = sdfTime.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

//        Log.d("TAG", "onCreate: strtime--->"+strtime);

        return strtime;
    }


    /**
     * @param message the encoded message
     * @return the decoded message
     */
    public static String fromBase64(String message) {
        byte[] data = Base64.decode(message, Base64.DEFAULT);
        try {
            return new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }






    public static void giveFeedBabk(Context mContext) {
        String appPackageName= mContext.getPackageName();
        Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+appPackageName));
        marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET|Intent.FLAG_ACTIVITY_MULTIPLE_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(marketIntent);
    }



    public static void openDialPad(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }


    public static double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch (Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else return 0;
    }


    public static float ParseFloat(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Float.parseFloat(strNumber);
            } catch (Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else return 0;
    }



    public static float getPerAmt(float charges, float subTotal) {
        return ((charges / 100) * subTotal) ;
    }


    public static  boolean isEmailValidPattern(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static   boolean isMobileValidPattern(CharSequence email) {
        return Patterns.PHONE.matcher(email).matches();
    }



    public static Date convertDateFormat(String strDate) {
        SimpleDateFormat sdf = null;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Date date = null;
        try {
            date =  sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String getDaysAgo(Date date) {
        long days = (new Date().getTime() - date.getTime()) / 86400000;
        if (days == 0) return "Today";
        else if (days == 1) return "Yesterday";
        else return days + " days ago";
    }



    public static boolean isStringNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

    public static boolean isValidEmail(String email) {
        return !Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public static boolean isPasswordValid(String password) {

        String regExpn =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        CharSequence inputStr = password;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }





    public final static boolean isValidEmail_Pattern(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static final boolean isValidPhoneNumber(CharSequence target) {
        if (target.length()!=10) {
            return false;
        } else {
            return Patterns.PHONE.matcher(target).matches();
        }
    }



    public static final String getFirstTwoChar(String str) {
        return str.length() < 2 ? str : str.substring(0, 2);
    }







    public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }







    public static double _getDist(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double distance;
            Location locationA = new Location("locationA");
            locationA.setLatitude(lat1);
            locationA.setLongitude(lon1);

            Location locationB = new Location("locationB");
            locationB.setLatitude(lat2);
            locationB.setLongitude(lon2);
            // distance = locationA.distanceTo(locationB);   // in meters
            distance = locationA.distanceTo(locationB)/1000;   // in km
            return (distance);
        }
    }




    public static void openMap(Context mContext, Double store_lat, Double store_long,
                               double myLatitude, double myLongitude) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr="
                + myLatitude + "," + myLongitude + "&daddr=" + store_lat + "," + store_long));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        mContext.startActivity(intent);
    }






    public static void openWhatsApp(Context context, String store_Mobile) {
        String smsNumber = "91" + store_Mobile;
        boolean isWhatsappInstalled = whatsappInstalledOrNot(context,"com.whatsapp");
        if (isWhatsappInstalled) {

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");//phone number without "+" prefix
            context.startActivity(sendIntent);
        } else {
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            AppAlert.callNotamlAlert(context, "Error", "No Whats App Found !");
            context.startActivity(goToMarket);
        }
    }


    public static boolean whatsappInstalledOrNot(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }



    public static boolean getCalBetween(float i, float minValueInclusive, float maxValueInclusive) {
        if (i >= minValueInclusive && i <= maxValueInclusive)
            return true;
        else
            return false;
    }




}