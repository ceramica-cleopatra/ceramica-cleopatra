
import java.util.TimeZone;
import javax.faces.convert.DateTimeConverter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class CustomConvertDateTimestamp extends DateTimeConverter {

    public CustomConvertDateTimestamp() {
        super();
        setTimeZone(TimeZone.getDefault());
        setDateStyle("short");
        setTimeStyle("short");
        setPattern("dd-MM-yyyy HH:mm");
        setType("both");
    }
}
