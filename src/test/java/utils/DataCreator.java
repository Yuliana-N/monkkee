package utils;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import java.util.Locale;

public class DataCreator {

    public String getFakeHeader() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String header = fakeValuesService.letterify("Header of entry about ????? ????? ? ?????");
        return header;
    }
    public String getFakeEntryText() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String text = fakeValuesService.letterify("Interesting text about ????? ????? ? ?????");
        return text;
    }
    public String getFakeTag() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String tag = fakeValuesService.letterify("?????");
        return tag;
    }
    public String getFakeTextToSearch() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String tag = fakeValuesService.letterify("travel");
        return tag;
    }
}
