
import e.HrLocation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sb.SessionBeanLocal;
import sb.SessionBeanRemote;

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
public class LocationConvertor implements Converter{
  

    
    SessionBeanLocal sessionBean=lookupSessionBeanLocal() ;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
      if(value!=null)
      {
        HrLocation hrLocation = sessionBean.getLocationByName(value);
        return hrLocation;
        }
     else
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((HrLocation)value).getName();
    }

   private SessionBeanLocal lookupSessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (SessionBeanLocal) c.lookup("java:comp/env/SessionBean");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

}
