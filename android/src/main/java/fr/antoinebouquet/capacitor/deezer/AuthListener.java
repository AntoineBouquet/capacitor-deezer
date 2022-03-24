package fr.antoinebouquet.capacitor.deezer;

import android.app.Activity;
import android.os.Bundle;

import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.connect.SessionStore;
import com.deezer.sdk.network.connect.event.DialogListener;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;

public class AuthListener implements DialogListener {
    final PluginCall mCall;
    final DeezerConnect mDeezerConnect;
    final Activity mActivity;

    public AuthListener(PluginCall call, DeezerConnect deezerConnect, Activity activity) {
        mCall = call;
        mDeezerConnect = deezerConnect;
        mActivity = activity;
    }

    @Override
    public void onComplete(Bundle bundle) {
        DeezerSDKPlugin.token = String.valueOf(bundle.get("access_token"));

        // store the current authentication info
        SessionStore sessionStore = new SessionStore();
        sessionStore.save(mDeezerConnect, mActivity);

        JSObject result = new JSObject();
        result.put("result", true);
        result.put("access_token", DeezerSDKPlugin.token);

        mCall.resolve(result);
        mCall.setKeepAlive(false);
    }

    @Override
    public void onCancel() {
        mCall.reject("AUTH_CANCELED");
    }

    @Override
    public void onException(Exception e) {
        mCall.reject("AUTH_ERROR", e);
    }
}
