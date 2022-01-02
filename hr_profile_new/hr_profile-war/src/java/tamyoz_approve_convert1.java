
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahmed abbas
 */
public class tamyoz_approve_convert1 implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException
    {
       try {System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            return Boolean.valueOf(value)==true ? 3  : 2;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("yyyyyyyyyyyyyyyyyyyyyyyy");
        return (Long)value==3L ? "true" : "false";
    }

}
