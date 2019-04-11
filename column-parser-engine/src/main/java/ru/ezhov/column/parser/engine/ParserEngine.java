package ru.ezhov.column.parser.engine;

public interface ParserEngine {
    String parse(String source, String separator, int columnCount);
}
