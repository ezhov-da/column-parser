package ru.ezhov.column.parser.engine;

public abstract class ParserEngineFactory {
    public static ParserEngine create() {
        return new DefaultParserEngine();
    }
}
