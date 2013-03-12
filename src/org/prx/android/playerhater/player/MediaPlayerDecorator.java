package org.prx.android.playerhater.player;

import java.io.FileDescriptor;
import java.io.IOException;

import org.prx.android.playerhater.player.IPlayer.Player;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.net.Uri;

public abstract class MediaPlayerDecorator implements Player {
	
	protected final StateManager mPlayer;
	public MediaPlayerDecorator(StateManager player) {
		mPlayer = player;
	}

	@Override
	public int getState() {
		return mPlayer.getState();
	}

	@Override
	public void reset() {
		mPlayer.reset();
	}

	@Override
	public void release() {
		mPlayer.release();
	}

	@Override
	public void prepare() throws IOException, IllegalStateException {
		mPlayer.prepare();
	}

	@Override
	public void prepareAsync() throws IllegalStateException {
		mPlayer.prepareAsync();
	}

	@Override
	public void start() throws IllegalStateException {
		mPlayer.start();
	}

	@Override
	public void pause() throws IllegalStateException {
		mPlayer.pause();
	}

	@Override
	public void stop() throws IllegalStateException {
		mPlayer.stop();
	}

	@Override
	public void seekTo(int msec) {
		mPlayer.seekTo(msec);
	}

	@Override
	public boolean isPlaying() {
		return mPlayer.isPlaying();
	}

	@Override
	public int getCurrentPosition() {
		return mPlayer.getCurrentPosition();
	}

	@Override
	public int getDuration() {
		return mPlayer.getDuration();
	}

	@Override
	public void setAudioStreamType(int streamType) {
		mPlayer.setAudioStreamType(streamType);
	}

	@Override
	public void setDataSource(FileDescriptor fd) throws IllegalStateException,
			IOException, IllegalArgumentException, SecurityException {
		mPlayer.setDataSource(fd);
	}

	@Override
	public void setDataSource(String path) throws IllegalStateException,
			IOException, IllegalArgumentException, SecurityException {
		mPlayer.setDataSource(path);
	}

	@Override
	public void setDataSource(Context context, Uri uri)
			throws IllegalStateException, IOException,
			IllegalArgumentException, SecurityException {
		mPlayer.setDataSource(context, uri);
	}

	@Override
	public void setDataSource(FileDescriptor fd, long offset, long length)
			throws IllegalStateException, IOException, IllegalArgumentException {
		mPlayer.setDataSource(fd, offset, length);
	}

	@Override
	public void setOnErrorListener(OnErrorListener errorListener) {
		mPlayer.setOnErrorListener(errorListener);
	}

	@Override
	public void setOnBufferingUpdateListener(
			OnBufferingUpdateListener bufferingUpdateListener) {
		mPlayer.setOnBufferingUpdateListener(bufferingUpdateListener);
	}

	@Override
	public void setOnCompletionListener(OnCompletionListener completionListener) {
		mPlayer.setOnCompletionListener(completionListener);
	}

	@Override
	public void setOnInfoListener(OnInfoListener infoListener) {
		mPlayer.setOnInfoListener(infoListener);
	}

	@Override
	public void setVolume(float leftVolume, float rightVolume) {
		mPlayer.setVolume(leftVolume, rightVolume);
	}

	@Override
	public boolean equals(MediaPlayer mp) {
		return mPlayer.equals(mp);
	}

	@Override
	public MediaPlayer getBarePlayer() {
		return mPlayer.getBarePlayer();
	}

	@Override
	public void setNextMediaPlayer(StateManager mediaPlayer) {
		if (mPlayer instanceof Player) {
			((Player)mPlayer).setNextMediaPlayer(mediaPlayer);
			return;
		}
		throw new UnsupportedOperationException("This Player doesn't know how to do set the next media player."); 
	}

	@Override
	public void setOnPreparedListener(OnPreparedListener preparedListener) {
		mPlayer.setOnPreparedListener(preparedListener);
	}

	@Override
	public void setOnSeekCompleteListener(
			OnSeekCompleteListener seekCompleteListener) {
		mPlayer.setOnSeekCompleteListener(seekCompleteListener);
		
	}
	
	@Override
	public void swapPlayer(MediaPlayer barePlayer, int state) {
		mPlayer.swapPlayer(barePlayer, state);
	}
	
	@Override
	public void swap(StateManager player) {
		mPlayer.swap(player);
	}

	@Override
	public boolean prepare(Context context, Uri uri) {
		if (mPlayer instanceof Player) {
			return ((Player)mPlayer).prepare(context, uri);
		}
		throw new UnsupportedOperationException("This Player doesn't know how to automatically start.");
	}

	@Override
	public boolean prepareAndPlay(Context applicationContext, Uri uri,
			int position) {
		if (mPlayer instanceof Player) {
			return ((Player)mPlayer).prepareAndPlay(applicationContext, uri, position);
		}
		throw new UnsupportedOperationException("This Player doesn't know how to automatically start.");
	}
	
	@Override
	public String toString() {
		return "(" + getClass().getName() + ")::" + mPlayer.toString(); 
	}

}