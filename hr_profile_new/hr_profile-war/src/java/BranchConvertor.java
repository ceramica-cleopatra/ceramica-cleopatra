
import e.CrmkBranch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sb.SessionBeanRemote;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author data
 */
public class BranchConvertor implements Converter {


    SessionBeanRemote sessionBean=lookupSessionBeanRemote() ;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
      if(value!=null)
      {System.out.println("value"+value);
        CrmkBranch crmkBranch = null ;
        return crmkBranch;
        }
     else
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("loc loc"+((CrmkBranch)value).getName());
        return ((CrmkBranch)value).getName();
    }

    private SessionBeanRemote lookupSessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (SessionBeanRemote) c.lookup("sb.SessionBeanRemote#sb.SessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
