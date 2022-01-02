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
public class CustomConvertTime extends DateTimeConverter {

    /** Creates a new instance of CustomConvertTime */
    public CustomConvertTime() {
        super();
        System.out.println(TimeZone.getDefault().getID());
        setTimeZone(TimeZone.getDefault());
        setPattern("HH:mm");
        setType("time");
}


}
