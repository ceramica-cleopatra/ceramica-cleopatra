
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahmed abbas
 */
public class handlePhases implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event)
    {
        if(event.getPhaseId()==PhaseId.RESTORE_VIEW){
           System.out.println("render response");
        FacesContext context = event.getFacesContext();
        ExternalContext ec = context.getExternalContext();
        String usercode=null;
         final HttpServletResponse res = (HttpServletResponse)ec.getResponse();
       try{
        ValueBinding vb = context.getApplication().createValueBinding("#{loginBean.username}");
        usercode = vb.getValue(context).toString();
           }catch(Exception e){}
        boolean loginPage =
          context.getViewRoot().getViewId().lastIndexOf("index") > -1 ? true : false;
        if (!loginPage && usercode.length()>0) {
                try {
                    //            NavigationHandler nh = context.getApplication().getNavigationHandler();
                    //            nh.handleNavigation(context, null, "index");
                    res.sendRedirect("index.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(handlePhases.class.getName()).log(Level.SEVERE, null, ex);
                }
       }
    }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
//     try{
//        if(event.getPhaseId()==PhaseId.RENDER_RESPONSE)
//      {System.out.println("current phase"+event.getPhaseId().toString());
//
//          System.out.println("1111111111111111111");
//      FacesContext fc=FacesContext.getCurrentInstance();
//          System.out.println("222222222222222222");
//      ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
//          System.out.println("333333333333333333333");
//     String usercode=null;
//          try{
//        usercode= vb.getValue(fc).toString();
//          System.out.println("4444444444444444444444");
//     }catch(Exception e){if(!fc.getViewRoot().getViewId().toString().equals("/index.xhtml") && fc.getViewRoot().getViewId().toString().contains(".xhtml"))fc.getApplication().getNavigationHandler().handleNavigation(fc,"","index.xhtml");}
//      if(usercode==null && !fc.getViewRoot().getViewId().toString().equals("/index.xhtml") && fc.getViewRoot().getViewId().toString().contains(".xhtml"))
//      {
//          System.out.println("fc.getViewRoot().getViewId().toString()"+fc.getViewRoot().getViewId().toString());
//     fc.getApplication().getNavigationHandler().handleNavigation(fc,"","index.xhtml");
//    System.out.println("5555555555555555555555");
//      }
//
//      }
//        }catch(Exception e){FacesContext fc=FacesContext.getCurrentInstance();fc.getApplication().getNavigationHandler().handleNavigation(fc,"","index.xhtml");}
       
    }
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}
