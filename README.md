# Micrometer commons-dbcp2 metrics

Tiny example project to illustrate questions regarding `commons-dbcp2` metrics.

Simply run `mvn verify` and examine the logs:

```
SEVERE: can not set name tag
javax.management.AttributeNotFoundException: No such attribute: FactoryType
	at java.management/com.sun.jmx.mbeanserver.PerInterface.getAttribute(PerInterface.java:81)
	at java.management/com.sun.jmx.mbeanserver.MBeanSupport.getAttribute(MBeanSupport.java:206)
	at java.management/com.sun.jmx.interceptor.DefaultMBeanServerInterceptor.getAttribute(DefaultMBeanServerInterceptor.java:641)
	at java.management/com.sun.jmx.mbeanserver.JmxMBeanServer.getAttribute(JmxMBeanServer.java:678)
	at io.micrometer.core.instrument.binder.commonspool2.CommonsObjectPool2Metrics.nameTag(CommonsObjectPool2Metrics.java:141)
	at io.micrometer.core.instrument.binder.commonspool2.CommonsObjectPool2Metrics.lambda$registerNotificationListener$1(CommonsObjectPool2Metrics.java:193)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:834)

May 07, 2021 1:16:39 PM io.micrometer.core.instrument.binder.commonspool2.CommonsObjectPool2Metrics lambda$registerNotificationListener$1
SEVERE: can not set name tag
javax.management.InstanceNotFoundException: org.apache.commons.pool2:name=dbcp,type=GenericObjectPool,connectionpool=connections,connection=0
	at java.management/com.sun.jmx.interceptor.DefaultMBeanServerInterceptor.getMBean(DefaultMBeanServerInterceptor.java:1083)
	at java.management/com.sun.jmx.interceptor.DefaultMBeanServerInterceptor.getAttribute(DefaultMBeanServerInterceptor.java:637)
	at java.management/com.sun.jmx.mbeanserver.JmxMBeanServer.getAttribute(JmxMBeanServer.java:678)
	at io.micrometer.core.instrument.binder.commonspool2.CommonsObjectPool2Metrics.nameTag(CommonsObjectPool2Metrics.java:141)
	at io.micrometer.core.instrument.binder.commonspool2.CommonsObjectPool2Metrics.lambda$registerNotificationListener$1(CommonsObjectPool2Metrics.java:193)
```