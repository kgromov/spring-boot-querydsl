package com.spring.templates.services;

import com.spring.templates.services.profiling.Profiling;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Aspect
@Component
public class TimeLoggerAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeLoggerAspect.class);

    private final Map<TimeUnit, String> timeunits = new EnumMap<>(TimeUnit.class);

    @PostConstruct
    public void init () {
        timeunits.put(TimeUnit.SECONDS, "s");
        timeunits.put(TimeUnit.MILLISECONDS, "ms");
        timeunits.put(TimeUnit.MICROSECONDS, "μs");
        timeunits.put(TimeUnit.NANOSECONDS, "ns");
    }

    @Around("@annotation(com.spring.templates.services.profiling.Profiling)")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(Profiling.class)) {
            Profiling profiling = method.getAnnotation(Profiling.class);
            TimeUnit timeUnit = profiling.timeunit();
            LogLevel logLevel = profiling.loglevel();
            long startTime = System.nanoTime();
            Object result = joinPoint.proceed(joinPoint.getArgs());
            long endTime = getEndTimeConvertedToTimeUnit(startTime, timeUnit);
            String message = String.format("Method: '%s' execution time took %d %s",
                joinPoint.getSignature().toLongString(), endTime, timeunits.getOrDefault(timeUnit, "ms"));
            log(logLevel, () -> message);
            return result;
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }

    @Around("within(com.spring.templates.repositories.custom.QueryDslRepository+) ||" +
            "within(org.springframework.data.repository.CrudRepository+)")
    public Object profileRepositories(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        Object result = joinPoint.proceed(joinPoint.getArgs());
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long endTime = getEndTimeConvertedToTimeUnit(startTime, timeUnit);
        String message = String.format("Method: '%s' execution time took %d %s",
                joinPoint.getSignature().toLongString(), endTime, timeunits.get(timeUnit));
        log(LogLevel.DEBUG, () -> message);
        return result;
    }

    private static Optional<Profiling> getProfilingFromJointPoint(ProceedingJoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getSignature().getDeclaringType();
        Profiling profiling = clazz.getAnnotation(Profiling.class);
        if (profiling == null) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            profiling = method.getAnnotation(Profiling.class);
        }
        return Optional.ofNullable(profiling);
    }

    private static long getEndTimeConvertedToTimeUnit(long startTime, TimeUnit timeUnit) {
        return timeUnit.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
    }

    private static void log(LogLevel logLevel, Supplier<String> message) {
        switch (logLevel) {
            case ERROR:
                LOGGER.error(message.get());
                break;
            case WARN:
                LOGGER.warn(message.get());
                break;
            case INFO:
                LOGGER.info(message.get());
                break;
            case TRACE:
                LOGGER.trace(message.get());
                break;
            default:
                LOGGER.debug(message.get());
        }
    }
}
