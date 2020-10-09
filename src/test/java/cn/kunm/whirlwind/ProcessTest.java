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
        Status midBStatus = new Status("midB");
        Status midCStatus = new Status("midC");
        // 终止状态
        Status endStatus = new Status("end", false, true);

        // 状态流转
        initStatus.setAllowNextStatus(new HashSet<>(Arrays.asList(midAStatus)));
        midAStatus.setAllowNextStatus(new HashSet<>(Arrays.asList(midBStatus)));
        midBStatus.setAllowNextStatus(new HashSet<>(Arrays.asList(midCStatus)));
        // 流转到终止状态的from状态可以不显式声明
        // midCStatus.setAllowNextStatus(new HashSet<>(Arrays.asList(endStatus)));

        allStatus.addAll(Arrays.asList(initStatus, midAStatus, midBStatus, midCStatus, endStatus));

        StatusMachine sm = new StatusMachine(allStatus);
        sm.start();

        Event eA = new Event("ea", "start", "midA", new HS());
        Event eB = new Event("eb", "midA", "midB", new HA());
        Event eC = new Event("ec", "midB", "midC", new HE());
        Event eE = new Event("ee", "midC", "end", new HE());
        sm.sendEvent(eA);
        sm.sendEvent(eB);
        sm.sendEvent(eC);
        sm.sendEvent(eE);
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
