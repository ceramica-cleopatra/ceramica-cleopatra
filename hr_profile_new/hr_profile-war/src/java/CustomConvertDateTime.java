/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.TimeZone;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;
/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@RequestScoped
public class CustomConvertDateTime extends DateTimeConverter {

    /** Creates a new instance of CustomConvertDateTime */
    public CustomConvertDateTime() 
    {
        super();
        setTimeZone(TimeZone.getDefault());
        setPattern("dd/MM/yyyy");
}

    }


