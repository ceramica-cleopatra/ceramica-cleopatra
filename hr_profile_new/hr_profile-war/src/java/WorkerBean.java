
import e.HrEmpInfo;
import e.HrEmpMangers;
import e.HrMontlySalaryCalcPeriod;
import e.HrProfileMsg;
import e.HrProfilePrivilage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import sb.SessionBeanLocal;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DEV
 */
@Singleton
public class WorkerBean {

    private static SessionBeanLocal sessionBean;
    private AtomicBoolean busy = new AtomicBoolean(false);
    public static JedisPool pool;

    @PostConstruct
    public void init() {
        try {
            Context c = new InitialContext();
            sessionBean = (SessionBeanLocal) c.lookup("java:comp/env/SessionBean");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMinIdle(10);
        pool = new JedisPool(jedisPoolConfig, "20.1.1.46");
    }

    @Lock(LockType.READ)
    public void doTimerWork() throws InterruptedException {
        if (!busy.compareAndSet(false, true)) {
            return;
        }
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            System.out.print("##################Start Refresh Cache##########################");
            long startProcessTime = System.currentTimeMillis();
            List<HrEmpInfo> hrEmpInfoList = sessionBean.allHrEmpInfo();
            Pipeline p = jedis.pipelined();
            ObjectMapper objectMapper = new ObjectMapper();
            for (HrEmpInfo empInfo : hrEmpInfoList) {
                long startTime = System.currentTimeMillis();
                System.out.print("##################Start Refresh Menu Cache for " + empInfo.getEmpNo() + "##########################");
                List<HrProfilePrivilage> hrProfilePrivilages = sessionBean.allProfilePrivilage(empInfo.getEmpNo());
                p.del("menu:" + empInfo.getEmpNo());
                p.sync();
                for (HrProfilePrivilage hrProfilePrivilage : hrProfilePrivilages) {
                    HrProfilePrivilageDTO hrProfilePrivilageDTO = new HrProfilePrivilageDTO(Long.parseLong(hrProfilePrivilage.getId() + ""), hrProfilePrivilage.getEmpNo(),
                            new HrMenuTableDTO(hrProfilePrivilage.getMenuId().getId(), hrProfilePrivilage.getMenuId().getMenuName(), hrProfilePrivilage.getMenuId().getArabicName(), hrProfilePrivilage.getMenuId().getIcon(), hrProfilePrivilage.getMenuId().getParentId(), hrProfilePrivilage.getMenuId().getMenuOrder()), hrProfilePrivilage.getArabicName());
                    try {
                        String hrProfilePrivilageAsString = objectMapper.writeValueAsString(hrProfilePrivilageDTO);
                        jedis.hset("menu:" + hrProfilePrivilage.getEmpNo(), hrProfilePrivilage.getId().toString(), hrProfilePrivilageAsString);
                        jedis.expire("menu:" + hrProfilePrivilage.getEmpNo(), Integer.MAX_VALUE);
                    } catch (IOException ex) {
                        Logger.getLogger(CashHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.print("##################End Refresh Menu Cache for " + empInfo.getEmpNo() + " in " + ((System.currentTimeMillis() - startTime) / (1000)) + " sec ##########################");

                startTime = System.currentTimeMillis();
                System.out.print("##################Start Refresh Alerts Cache for " + empInfo.getEmpNo() + "##########################");
                List<HrProfileMsg> hrProfileMsgs = sessionBean.findAllProfilMsgs(empInfo.getEmpNo());
                p.del("alerts:" + empInfo.getEmpNo());
                p.del("msgs:" + empInfo.getEmpNo());
                p.del("decisions:" + empInfo.getEmpNo());
                p.del("tasks:" + empInfo.getEmpNo());
                p.sync();
                for (HrProfileMsg msg : hrProfileMsgs) {
                    String hrProfileMsgAsString = null;
                    try {
                        hrProfileMsgAsString = objectMapper.writeValueAsString(msg);
                    } catch (IOException ex) {
                        Logger.getLogger(CashHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (msg.getMsgType() == 1L) {
                        jedis.hset("alerts:" + msg.getEmpNo(), msg.getEntityName() + "" + msg.getMsgType() + "" + msg.getEmpNo() + "" + msg.getMsgId(), hrProfileMsgAsString);
                        jedis.expire("alerts:" + msg.getEmpNo(), Integer.MAX_VALUE);
                    } else if (msg.getMsgType() == 2L) {
                        jedis.hset("msgs:" + msg.getEmpNo(), msg.getEntityName() + "" + msg.getMsgType() + "" + msg.getEmpNo() + "" + msg.getMsgId(), hrProfileMsgAsString);
                        jedis.expire("msgs:" + msg.getEmpNo(), Integer.MAX_VALUE);
                    } else if (msg.getMsgType() == 3L) {
                        jedis.hset("decisions:" + msg.getEmpNo(), msg.getEntityName() + "" + msg.getMsgType() + "" + msg.getEmpNo() + "" + msg.getMsgId(), hrProfileMsgAsString);
                        jedis.expire("decisions:" + msg.getEmpNo(), Integer.MAX_VALUE);
                    } else if (msg.getMsgType() == 4L) {
                        jedis.hset("tasks:" + msg.getEmpNo(), msg.getEntityName() + "" + msg.getMsgType() + "" + msg.getEmpNo() + "" + msg.getMsgId(), hrProfileMsgAsString);
                        jedis.expire("tasks:" + msg.getEmpNo(), Integer.MAX_VALUE);
                    }
                }
                System.out.print("##################End Refresh Alert Cache for " + empInfo.getEmpNo() + " in " + ((System.currentTimeMillis() - startTime) / (1000)) + " sec ##########################");


                startTime = System.currentTimeMillis();
                System.out.print("##################Start Refresh Managers/Employees Cache for " + empInfo.getEmpNo() + "##########################");
                List<HrEmpMangers> hrEmpMangers = sessionBean.findAllEmpManagers(empInfo.getEmpNo());
                p.del("managers:" + empInfo.getEmpNo());
                p.del("employees:" + empInfo.getEmpNo());
                p.sync();
                for (HrEmpMangers empMng : hrEmpMangers) {
                    if (empInfo.getEmpNo() != empMng.getMngNo()) {
                        jedis.hset("managers:" + empInfo.getEmpNo(), empMng.getEmpNo() + "" + empMng.getMngNo(), empMng.getMngNo().toString());
                        jedis.expire("managers:" + empInfo.getEmpNo(), Integer.MAX_VALUE);

                    }
                    if (empInfo.getEmpNo() != empMng.getEmpNo()) {
                        jedis.hset("employees:" + empInfo.getEmpNo(), empMng.getEmpNo() + "" + empMng.getMngNo(), empMng.getEmpNo() + "");
                        jedis.expire("employees:" + empInfo.getEmpNo(), Integer.MAX_VALUE);
                    }
                }
                System.out.print("##################End Refresh Managers/Employees Cache for " + empInfo.getEmpNo() + " in " + ((System.currentTimeMillis() - startTime) / (1000)) + "##########################");
            }




            HrMontlySalaryCalcPeriod hscp = new HrMontlySalaryCalcPeriod();
            SimpleDateFormat sdf = new SimpleDateFormat("dd");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            if (Long.parseLong(sdf.format(new Date())) > 15) {
                cal.add(Calendar.DATE, -15);
                hscp = sessionBean.find_month_period(cal.getTime());
            } else {
                hscp = sessionBean.find_month_period(new Date());
            }
            try {
                jedis.set("hscp", new ObjectMapper().writeValueAsString(hscp));
                jedis.expire("hscp", Integer.MAX_VALUE);
            } catch (IOException ex) {
                Logger.getLogger(CashHandler.class.getName()).log(Level.SEVERE, null, ex);
            }


            System.out.print("##################End Refresh Process for All Employees in " + ((System.currentTimeMillis() - startProcessTime) / (1000)) + " sec ##########################");
        } finally {
            busy.set(false);
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
