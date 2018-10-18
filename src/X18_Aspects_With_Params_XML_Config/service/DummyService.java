package X18_Aspects_With_Params_XML_Config.service;

import X18_Aspects_With_Params_XML_Config.aspect.MessageTypeEnum;

public class DummyService {

    public void aMethodIWantToTest(MessageTypeEnum messageType) {
        System.out.println("I am a " + messageType + " message!");
    }

}
