
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author data
 */

@FacesConverter("booleanConverter")
public class BooleanConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.equals("true"))
            return Boolean.valueOf("true");
        else
            return Boolean.valueOf("false");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if((Boolean)value)
            return "true";
        else
            return "false";
    }

}
