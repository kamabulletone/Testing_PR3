package org.mirea;


import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AppTest {

    @Test
    public void testDocumentContent() throws IOException {
        String testingHTML = "<div class=\"tablica_stroka\"> \n" +
                "          <div class=\"holst_gener\" id=\"vov\">\n" +
                "           <div class=\"blok_otvet\" id=\"bov\" style=\"font-size:60px;\">\n" +
                "            Крахмал\n" +
                "           </div>\n" +
                "          </div> \n" +
                "         </div> ";

        Document doc = Jsoup.parse(testingHTML);
        Connection connection = Mockito.mock(Connection.class);
        Mockito.when(connection.get()).thenReturn(doc);

        Assertions.assertNotNull(connection.get());
        MatcherAssert.assertThat(connection.get(), Matchers.equalTo(doc));

    }

    @Test
    public void testWordSearch() {
        String wordToFind = "Крахмал";
        Element element = Mockito.mock(Element.class);

        Mockito.when(element.text()).thenReturn(wordToFind);

        Assertions.assertNotNull(element.text());
        MatcherAssert.assertThat(element.text(), Matchers.equalTo(wordToFind));

    }

    @Test
    public void testChooseWordChar() {
        String testWord = "питсеед";
        ArrayList<String> maskedWord = new ArrayList<>(Arrays.asList("*", "*", "*", "*", "*", "*", "*"));
        ArrayList<String> testMaskedWordMaskWord = App.initMask(testWord.length());

        MatcherAssert.assertThat(maskedWord.size(), Matchers.equalTo(testMaskedWordMaskWord.size()));

        String printedChar = "е";
        ByteArrayInputStream in = new ByteArrayInputStream(printedChar.getBytes());
        System.setIn(in);
        List<Object> res = App.chooseWordChar(testWord, maskedWord);

        MatcherAssert.assertThat(res, Matchers.notNullValue());
        MatcherAssert.assertThat(res, Matchers.hasSize(2));

        MatcherAssert.assertThat(res.get(0), Matchers.isA(Boolean.class));
        MatcherAssert.assertThat(res.get(1), Matchers.isA(ArrayList.class));

        MatcherAssert.assertThat(res.get(0), Matchers.notNullValue());
        MatcherAssert.assertThat(res.get(1), Matchers.notNullValue());

        MatcherAssert.assertThat(((ArrayList<String>) res.get(1)), Matchers.hasSize(maskedWord.size()));
        MatcherAssert.assertThat(res.get(1), Matchers.equalTo(new ArrayList<>(Arrays.asList("*", "*", "*", "*", "е", "е", "*"))));

    }
}
