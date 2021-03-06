package de.undercouch.citeproc.helper;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link SmartQuotes}
 * @author MicheL Kraemer
 */
public class SmartQuotesTest {
    /**
     * Test the {@link SmartQuotes#apply(String)} method
     */
    @Test
    public void apply() {
        SmartQuotes sq = new SmartQuotes();

        // tests from smartquotes.js (https://smartquotes.js.org/)
        // written by Kelly Martin released under the MIT license
        assertEquals("\u201ctest\u201d", sq.apply("\"test\""));
        assertEquals("the\u2014 \u201ctest\u201d", sq.apply("the\u2014 \"test\""));
        assertEquals("\u2018test\u2019", sq.apply("'test'"));
        assertEquals("ma\u2019am", sq.apply("ma'am"));
        assertEquals("\u2019em", sq.apply("'em"));
        assertEquals("Marshiness of \u2019Ammercloth\u2019s",
                sq.apply("Marshiness of 'Ammercloth's"));
        assertEquals("\u201995", sq.apply("'95"));
        assertEquals("\u2034", sq.apply("'''"));
        assertEquals("\u2033", sq.apply("''"));
        assertEquals("\u201cBetter than a 6\u20325\u2033 whale.\u201d",
                sq.apply("\"Better than a 6'5\" whale.\""));
        assertEquals("\u201cIt\u2019s my \u2018#1\u2019 choice!\u201d - 12\u2033 Foam Finger from \u201993",
                sq.apply("\"It's my '#1' choice!\" - 12\" Foam Finger from '93"));
        assertEquals("\u201cSay \u2018what?\u2019\u201d says a Mill\u2019s Pet Barn employee.",
                sq.apply("\"Say 'what?'\" says a Mill's Pet Barn employee."));
        assertEquals("\u201cQuote?\u201d: Description",
                sq.apply("\"Quote?\": Description"));
        assertEquals("\u2018Quo Te?\u2019: Description",
                sq.apply("'Quo Te?': Description"));
        assertEquals("\u201cDe Poesjes van Kevin?\u201d: Something, something",
                sq.apply("\"De Poesjes van Kevin?\": Something, something"));
        assertEquals("And then she blurted, \u201cI thought you said, \u2018I don\u2019t like \u201980s music\u2019?\u201d",
                sq.apply("And then she blurted, \"I thought you said, 'I don't like '80s music'?\""));

        // further tests
        assertEquals("That\u2019s and it\u2019s and couldn\u2019t.",
                sq.apply("That's and it's and couldn't."));
        assertEquals("\u201C\u2018That\u2019s so cool,\u2019 he said.\u201D",
                sq.apply("\"'That's so cool,' he said.\""));
        assertEquals("\u201C\u2018That\u2019s so \u201Ccool\u201D,\u2019 he said.\u201D",
                sq.apply("\"'That's so \"cool\",' he said.\""));

        // tests from https://medium.design/quotation-marks-c8993b54417c
        assertEquals("12½\u2033 record, 5\u203210⅝\u2033 height",
                sq.apply("12½\" record, 5'10⅝\" height"));
        assertEquals("iPad 3\u2019s battery life",
                sq.apply("iPad 3's battery life"));
        assertEquals("Book \u2019em, Danno. Rock\u2019n\u2019roll. \u2019Cause \u2019twas the season.",
                sq.apply("Book 'em, Danno. Rock'n'roll. 'Cause 'twas the season."));
        assertEquals("This is \u2018empathy\u2019.",
                sq.apply("This is 'empathy'."));
        assertEquals("Book \u2019em",
                sq.apply("Book 'em"));
        assertEquals("\u201985 was a good year. The entire \u201980s were",
                sq.apply("'85 was a good year. The entire '80s were"));
    }

    /**
     * Characters with accents as well as umlauts
     */
    @Test
    public void accented() {
        SmartQuotes sq = new SmartQuotes();

        assertEquals("\u201CÁguila\u201D", sq.apply("\"Águila\""));
        assertEquals("\u201Cáguila\u201D", sq.apply("\"águila\""));
        assertEquals("\u201CAguila\u201D", sq.apply("\"Aguila\""));
        assertEquals("\u201Caguila\u201D", sq.apply("\"aguila\""));

        assertEquals("\u201CÄquator\u201D", sq.apply("\"Äquator\""));
        assertEquals("\u201Cärgerlich\u201D", sq.apply("\"ärgerlich\""));

        assertEquals("\u201CHä\u201D", sq.apply("\"Hä\""));
    }

    /**
     * Similar to {@link #apply()} but with German quotation marks
     */
    @Test
    public void german() {
        SmartQuotes sq = new SmartQuotes("\u201a", "\u2018", "\u201e", "\u201c", Locale.GERMAN);

        assertEquals("\u201etest\u201c", sq.apply("\"test\""));
        assertEquals("Der\u2014 \u201eTest\u201c", sq.apply("Der\u2014 \"Test\""));
        assertEquals("\u201atest\u2018", sq.apply("'test'"));
        assertEquals("Er kann\u2019s", sq.apply("Er kann's"));
        assertEquals("Marshiness von \u2018Ammercloth\u2019s",
                sq.apply("Marshiness von 'Ammercloth's"));
        assertEquals("\u201995", sq.apply("'95"));
        assertEquals("\u2034", sq.apply("'''"));
        assertEquals("\u2033", sq.apply("''"));
        assertEquals("\u201eBesser als ein 6\u20325\u2033 Wal.\u201c",
                sq.apply("\"Besser als ein 6'5\" Wal.\""));
        assertEquals("\u201eIch hab\u2019s auf \u201a#1\u2018 gesetzt!\u201c - Der 12\u2033 Schaumstofffinger von \u201993",
                sq.apply("\"Ich hab's auf '#1' gesetzt!\" - Der 12\" Schaumstofffinger von '93"));
        assertEquals("\u201eSag \u201awas?\u2018\u201c sagt der Mitarbeiter der Mill\u2019s Pet Barn.",
                sq.apply("\"Sag 'was?'\" sagt der Mitarbeiter der Mill's Pet Barn."));
        assertEquals("\u201eQuote?\u201c: Beschreibung",
                sq.apply("\"Quote?\": Beschreibung"));
        assertEquals("\u201aQuo Te?\u2018: Beschreibung",
                sq.apply("'Quo Te?': Beschreibung"));
        assertEquals("\u201eDe Poesjes van Kevin?\u201c: Irgendwas, irgendwas",
                sq.apply("\"De Poesjes van Kevin?\": Irgendwas, irgendwas"));
        assertEquals("Und dann platze es aus ihr heraus: \u201eIch dachte, du sagtest: \u201aIch mag keine 80er-Musik\u2018?\u201c",
                sq.apply("Und dann platze es aus ihr heraus: \"Ich dachte, du sagtest: 'Ich mag keine 80er-Musik'?\""));

        assertEquals("\u201985 war ein gutes Jahr. Die ganzen 80er waren so.",
                sq.apply("'85 war ein gutes Jahr. Die ganzen 80er waren so."));
    }

    /**
     * Test that opening quotation mark at the beginning of the string is
     * converted correctly, even if it does not follow a letter or a number.
     */
    @Test
    public void openQuoteNoPrime() {
        SmartQuotes sq = new SmartQuotes();
        assertEquals("\u201cTest", sq.apply("\"Test"));
        assertEquals("\u201c-Test", sq.apply("\"-Test"));
        assertEquals("\u2033-Test", sq.apply("\u2033-Test"));
    }
}
