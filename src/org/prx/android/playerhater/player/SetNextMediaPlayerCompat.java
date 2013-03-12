package org.prx.android.playerhater.player;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Build;
import android.os.Handler;

public interface SetNextMediaPlayerCompat {

	public class Compat implements SetNextMediaPlayerCompat, OnCompletionListener,
			Runnable {
		private MediaPlayerWithState mNextMediaPlayer;
		private final Handler mHandler = new Handler();
		private final MediaPlayerWithState mStateManager;
		private OnCompletionListener mOnCompletionListener;
		private MediaPlayer mPlayer;

		public Compat(MediaPlayerWithState stateManager) {
			mStateManager = stateManager;
			stateManager.setOnCompletionListener(this);
		}

		@Override
		public void setNextMediaPlayer(MediaPlayerWithState next) {
			mNextMediaPlayer = next;
			if (mNextMediaPlayer != null) {
				switch (mNextMediaPlayer.getState()) {
				case Player.STARTED:
				case Player.PAUSED:
				case Player.PLAYBACK_COMPLETED:
					mNextMediaPlayer.stop();
				case Player.STOPPED:
				case Player.INITIALIZED:
					mNextMediaPlayer.prepareAsync();
					break;
				}
			}
		}

		@Override
		public void onCompletion(MediaPlayer mp) {
			if (mNextMediaPlayer != null) {
				if (mNextMediaPlayer.getState() == Player.PREPARED) {
					mStateManager.swap(mNextMediaPlayer);
					if (mOnCompletionListener != null) {
						mOnCompletionListener.onCompletion(mp);
					}
					mPlayer = null;
				} else {
					mPlayer = mp;
					mHandler.postDelayed(this, 200);
				}
			}
		}

		@Override
		public void run() {
			onCompletion(mPlayer);
		}

		@Override
		public void setOnCompletionListener(OnCompletionListener onCompletion) {
			mOnCompletionListener = onCompletion;
		}
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public class Modern implements SetNextMediaPlayerCompat, OnCompletionListener {
		private final MediaPlayerWithState mMediaPlayer;
		private MediaPlayerWithState mNextMediaPlayer;
		private OnCompletionListener mOnComplete;

		public Modern(MediaPlayerWithState stateManager) {
			stateManager.setOnCompletionListener(this);
			mMediaPlayer = stateManager;
		}

		@Override
		public void setNextMediaPlayer(MediaPlayerWithState next) {
			MediaPlayerWithState was = mNextMediaPlayer;
			mNextMediaPlayer = next;
			MediaPlayer tmp = null;
			if (next != null) {
				tmp = mNextMediaPlayer.getBarePlayer();
			}
			if (tmp != null || was != null) {
				mMediaPlayer.getBarePlayer().setNextMediaPlayer(tmp);
			}
		}

		@Override
		public void setOnCompletionListener(OnCompletionListener onCompletion) {
			mOnComplete = onCompletion;
		}

		@Override
		public void onCompletion(MediaPlayer mp) {
			if (mNextMediaPlayer != null)
				mMediaPlayer.swap(mNextMediaPlayer);
			if (mOnComplete != null) {
				mOnComplete.onCompletion(mp);
			}
		}

	}

	public void setNextMediaPlayer(MediaPlayerWithState mediaPlayer);

	public void setOnCompletionListener(OnCompletionListener onCompletion);
}