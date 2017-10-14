package ru.ezhov.columnparser;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ParseImplTest {
    @Test
    public void getText() throws Exception {
        IParse parse = new ParseImpl();
        parse.setCountColumn(2);
        parse.setSeparator(",");
        parse.setText("1,1");

        String s = parse.getText();
        assertTrue(" 1,1\n\n".equals(s));
    }

}