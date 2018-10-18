package X16_Aspects_With_Params_Java_Config.service;

import X16_Aspects_With_Params_Java_Config.aspect.MessageTypeEnum;

public class DummyService {

    public void aMethodIWantToTest(MessageTypeEnum messageType) {
        System.out.println("I am a " + messageType + " message!");
    }

}
