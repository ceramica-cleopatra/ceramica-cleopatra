
import e.HrCheckupDutyDt;
import e.HrEmpInfo;
import javax.annotation.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sb.SessionBeanLocal;

@FacesConverter(value = "EmpConverter")
public class EmpConverter implements Converter {

    SessionBeanLocal sessionBean = lookupSessionBeanLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.length() > 0) {
            HrEmpInfo hrEmpInfo = sessionBean.finduserbyid(Long.parseLong(value));
            if(hrEmpInfo!=null && hrEmpInfo.getEmpName()!=null)
                return hrEmpInfo;
             else
                 return null;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((HrEmpInfo) value).getEmpNo());
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
