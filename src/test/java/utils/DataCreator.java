package utils;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.extern.log4j.Log4j2;

import java.util.Locale;
@Log4j2
public class DataCreator {

    public String getFakeHeader() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        log.info("Generating text for header");
        String header = fakeValuesService.letterify("Header of entry about ????? ????? ? ?????");
        return header;
    }
    public String getFakeEntryText() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        log.info("Generating text for body");
        String text = fakeValuesService.letterify("Interesting text about ????? ????? ? ?????");
        return text;
    }
    public String getFakeTag() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        log.info("Generating text for tag name");
        String tag = fakeValuesService.letterify("?????");
        return tag;
    }
    public String getFakeTextToSearch() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        log.info("Generating text for search field");
        String searchText = fakeValuesService.letterify("travel");
        return searchText;
    }
}
