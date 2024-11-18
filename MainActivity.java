
package com.alicorn;

import android.app.NativeActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.view.KeyEvent;
import com.alicorn.system.platform.Activity;

public class MainActivity extends NativeActivity
{
  static
  {
    // Нужно для ARM, для AM64 не нужно.
		System.loadLibrary("c++_shared");
  }

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
    try 
    {
      // Загрузка библиотеки, заданной в AndroidManifest.xml как параметр
      // android.app.lib_name; это нужно из-за того, что инициализация классов
      // Cpp, Contex и Activity должна происходить до запуска кода С++, которое
      // делает класс NativeActivity, но уже после загрузки главного .so файла.

      ActivityInfo ai = getPackageManager().getActivityInfo(
        getIntent().getComponent(), PackageManager.GET_META_DATA);
      String libName = ai.metaData.getString(META_DATA_LIB_NAME);
      System.loadLibrary(libName);
    } 
    catch (PackageManager.NameNotFoundException e) 
    {
      throw new RuntimeException("Error getting activity info", e);
    }

		new Activity(this);
    super.onCreate(savedInstanceState);
	}

  @Override
  public boolean dispatchKeyEvent(KeyEvent _Event)
  {
    int KeyAction = _Event.getAction();
    final int ACTION_KEY_PRESSED = 32;
    
    if(KeyAction == KeyEvent.ACTION_UP)
    {
      int KeyCode = _Event.getUnicodeChar();
      super.dispatchKeyEvent(new KeyEvent(ACTION_KEY_PRESSED, KeyCode));
    }
    else if(KeyAction == KeyEvent.ACTION_MULTIPLE)
    {
      if (_Event.getCharacters().length() == 1)
      {
        int KeyCode = _Event.getCharacters().charAt(0);
        return super.dispatchKeyEvent(new KeyEvent(ACTION_KEY_PRESSED, KeyCode));
      }
    }
    
    return super.dispatchKeyEvent(_Event);
  }

  @Override
  public void onUserLeaveHint() 
  {
    // Это для того, чтобы программа при потере фокуса всегда завершалась
    // полностью, с получением сообщения APP_CMD_DESTROY.
    finish();
  }
}
