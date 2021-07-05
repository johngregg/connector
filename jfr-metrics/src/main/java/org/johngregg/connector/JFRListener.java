package org.johngregg.connector;

import jdk.jfr.consumer.RecordingStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JFRListener {

//    @EventListener()
//    public void listen(ApplicationStartedEvent event) throws IOException, ParseException {
//        System.out.println("XXX app started XXX");
//
////        var c = Configuration.getConfiguration("default");
////        try (var rs = new RecordingStream(c)) {
////            rs.enable("jdk.CPULoad").withPeriod(Duration.ofSeconds(1));
////            rs.enable("jdk.JavaMonitorEnter").withThreshold(Duration.ofMillis(10));
////            rs.onEvent("jdk.CPULoad", e -> {
////                System.out.println(e.getFloat("machineTotal"));
////            });
////            rs.onEvent("jdk.JavaMonitorEnter", e -> {
////                System.out.println(e.getClass("monitorClass"));
////            });
////            rs.startAsync();
////        }
//        var c = Configuration.getConfiguration("default");
//        try (var rs = new RecordingStream(c)) {
//            rs.onEvent(e -> System.out.println(e.getEventType()));
//            rs.start();
//        }
//    }

    @Bean(destroyMethod = "close")
    public RecordingStream jfr() {

//        var c = Configuration.getConfiguration("default");
//        try (var rs = new RecordingStream(c)) {
//            rs.enable("jdk.CPULoad").withPeriod(Duration.ofSeconds(1));
//            rs.enable("jdk.JavaMonitorEnter").withThreshold(Duration.ofMillis(10));
//            rs.onEvent("jdk.CPULoad", e -> {
//                System.out.println(e.getFloat("machineTotal"));
//            });
//            rs.onEvent("jdk.JavaMonitorEnter", e -> {
//                System.out.println(e.getClass("monitorClass"));
//            });
//            rs.startAsync();
//        }
//        var c = Configuration.getConfiguration("default");
        var rs = new RecordingStream();
        String eventName = "jdk.TLSHandshake";
        rs.enable(eventName);
        Map<String, TLSHandshake> tlsHandshakes = new HashMap<>();
        rs.onEvent(eventName, e -> {

//            tlsHandshakes.getOrDefault()
            String peerHost = e.getString("peerHost");
            TLSHandshake tlsHandshake = tlsHandshakes.get(peerHost);
            if (tlsHandshake == null) {
                tlsHandshake = new TLSHandshake(peerHost);
                tlsHandshakes.put(peerHost, tlsHandshake);
                MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
                try {
                    mBeanServer.registerMBean(tlsHandshake, new ObjectName("org.johngregg:type=TLSHandshake,name=" + peerHost));
                } catch (InstanceAlreadyExistsException instanceAlreadyExistsException) {
                    instanceAlreadyExistsException.printStackTrace();
                } catch (MBeanRegistrationException mBeanRegistrationException) {
                    mBeanRegistrationException.printStackTrace();
                } catch (NotCompliantMBeanException notCompliantMBeanException) {
                    notCompliantMBeanException.printStackTrace();
                } catch (MalformedObjectNameException malformedObjectNameException) {
                    malformedObjectNameException.printStackTrace();
                }
            }
            tlsHandshake.increment();
//            List<ValueDescriptor> fields = e.getFields();
//            for (ValueDescriptor field : fields) {
//                String name = field.getName();
//                if ("peerHost".equals(name)) {
//                    TLSHandshake tlsHandshake = tlsHandshakes.getOrDefault(name, new TLSHandshake(peerHost));
//                    System.out.println(tlsHandshake.getPeer());
//                }
//            }
//            System.out.println(e.getEventType().getName());
        });
        rs.startAsync();

        return rs;
    }
}
