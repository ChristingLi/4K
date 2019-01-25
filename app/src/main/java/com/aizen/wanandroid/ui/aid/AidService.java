package com.aizen.wanandroid.ui.aid;

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Handler;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.aizen.utils.LoggerUtils;
import com.orhanobut.hawk.Hawk;

import java.util.List;

/**
 * Created by ld on 2019/1/4.
 *
 * @author ld
 * @date 2019/1/4
 * 描    述：辅助服务
 */
public class AidService extends AccessibilityService {

    private Member mMember = new Member();

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        CharSequence eventNameChar = event.getClassName();
        String className = eventNameChar.toString();

        LoggerUtils.Logger("EventName",className);

        switch (eventType){
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                if(Hawk.get(AidConstant.ADD_FRIENDS,false)){
                    switch (className){
                        case "com.tencent.mm.ui.LauncherUI":
                            openGroup();
                            break;
                        case "com.tencent.mm.ui.contact.ChatroomContactUI":
                            break;
                        case "com.tencent.mm.ui.chatting.ChattingUI":
                            break;
                        case "com.tencent.mm.chatroom.ui.ChatroomInfoUI":
                            break;
                        case "com.tencent.mm.ui.contact.SelectContactUI":
                            break;
                    }
                }
                if(className.equals("com.tencent.mm.ui.widget.a.c")){

                }
                if (Hawk.get(AidConstant.FRIEND_SQUARE, false)) {
                    if (className.equals("com.tencent.mm.plugin.sns.ui.SnsTimeLineUI")) {

                    }
                }
                if (Hawk.get(AidConstant.RED_PACKET, false)) {
                    switch (className) {
                        case "com.tencent.mm.ui.LauncherUI":
                            break;
                        case "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI":
                            break;
                        case "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI" :
                            break;
                    }
                }
                break;
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                if(event.getParcelableData() != null  && event.getParcelableData() instanceof Notification){
                    Notification notification = (Notification) event.getParcelableData();
                    String content = notification.tickerText.toString();
                    if(content.contains("[微信红包]")){
                        PendingIntent intent =  notification.contentIntent;
                        try {
                            intent.send();
                        } catch (PendingIntent.CanceledException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }

    }

    String curGroup = "";
    Handler handler = new Handler();
    //打开群聊
    private void openGroup() {
        mMember = Hawk.get(AidConstant.MEMBER);
        if(mMember.getGuy().size() != 0){
            curGroup = AidConstant.GROUP_NAME_1;
            AccessibilityNodeInfo nodeInfo =  getRootInActiveWindow();
            if(nodeInfo!= null){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    List<AccessibilityNodeInfo> tabNoes =  nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/d3t");
                    for (AccessibilityNodeInfo tabNoe : tabNoes) {
                        if(tabNoe.getText().toString().equals("通讯录")){
                            tabNoe.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            handler.postDelayed(() -> {
                                AccessibilityNodeInfo newNodeInfo =  getRootInActiveWindow();
                                if(nodeInfo != null){
                                    List<AccessibilityNodeInfo> newTabNoeds =  newNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/mn");
                                    for (AccessibilityNodeInfo newTabNoed : newTabNoeds) {
                                        if(newTabNoed.getText().toString().equals("群聊")){
                                            newTabNoed.getParent().getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                            break;
                                        }
                                    }
                                }
                            },500L);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}
