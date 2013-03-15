package org.prx.android.playerhater.util;

import android.content.Context;

public class ContextRefSpec {
	private final int mHash;
	private final Context mContext;
	private final int mId;
	public ContextRefSpec(Context context, int id) {
		mContext = context;
		mHash = context.hashCode() + (id * 20000);
		mId = id;
	}
	
	public int getId() {
		return mId;
	}
	
	@Override
	public int hashCode() {
		return mHash;
	}
	
	@Override
	public boolean equals(Object o) {
		return o.hashCode() == mHash;
	}

	public Context getContext() {
		return mContext;
	}
}