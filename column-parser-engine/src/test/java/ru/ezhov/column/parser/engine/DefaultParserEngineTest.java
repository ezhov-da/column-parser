package ru.ezhov.column.parser.engine;

import org.junit.Test;

public class DefaultParserEngineTest {

    @Test
    public void parse() {
        String source = "1,12,14124,123123";
        DefaultParserEngine defaultParserEngine = new DefaultParserEngine();

        String parse = defaultParserEngine.parse(source, ",", 3);

        System.out.println(parse);
    }

}