package com.LoveSea.fengCore.callback;

import com.LoveSea.fengCore.callback.api.CallbackAble;
import com.LoveSea.fengCore.callback.api.CallbackGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author xiahaifeng
 */
@Slf4j
public class MyOneDay implements CallbackAble<OneDayCallback> {
    CallbackGroup<OneDayCallback> callbackGroup = CallbackGroup.of();
    @Override
    public <V extends OneDayCallback> OneDayCallback set(Class<V> vClazz, V callback) {
        return callbackGroup.set(vClazz, callback);
    }

    @Override
    public <V extends OneDayCallback> Optional<OneDayCallback> get(Class<V> vClazz) {
        return Optional.ofNullable(callbackGroup.get(vClazz));
    }

    @Override
    public <V extends OneDayCallback> OneDayCallback remove(Class<V> vClazz) {
        return callbackGroup.remove(vClazz);
    }

    static class WashFaceAction implements WashFaceCallback.Action {
        boolean washFace = false;

        @Override
        public void washFace() {
            washFace = true;
        }

        @Override
        public void notWashFace() {
            washFace = false;
        }

        public boolean isWashFace() {
            return washFace;
        }
    }

    public void execute() {
        log.info("start a day");
        log.info(" get up");
        log.info("start to wash face");
        WashFaceAction action = new WashFaceAction();
        callbackGroup.run(WashFaceCallback.class, new WashFaceCallback.Params(), action);
        log.info("is wash face:{}", action.isWashFace());
        log.info("end to wash face");
    }

    public static void main(String[] args) {
        MyOneDay myOneDay = new MyOneDay();
        myOneDay.set(WashFaceCallback.class, (params, action) -> {
            log.info("wash face at {}", params.date());
            action.notWashFace();
        });
        myOneDay.execute();
    }
}