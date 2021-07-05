Proof of concept using the [Java 14 flight recorder event streaming](http://hirt.se/blog/?p=1239) to expose JMX MBeans
from the data.

Call http://localhost:8080/open?host=www.google.com some number 
of times, then check the MBean browser to see the number of TLS handshakes performed.
Look under org.johngregg:type=TLSHandshake,name=*.

