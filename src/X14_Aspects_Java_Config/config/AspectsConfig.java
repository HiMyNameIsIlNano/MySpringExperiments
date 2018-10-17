package X14_Aspects_Java_Config.config;

import X14_Aspects_Java_Config.service.DummyService;
import X14_Aspects_Java_Config.aspect.MyAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
/*
@ComponentScan: can be left here if the aspect is annotated with @Component. If one goes for a custom Configuration,
then the Advice Bean needs to be instantiated by hand.
**/
public class AspectsConfig {

    @Bean
    public DummyService myTestBean() {
        return new DummyService();
    }

    @Bean
    public MyAspect myAspect() {
        return new MyAspect();
    }

}
