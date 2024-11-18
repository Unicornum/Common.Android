
package com.alicorn.system.platform;

import android.os.Build;
import android.util.Log;

public class Context extends com.alicorn.system.platform.Cpp
{
  private static android.content.Context m_Context = null;

	public Context(android.content.Context _Context)
	{
    m_Context = _Context;

    Log.v(Cpp.ALICORN_TAG, "Context was initialized.");
	}

  public static String GetPackageName()
  {
    final String Result = Context.m_Context.getPackageName();

    Log.v(Cpp.ALICORN_TAG, "Call GetPackageName(): " + Result);

    return Result;
  }

  public static String GetApplicationDirectory()
	{
		final String Result = Context.m_Context.getFilesDir().getParent();

    Log.v(Cpp.ALICORN_TAG, "Call GetApplicationDirectory(): " + Result);

    return Result;
	}

  public static String GetNativeLibraryDirectory()
  {
    final String Result = 
      Context.m_Context.getApplicationInfo().nativeLibraryDir;

    Log.v(Cpp.ALICORN_TAG, "Call GetNativeLibraryDirectory(): " + Result);

    return Result;
  }

	public static String GetExternalStorageDirectory()
	{
		final String Result = 
      Context.m_Context.getExternalFilesDir(null).getAbsolutePath();

    Log.v(Cpp.ALICORN_TAG, "Call GetExternalStorageDirectory(): " + Result);

    return Result;
	}

  public static String GetResourceString(String _Name)
  {
    Log.v(Cpp.ALICORN_TAG, "Call GetResourceString(" + _Name + ")");

    int StringId = Context.m_Context.getResources()
      .getIdentifier(_Name, "string", GetPackageName());
    final String Result = Context.m_Context.getResources().getString(StringId);

    Log.v(Cpp.ALICORN_TAG, "Call GetResourceString(): " + Result);

    return Result;
  }

  public static String GetAbi()
  {
    String Result = new String();

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) 
    {
      Result = Build.SUPPORTED_ABIS[0];
    }
    else
    {
      Result = Build.CPU_ABI;
    }

    Log.v(Cpp.ALICORN_TAG, "Call GetAbi(): " + Result);

    return Result;
  }
}
