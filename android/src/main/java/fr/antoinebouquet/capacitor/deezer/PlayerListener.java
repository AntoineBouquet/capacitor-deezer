package fr.antoinebouquet.capacitor.deezer;

import com.deezer.sdk.model.PlayableEntity;
import com.deezer.sdk.player.event.RadioPlayerListener;
import com.getcapacitor.PluginCall;

public class PlayerListener implements RadioPlayerListener {
    private final PluginCall mCall;

    public PlayerListener(PluginCall call) {
        mCall = call;
    }

    @Override
    public void onTooManySkipsException() {

    }

    @Override
    public void onAllTracksEnded() {

    }

    @Override
    public void onPlayTrack(PlayableEntity playableEntity) {

    }

    @Override
    public void onTrackEnded(PlayableEntity playableEntity) {

    }

    @Override
    public void onRequestException(Exception e, Object o) {

    }
}
