package fr.antoinebouquet.capacitor.deezer;

import android.app.Activity;
import android.util.Log;

import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.connect.SessionStore;
import com.deezer.sdk.network.request.event.DeezerError;
import com.deezer.sdk.player.PlayerWrapper;
import com.deezer.sdk.player.TrackPlayer;
import com.deezer.sdk.player.exception.TooManyPlayersExceptions;
import com.deezer.sdk.player.networkcheck.WifiAndMobileNetworkStateChecker;
import com.getcapacitor.Bridge;
import com.getcapacitor.PluginCall;

import java.util.List;

public class DeezerSDK {
    private DeezerConnect mConnect;
    private PlayerWrapper mPlayerWrapper;

    private final String LOG_TAG = "DeezerSDK";

    private Long currentTrackId;

    public DeezerSDK() {
    }

    public boolean init(Activity mActivity, String appId) {
        mConnect = new DeezerConnect(mActivity, appId);
        return true;
    }

    public boolean login(Activity mActivity, PluginCall call, List<String> permissions) {
        SessionStore sessionStore = new SessionStore();
        if(! sessionStore.restore(mConnect, mActivity.getApplicationContext())) {
            final AuthListener listener = new AuthListener(call, mConnect, mActivity);
            mActivity.runOnUiThread(() -> mConnect.authorize(mActivity, permissions.toArray(new String[0]), listener));
            return false;
        } else {
            return true;
        }
    }

    public void logout(Activity mActivity) {
        if(mConnect != null) {
            mConnect.logout(mActivity.getApplicationContext());
        }

        new SessionStore().clear(mActivity.getApplicationContext());
    }

    public boolean playTrack(Activity mActivity, PluginCall call, Bridge bridge, Long trackId) {
        // restore existing deezer Connection
        new SessionStore().restore(mConnect, mActivity.getApplicationContext());

        if (mPlayerWrapper != null) {
            mPlayerWrapper.stop();
            mPlayerWrapper.release();
            mPlayerWrapper = null;
        }

        if(mConnect == null || ! mConnect.isSessionValid()) {
            call.reject("SESSION_INVALID");
            return false;
        }

        try {
            // create the track player
            mPlayerWrapper = new TrackPlayer(mActivity.getApplication(), mConnect, new WifiAndMobileNetworkStateChecker());

            // add a listener
            ((TrackPlayer) mPlayerWrapper).addPlayerListener(new PlayerListener(call, bridge));

            // play the given track id
            ((TrackPlayer) mPlayerWrapper).playTrack(trackId);
            this.currentTrackId = trackId;
        } catch (TooManyPlayersExceptions e) {
            Log.e(LOG_TAG, "TooManyPlayersExceptions", e);
            call.reject("TooManyPlayersExceptions", e);
            return false;
        } catch (DeezerError e) {
            Log.e(LOG_TAG, "DeezerError", e);
            call.reject("DeezerError", e);
            return false;
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG, "NumberFormatException", e);
            call.reject("NumberFormatException", e);
            return false;
        }

        return true;
    }

    public Long play(Activity mActivity, PluginCall call) {
        // restore existing deezer Connection
        new SessionStore().restore(mConnect, mActivity.getApplicationContext());

        if(mConnect == null || ! mConnect.isSessionValid()) {
            call.reject("SESSION_INVALID");
            return null;
        }

        if (mPlayerWrapper != null) {
            mPlayerWrapper.play();
        }

        return this.currentTrackId;
    }

    public void pause() {
        if (mPlayerWrapper != null) {
            mPlayerWrapper.pause();
        }
    }
}
