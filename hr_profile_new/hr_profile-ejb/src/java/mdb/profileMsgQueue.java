/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import e.HrEmpMangers;
import e.HrProfileMsg;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */

@MessageDriven(mappedName = "jms/profileMsgQueue", activationConfig = {
@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class profileMsgQueue implements MessageListener {
@EJB
private SessionBeanLocal sessionBean;
    public profileMsgQueue() {
    }
    @Resource
    private MessageDrivenContext mdc;

    @Override
    public void onMessage(Message message) {
        //SessionBeanLocal sessionBean = lookupSessionBeanLocal();
        ObjectMessage objectMessage = (ObjectMessage) message;
        HrProfileMsg hrProfileMsg;
        try {
            hrProfileMsg = (HrProfileMsg) objectMessage.getObject();
            sessionBean.persistHrProfileMSG(hrProfileMsg);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }

    }

    private static SessionBeanLocal lookupSessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (SessionBeanLocal) c.lookup("java:global/hr_profile-war-new/SessionBean!sb.SessionBeanLocal");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
}
