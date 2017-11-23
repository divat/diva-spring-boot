package com.diva.oauthexample;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OAuthRestControllerAspect {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(public * com.codemantra.cms.controller.*Controller.*(..))")
    public void logBeforeRestCall(JoinPoint jp) throws Throwable {
        log.info(":::::REST API call:::::" + jp);
    }
}
