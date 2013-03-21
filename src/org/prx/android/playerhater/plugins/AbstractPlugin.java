package org.prx.android.playerhater.plugins;

import org.prx.android.playerhater.Song;
import org.prx.android.playerhater.plugins.PlayerHaterPlugin;
import org.prx.android.playerhater.util.IPlayerHater;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

public abstract class AbstractPlugin implements PlayerHaterPlugin {

	private static final String TAG = "AbstractPlugin";
	private IPlayerHater mPlayerHater;
	private Context mContext;

	public AbstractPlugin() {

	}

	@Override
	public void onServiceStarted(Context context, IPlayerHater binder) {
		mContext = context;
		mPlayerHater = binder;
	}

	@Override
	public void onServiceRebind(Context context, IPlayerHater playerHater) {
		Log.w(TAG,
				"Forwarding a call to onServiceRebind => onServiceStarted in "
						+ getClass().getSimpleName());
		onServiceStarted(context, playerHater);
	}

	@Override
	public void onServiceStopping() {
		mContext = null;
		mPlayerHater = null;
	}

	@Override
	public void onPlaybackStarted() {
	}

	@Override
	public void onPlaybackResumed() {
		Log.w(TAG,
				"Forwarding a call to onPlaybackResumed => onPlaybackStarted "
						+ getClass().getSimpleName());
		onPlaybackStarted();
	}

	@Override
	public void onPlaybackStopped() {
	}

	@Override
	public void onTitleChanged(String title) {
	}

	@Override
	public void onArtistChanged(String artist) {
	}

	@Override
	public void onAlbumArtChanged(int resourceId) {
	}

	@Override
	public void onAlbumArtChangedToUri(Uri url) {
	}

	@Override
	public void onSongChanged(Song song) {
	}

	@Override
	public void onDurationChanged(int duration) {
	}

	@Override
	public void onAudioLoading() {
	}

	@Override
	public void onPlaybackPaused() {
	}

	@Override
	public void onNextTrackAvailable(Song nextSong) {
	}

	@Override
	public void onNextTrackUnavailable() {
	}

	@Override
	public void onIntentActivityChanged(PendingIntent pending) {
	}

	@Override
	public void onSongFinished(Song song, int reason) {
	}

	protected final IPlayerHater getPlayerHater() {
		return mPlayerHater;
	}

	public final Context getContext() {
		return mContext;
	}

	@Override
	public void onChangesComplete() {
	}
}
