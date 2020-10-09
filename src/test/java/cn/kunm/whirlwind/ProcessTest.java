package cn.kunm.whirlwind;

import cn.kunm.whirlwind.model.Context;
import cn.kunm.whirlwind.model.Event;
import cn.kunm.whirlwind.model.Handler;
import cn.kunm.whirlwind.model.Status;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Desc:
 * @Date: 2020/10/9
 * @author: kunm
 * @modified_user: kunm
 * @modified_date: 2020/10/9
 */

public class ProcessTest {

    @Test
    public void testNormalProcess() throws InterruptedException, IllegalAccessException {
        Set<Status> allStatus = new HashSet<>();

        // 起始状态
        Status initStatus = new Status("start", true, false);
        // 中间状态
        Status midAStatus = new Status("midA");
        Status midA1Status = new Status("midA1");
        Status midBStatus = new Status("midB");
        Status midB1Status = new Status("midB1");
        Status midB2Status = new Status("midB2");
        Status midCStatus = new Status("midC");
        Status midC1Status = new Status("midC1");
        Status midC2Status = new Status("midC2");
        Status midC3Status = new Status("midC3");
        // 终止状态
        Status endStatus = new Status("end", false, true);

        // 状态流转
        initStatus.setAllowNextStatus(new HashSet<>(Arrays.asList(midAStatus, midBStatus, midCStatus)));
        midAStatus.setAllowNextStatus(new HashSet<>(Arrays.asList(midA1Status)));
        midBStatus.setAllowNextStatus(new HashSet<>(Arrays.asList(midB1Status, midB2Status)));
        midCStatus.setAllowNextStatus(new HashSet<>(Arrays.asList(midC1Status, midC2Status, midC3Status)));

        allStatus.addAll(Arrays.asList(initStatus, midAStatus, midA1Status, midBStatus, midB1Status, midB2Status,
                midCStatus, midC1Status, midC2Status, midC3Status, endStatus));


        StatusMachine sm = new StatusMachine(allStatus);
        sm.start();

        // Event eS = new Event("es", "start", "midA", new HS());
        // Event eA = new Event("ea", "midA", "midA1", new HA());
        // Event eAE = new Event("ea", "midA1", "end", new HE());
        // sm.sendEvent(eS);
        // sm.sendEvent(eA);
        // sm.sendEvent(eAE);

        Event eS = new Event("es", "start", "midB", new HS());
        Event eB1 = new Event("eb1", "midB", "midB1", new HB());
        // Event eB2 = new Event("eb2", "midB1", "midB2", new HB());
        Event eBE = new Event("ebe", "midB1", "end", new HE());
        sm.sendEvent(eS);
        sm.sendEvent(eB1);
        // sm.sendEvent(eB2);
        sm.sendEvent(eBE);


        // Event eC1 = new Event("ec", "midC", "midC1", new HC());
        // Event eC2 = new Event("ec", "midC", "midC2", new HC());
        // Event eC3 = new Event("ec", "midC", "midC3", new HC());
        // Event eE = new Event("ee", "midC3", "end", new HE());


        // sm.sendEvent(eB1);
        // sm.sendEvent(eS);
        // sm.sendEvent(eS);
        // sm.sendEvent(eS);
    }



    class HS implements Handler{

        @Override
        public void execute(Context context) {
            System.out.println("hs");
        }
    }

    class HA implements Handler{

        @Override
        public void execute(Context context) {
            System.out.println("ha");
        }
    }

    class HB implements Handler{

        @Override
        public void execute(Context context) {
            System.out.println("hb");
        }
    }
    class HC implements Handler{

        @Override
        public void execute(Context context) {
            System.out.println("hc");
        }
    }

    class HE implements Handler{

        @Override
        public void execute(Context context) {
            System.out.println("he");
        }
    }

}
