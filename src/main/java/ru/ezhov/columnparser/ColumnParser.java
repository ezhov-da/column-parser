package ru.ezhov.columnparser;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;

public class ColumnParser
  implements HotKeyListener
{
  private static final BasicFrame basicFrame = new BasicFrame();
  
  public void onHotKey(HotKey hotkey)
  {
    basicFrame.setVisible(true);
  }
}
