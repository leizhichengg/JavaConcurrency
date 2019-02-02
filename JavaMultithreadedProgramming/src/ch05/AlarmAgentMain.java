package ch05;

import util.Tools;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-02 16:02
 */
public class AlarmAgentMain {
    final static AlarmAgent alarmAgent;
    static{
        alarmAgent = AlarmAgent.getInstance();
        alarmAgent.init();
    }

    public static void main(String[] args) throws InterruptedException{

        alarmAgent.sendAlarm("Database offline");
        Tools.randomPause(12000);
        alarmAgent.sendAlarm("XXX service unreachable!");
    }
}
