package fr.antoinebouquet.capacitor.deezer;

import android.app.Activity;
import android.util.Log;

import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.request.event.DeezerError;
import com.deezer.sdk.player.PlayerWrapper;
import com.deezer.sdk.player.TrackPlayer;
import com.deezer.sdk.player.exception.TooManyPlayersExceptions;
import com.deezer.sdk.player.networkcheck.WifiAndMobileNetworkStateChecker;
import com.getcapacitor.PluginCall;

import java.util.List;

public class DeezerSDK {
    private DeezerConnect mConnect;
    private PlayerWrapper mPlayerWrapper;

    private final String LOG_TAG = "DeezerSDK";

    public DeezerSDK() {
    }

    public boolean init(Activity mActivity, String appId) {
        mConnect = new DeezerConnect(mActivity, appId);
        return true;
    }

    public void login(Activity mActivity, PluginCall call, List<String> permissions) {
        final AuthListener listener = new AuthListener(call);

        mActivity.runOnUiThread(() -> mConnect.authorize(mActivity, permissions.toArray(new String[0]), listener));
    }

    public void playTrack(Activity mActivity, PluginCall call, Long trackId) {
        if (mPlayerWrapper != null) {
            mPlayerWrapper.stop();
            mPlayerWrapper.release();
            mPlayerWrapper = null;
        }

        try {
            // create the track player
            mPlayerWrapper = new TrackPlayer(mActivity.getApplication(), mConnect, new WifiAndMobileNetworkStateChecker());

            // add a listener
            ((TrackPlayer) mPlayerWrapper).addPlayerListener(new PlayerListener(call));

            // play the given track id
            ((TrackPlayer) mPlayerWrapper).playTrack(trackId);
        } catch (TooManyPlayersExceptions e) {
            Log.e(LOG_TAG, "TooManyPlayersExceptions", e);
            call.reject("TooManyPlayersExceptions", e);
        } catch (DeezerError e) {
            Log.e(LOG_TAG, "DeezerError", e);
            call.reject("DeezerError", e);
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG, "NumberFormatException", e);
            call.reject("NumberFormatException", e);
        }
    }
}
