package X15_Aspects_XML_Config.service;

import org.springframework.stereotype.Component;

@Component
public class DummyService {

    public void aMethodIWantToTest(String text) {
        System.out.println(text);
    }

}
