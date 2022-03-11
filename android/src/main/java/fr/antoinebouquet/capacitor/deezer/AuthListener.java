package fr.antoinebouquet.capacitor.deezer;

import android.os.Bundle;

import com.deezer.sdk.network.connect.event.DialogListener;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;

public class AuthListener implements DialogListener {
    final PluginCall mCall;

    public AuthListener(PluginCall call) {
        mCall = call;
    }

    @Override
    public void onComplete(Bundle bundle) {
        DeezerSDKPlugin.token = String.valueOf(bundle.get("access_token"));

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
