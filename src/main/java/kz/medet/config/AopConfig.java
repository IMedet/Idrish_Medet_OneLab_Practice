package kz.medet.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("kz.medet")
@EnableAspectJAutoProxy
public class AopConfig {
}
