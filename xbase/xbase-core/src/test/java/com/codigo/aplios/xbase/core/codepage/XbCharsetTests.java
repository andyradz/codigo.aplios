package com.codigo.aplios.xbase.core.codepage;

import java.nio.charset.Charset;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

//@DisplayName("Test1")
//@RunWith(JUnitPL)
public class XbCharsetTests {

    private static String MAZOVIA_CHARSET = "MAZOVIA";

    private static String ASCII_CHARSET = "US-ASCII";

    private static int MAZOVIA_CODEPAGE = 0x69;

    private static int UNKNOWN_CODEPAGE = 500;

    @Test
    public void sholudReturnMazoviaCharsetOfCodepage() {
        final Charset mazovia = XbCharset.ofCodepage(XbCharsetTests.MAZOVIA_CODEPAGE);

        assertThat(XbCharsetTests.MAZOVIA_CHARSET, CoreMatchers.equalTo(mazovia.name()));
    }

    @Test
    public void sholudReturnDefaultCharsetOfCodepage() {
        final Charset ascii = XbCharset.ofCodepage(XbCharsetTests.UNKNOWN_CODEPAGE);

        assertThat(XbCharsetTests.ASCII_CHARSET, CoreMatchers.equalTo(ascii.name()));
    }

    @Test
    public void sholudReturnMazoviaCodepageOfCharset() {
        final int mazovia = XbCharset.ofCharset(Charset.forName(XbCharsetTests.MAZOVIA_CHARSET));

        assertThat(XbCharsetTests.MAZOVIA_CODEPAGE, is(mazovia));
    }

}
