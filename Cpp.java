
package com.alicorn.system.platform;

import android.util.Log;

public class Cpp
{
  public static final String ALICORN_TAG = "Alicorn";
  private native void DoInit();

	public Cpp()
	{
    DoInit();

    Log.v(Cpp.ALICORN_TAG, "Cpp was initialized.");
	}
}
