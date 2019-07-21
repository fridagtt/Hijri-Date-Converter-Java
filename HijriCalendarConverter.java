package paquete;

import java.time.*;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.util.Calendar;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.List;

public class HijriCalendarConverter {

    public static String convertirFecha(Date fecha, int dateFormat) {

        Calendar cl = Calendar.getInstance();
        cl.setTime(fecha);

        //get Hijri date of the one given
        HijrahDate hijri = HijrahChronology.INSTANCE.date(LocalDate.of(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH) + 1, cl.get(Calendar.DATE)));

        //the method .format return a string of the week name day in English
        String dia = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH).format(hijri);
        String diaSemanal;

        //establish the week name day in Muslim
        switch (dia) {
            case "Monday":
                diaSemanal = "Al-ithnáyn";
                break;
            case "Tuesday":
                diaSemanal = "Al-thalatha";
                break;
            case "Wednesday":
                diaSemanal = "Al-arba'a";
                break;
            case "Thursday":
                diaSemanal = "Al-jamís";
                break;
            case "Friday":
                diaSemanal = "Al-yuma'a";
                break;
            case "Saturday":
                diaSemanal = "As-sabt";
                break;
            case "Sunday":
                diaSemanal = "Al-áhad";
                break;
            default:
                diaSemanal = "El día de la semana no existe";
            }
        //return the corresponding date format in Muslim according to the dateFormat number received
        if(dateFormat==0){
            return diaSemanal + ", " + DateTimeFormatter.ofPattern("MMMM d").format(hijri);
        }
        else if(dateFormat==1){
            return diaSemanal + " " + DateTimeFormatter.ofPattern("MM").format(hijri)+"/"+DateTimeFormatter.ofPattern("d").format(hijri);
        }else{
            if(DateTimeFormatter.ofPattern("d").format(hijri).contentEquals("1")){
                return DateTimeFormatter.ofPattern("MMMM d").format(hijri);
            }else{
                return DateTimeFormatter.ofPattern("d").format(hijri);
            }
        }
    }

    //return a list with the Hijri days of the week concatenated
   public static List<String> hijriWeekDays(List <String> list){

       list.set(0, list.get(0) + " / Al-áhad");
       list.set(1, list.get(1) + " / Al-ithnáyn");
       list.set(2, list.get(2) + " / Al-thalatha");
       list.set(3, list.get(3) + " / Al-arba'a");
       list.set(4, list.get(4) + " / Al-jamís");
       list.set(5, list.get(5) + " / Al-yuma'a");
       list.set(6, list.get(6) + " / Al-sabt");


       return list;
   }

   //return the number of the month within the Hijri Calendar
    public static String hijriMonthDays(Date fecha){

        return HijriCalendarConverter.convertirFecha(fecha,2);
    }
}

