package ru.ezhov.columnparser;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;




public class BasicFrame
  extends JDialog
  implements IParse
{
  private static final Logger logger = Logger.getLogger(ColumnParser.class.getName());
  private static final IParse PARSE = new ParseImpl();
  private static Clipboard clipboard;
  private final JLabel labelSeparator = new JLabel("Введите разделитель (, - по-умолчанию): ");
  private final JTextField textFieldSeparator = new JTextField(5);
  private final JLabel labelCountColumn = new JLabel("Введите кол-во столбцов (2 - по-умолчанию): ");
  private final JTextField textFieldCountColumn = new JTextField(5);
  
  public BasicFrame()
  {
    setMap();
    init();
  }
  
  private void init() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, 3));
    panel.add(this.labelSeparator);
    panel.add(this.textFieldSeparator);
    panel.add(this.labelCountColumn);
    panel.add(this.textFieldCountColumn);
    add(panel);
    panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(3, 3, 3, 3)));
    



    setUndecorated(true);
    setLocationRelativeTo(null);
    pack();
  }
  


  private void setMap()
  {
    InputMap inputMap = getRootPane().getInputMap(2);
    inputMap.put(KeyStroke.getKeyStroke(27, 0), "esc");
    inputMap.put(KeyStroke.getKeyStroke(10, 0), "enter");
    ActionMap actionMap = getRootPane().getActionMap();
    actionMap.put("esc", new AbstractAction()
    {
      public void actionPerformed(ActionEvent e) {
        BasicFrame.this.setVisible(false);
      }
    });
    actionMap.put("enter", new AbstractAction()
    {
      public void actionPerformed(ActionEvent e) {
        BasicFrame.this.parse();
        BasicFrame.this.setVisible(false);
      }
    });
  }
  




  public int getCountColumn()
  {
    String string = this.textFieldCountColumn.getText();
    if ("".equals(string))
      return 2;
    int col;
    try {
      col = Integer.parseInt(string);
    } catch (NumberFormatException ex) {
      return 2;
    }
    return col;
  }
  




  public String getSeparator()
  {
    String string = this.textFieldSeparator.getText();
    if ("".equals(string)) {
      return ",";
    }
    return string;
  }
  

  public String getText()
  {
    if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
      try {
        return (String)clipboard.getData(DataFlavor.stringFlavor);
      } catch (UnsupportedFlavorException ex) {
        logger.log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
        logger.log(Level.SEVERE, null, ex);
      }
    }
    return "";
  }
  
  public void setText(String text)
  {
    clipboard.setContents(new StringSelection(text), null);
  }
  
  public void setCountColumn(int countColumn)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public void setSeparator(String separator)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public void parse() {
    logger.log(Level.INFO, "Run parse");
    clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    String string = getText();
    if ("".equals(string)) {
      return;
    }
    PARSE.setCountColumn(getCountColumn());
    PARSE.setText(string);
    PARSE.setSeparator(getSeparator());
    string = PARSE.getText();
    logger.log(Level.INFO, "Set result to clipboard");
    setText(string);
  }
}
