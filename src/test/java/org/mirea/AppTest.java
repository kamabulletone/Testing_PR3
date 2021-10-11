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

import java.io.IOException;

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
}
