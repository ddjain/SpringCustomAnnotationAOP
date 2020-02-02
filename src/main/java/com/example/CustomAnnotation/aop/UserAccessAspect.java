package com.example.CustomAnnotation.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.example.CustomAnnotation.annotation.CustomAnnotation;

@Aspect
@Component
public class UserAccessAspect {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("@annotation(com.example.demo.annotation.MyAnnotation)")
	public Object trackTime(ProceedingJoinPoint jointPoint) throws Throwable {
		boolean debug=false;
		long stratTime=System.currentTimeMillis();
		Method m = MethodSignature.class.cast(jointPoint.getSignature()).getMethod();
		for (Annotation a : m.getDeclaredAnnotations()) {
			if (a instanceof CustomAnnotation) {
				CustomAnnotation annot = (CustomAnnotation) a;
				debug=annot.debug();
			}
		}
		Object obj=jointPoint.proceed();
		long endTime=System.currentTimeMillis();
		if(debug) {
			logger.debug("Method name"+jointPoint.getSignature()+" time taken to execute : "+(endTime-stratTime));
		} else {
			logger.info("Method name"+jointPoint.getSignature()+" time taken to execute : "+(endTime-stratTime));
		}
		return obj;
	}

}
