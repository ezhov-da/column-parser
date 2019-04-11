package ru.ezhov.column.parser.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Logger;


class DefaultParserEngine implements ParserEngine {
    private static final Logger LOG = Logger.getLogger(DefaultParserEngine.class.getName());

    private String createFormatterString(List<String> row, int maxCharLength) {
        StringBuilder stringBuilder = new StringBuilder(300);
        for (String str : row) {
            stringBuilder.append(",%-");
            stringBuilder.append(maxCharLength);
            stringBuilder.append("s ");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }


    private int getMaxCharLength(List<String> strings) {
        int maxCharLength = 0;
        for (String str : strings) {
            if (str.trim().length() >= maxCharLength) {
                maxCharLength = str.length();
            }
        }
        return maxCharLength;
    }

    @Override
    public String parse(String source, String separator, int columnCount) {
        //РАБОЧАЯ ПОЛНАЯ ХРЕНЬ НАПИСАННАЯ ПО МОЛОДОСТИ
        //ПЕРЕДЕЛАТЬ-БЫ...
        String[] mass = source.split(separator);
        List<String> listBase = Arrays.asList(mass);
        List<String> listWork = new ArrayList<>(columnCount);
        int maxCharLength = getMaxCharLength(listBase);
        int i = 0;
        int intWorkMass = 1;
        StringBuilder allFormatterStringBuilder = new StringBuilder();
        for (int size = listBase.size(); i < size; intWorkMass++) {
            String currentStr = listBase.get(i).trim();
            listWork.add(currentStr);
            listBase.set(i, currentStr);
            if (intWorkMass % columnCount == 0) {
                allFormatterStringBuilder.append(createFormatterString(listWork, maxCharLength));
                intWorkMass = 0;
                listWork.clear();
            }
            i++;
        }
        allFormatterStringBuilder.append(createFormatterString(listWork, maxCharLength));
        Formatter formatter = new Formatter();
        formatter = formatter.format(allFormatterStringBuilder.toString(), (Object[]) mass);
        formatter.flush();
        String finalString = formatter.toString();
        formatter.close();
        return " " + finalString.substring(1);
    }
}
