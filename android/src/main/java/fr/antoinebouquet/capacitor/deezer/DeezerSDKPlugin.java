package fr.antoinebouquet.capacitor.deezer;

import android.content.Context;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.List;

@CapacitorPlugin(name = "DeezerSDK")
public class DeezerSDKPlugin extends Plugin {

    private final DeezerSDK implementation;
    public static String token;

    public DeezerSDKPlugin() {
        implementation = new DeezerSDK();
    }


    @PluginMethod
    public void initialize(PluginCall call) {
        String appId = call.getString("appId");

        try {
            boolean resInit = implementation.init(getActivity(), appId);
            JSObject ret = new JSObject();
            ret.put("value", resInit);
            call.resolve(ret);
        } catch(Exception e) {
            call.reject("ERROR_INIT_DEEZER", e);
        }
    }

    @PluginMethod
    public void login(PluginCall call) {
        call.setKeepAlive(true);

        JSArray scopesArray = call.getArray("permissions");
        if (scopesArray == null) {
            call.reject("Permissions missing");
            return;
        }
        List<String> permissions;
        try {
            permissions = scopesArray.toList();
        } catch (JSONException e) {
            call.reject("Provided scopes format is invalid");
            return;
        }

        if(implementation.login(getActivity(), call, permissions)) {
            JSObject ret = new JSObject();
            ret.put("result", true);
            call.resolve(ret);
        }
    }

    @PluginMethod
    public void logout(PluginCall call) {
        implementation.logout(getActivity());

        JSObject ret = new JSObject();
        ret.put("result", true);
        call.resolve(ret);
    }

    @PluginMethod
    public void playTrack(PluginCall call) {
        call.setKeepAlive(true);

        Context con = this.getContext();
        PowerManage po = new PowerManage();
        po.activateLowBAttery(con);

        String trackIdStr = call.getString("trackId");

        if(trackIdStr == null) {
            call.reject("Track id missing");
            return;
        }

        try {
            implementation.playTrack(getActivity(), call, bridge, Long.parseLong(trackIdStr));
        } catch(NumberFormatException exception) {
            call.reject("Track id must be a number value in a string");
        }
    }

    @PluginMethod
    public void play(PluginCall call) {
        call.setKeepAlive(true);

        Long currentTrackId = implementation.play(getActivity(), call);
        if(currentTrackId != null) {
            sendPlayEvent(currentTrackId.toString());
        }


        JSObject ret = new JSObject();
        ret.put("result", true);
        call.resolve(ret);
    }

    @PluginMethod
    public void pause(PluginCall call) {
        implementation.pause();

        JSObject ret = new JSObject();
        ret.put("result", true);
        call.resolve(ret);
    }

    private void sendPlayEvent(String uri) {
        Gson gson = new Gson();
        PlayUri playUri = new PlayUri(uri);
        bridge.triggerWindowJSEvent("playRecord", gson.toJson(playUri));
    }
}
