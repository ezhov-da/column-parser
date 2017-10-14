package ru.ezhov.columnparser;

public abstract interface IParse
{
  public abstract String getText();
  
  public abstract void setText(String paramString);
  
  public abstract void setCountColumn(int paramInt);
  
  public abstract void setSeparator(String paramString);
}
