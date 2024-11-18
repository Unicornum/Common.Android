
package com.alicorn.system.platform;

import android.graphics.Rect;
import android.view.inputmethod.InputMethodManager;
import android.util.Log;

public class Activity extends com.alicorn.system.platform.Context
{
	private static android.app.Activity m_Activity = null;

	public Activity(android.app.Activity _Activity)
	{
    super(_Activity.getBaseContext());
		m_Activity = _Activity;

    Log.v(Cpp.ALICORN_TAG, "Activity was initialized.");
	}

  public static int GetStatusBarHeight()
  {
    Rect DisplayFrameRect = new Rect();

    Activity.m_Activity.getWindow().getDecorView()
      .getWindowVisibleDisplayFrame(DisplayFrameRect);
    final int Result = DisplayFrameRect.top;

    Log.v(Cpp.ALICORN_TAG, "Call GetStatusBarHeight(): " + Result);

    return Result;
  }

  public static void ShowSoftKeyboard()
  {
    Log.v(Cpp.ALICORN_TAG, "Call ShowSoftKeyboard()");

    InputMethodManager Manager = (InputMethodManager)Activity.m_Activity
      .getSystemService(android.content.Context.INPUT_METHOD_SERVICE);
    Manager.showSoftInput(Activity.m_Activity.getWindow().getDecorView(), 0);
  }
}
