package ru.ezhov.columnparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ParseImpl
        implements IParse {
    private static final Logger logger = Logger.getLogger(ParseImpl.class.getName());


    private String text;


    private int countColumn;


    private static String finalString;


    private static StringBuilder stringBuilder;


    private static List<String> listBase;


    private static List<String> listWork;


    private static String[] mass;


    private static Formatter formatter;


    private static int maxChar;


    private String separator;


    private static synchronized void pasteString() {
        for (String str : listWork) {
            stringBuilder.append(",%-");
            stringBuilder.append(maxChar);
            stringBuilder.append("s");
        }
        stringBuilder.append("\n");
        listWork.clear();
    }


    private static synchronized void getMaxChar() {
        logger.log(Level.INFO, "Run getMaxChar");
        for (String str : listBase) {
            if (str.trim().length() >= maxChar) {
                maxChar = str.length();
            }
        }
    }


    public String getText() {
        logger.log(Level.INFO, "Run getText");
        stringBuilder = new StringBuilder(300);
        formatter = new Formatter();
        mass = this.text.split(this.separator);
        listBase = Arrays.asList(mass);
        listWork = new ArrayList(this.countColumn);
        getMaxChar();
        int i = 0;
        int intWorkMass = 1;
        for (int size = listBase.size(); i < size; intWorkMass++) {
            String currentStr = ((String) listBase.get(i)).trim();
            listWork.add(currentStr);
            listBase.set(i, currentStr);
            if (intWorkMass % this.countColumn == 0) {
                pasteString();
                intWorkMass = 0;
            }
            i++;
        }


        pasteString();
        logger.log(Level.INFO, "Max char length: {0}", Integer.valueOf(maxChar));
        formatter = formatter.format(stringBuilder.toString(), (Object[]) mass);
        formatter.flush();
        finalString = formatter.toString();
        formatter.close();
        maxChar = 0;
        return " " + finalString.substring(1);
    }


    public void setText(String text) {
        this.text = text;
    }

    public void setCountColumn(int countColumn) {
        this.countColumn = countColumn;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
