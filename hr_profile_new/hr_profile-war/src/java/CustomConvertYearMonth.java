
import java.util.TimeZone;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.convert.DateTimeConverter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class CustomConvertYearMonth extends DateTimeConverter{

    public CustomConvertYearMonth() {
        super();
        setTimeZone(TimeZone.getDefault());
        setPattern("MM/yyyy");
    }

}
