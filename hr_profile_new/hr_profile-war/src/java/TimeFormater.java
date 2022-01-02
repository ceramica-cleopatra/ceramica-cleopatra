
import java.util.TimeZone;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
@FacesConverter(value="TimeFormater")
public class TimeFormater extends DateTimeConverter{
    public TimeFormater(){
        super();
        setTimeZone(TimeZone.getDefault());
        setPattern("HH:mm");
        setType("Time");
    }
}
