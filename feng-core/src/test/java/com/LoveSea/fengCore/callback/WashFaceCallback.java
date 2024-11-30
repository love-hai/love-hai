package com.LoveSea.fengCore.callback;

import com.LoveSea.fengCore.callback.api.CallBackParams;
import com.LoveSea.fengCore.callback.api.CallbackAction;
import com.LoveSea.fengCore.callback.api.PACallback;

import java.util.Date;

/**
 * @author xiahaifeng
 */

public interface WashFaceCallback extends OneDayCallback, PACallback<WashFaceCallback.Params, WashFaceCallback.Action> {

    class Params implements CallBackParams {
        Date date = new Date();

        public Date date() {
            return date;
        }
    }

    interface Action extends CallbackAction {
        boolean washFace = true;
        public void washFace();
        public void notWashFace();
    }
}