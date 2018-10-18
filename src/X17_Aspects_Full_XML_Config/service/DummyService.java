package X17_Aspects_Full_XML_Config.service;

import org.springframework.stereotype.Component;

@Component
public class DummyService {

    public void aMethodIWantToTest(String text) {
        System.out.println(text);
    }

    public void aMethodIWantToImprove(String text) {
        System.out.println(text);
    }

}
