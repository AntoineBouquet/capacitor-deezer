package fr.antoinebouquet.capacitor.deezer;

import com.deezer.sdk.model.PlayableEntity;
import com.deezer.sdk.player.event.RadioPlayerListener;
import com.getcapacitor.Bridge;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import com.google.gson.Gson;

public class PlayerListener implements RadioPlayerListener {
    private final Bridge bridge;
    private final PluginCall call;

    public PlayerListener(PluginCall call, Bridge bridge) {
        this.call = call;
        this.bridge = bridge;
    }

    @Override
    public void onTooManySkipsException() {
        call.reject("PlayerListener - onTooManySkipsException");
    }

    @Override
    public void onAllTracksEnded() {

    }

    @Override
    public void onPlayTrack(PlayableEntity playableEntity) {
        JSObject ret = new JSObject();
        ret.put("result", true);
        call.resolve(ret);

        Gson gson = new Gson();
        PlayUri playUri = new PlayUri(Long.toString(playableEntity.getId()));
        bridge.triggerWindowJSEvent("playRecord", gson.toJson(playUri));
    }

    @Override
    public void onTrackEnded(PlayableEntity playableEntity) {
        Gson gson = new Gson();
        PlayUri playUri = new PlayUri(Long.toString(playableEntity.getId()));
        bridge.triggerWindowJSEvent("deezer-endTrack", gson.toJson(playUri));
    }

    @Override
    public void onRequestException(Exception e, Object o) {
        call.reject("PlayerListener - onRequestException", e);
    }

    private void sendPlayEvent(String uri) {
        Gson gson = new Gson();
        PlayUri playUri = new PlayUri(uri);
        bridge.triggerWindowJSEvent("playRecord", gson.toJson(playUri));
    }
}
