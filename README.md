# clj-jms-activemq-fifo-reader

A simple tool that reads data from a named pipe (fifo) and sends it to a JMS topic.

## Quick Usage Cheat Sheet

Create named pipes for reading and writing:

    mkfifo test.reader.fifo
    mkfifo test.writer.fifo


Start a JMS broker.
In our example, we use the [clj-jms-activemq-toolkit](https://github.com/fg-netzwerksicherheit/clj-jms-activemq-toolkit) helper that starts a pre-configured embedded ActiveMQ broker:

    java -jar clj-jms-activemq-toolkit-1.99.1-standalone.jar

You can download a pre-built Jar file from: https://github.com/fg-netzwerksicherheit/clj-jms-activemq-toolkit/raw/master/dist/clj-jms-activemq-toolkit-1.99.1-standalone.jar


Start the fifo reader:

    java -jar clj-jms-activemq-fifo-reader-0.1.0-SNAPSHOT-standalone.jar

You can download a pre-built Jar file from: https://github.com/fg-netzwerksicherheit/clj-jms-activemq-fifo-reader/raw/master/dist/clj-jms-activemq-fifo-reader-0.1.0-SNAPSHOT-standalone.jar


Start the fifo writer:

    java -jar clj-jms-activemq-fifo-writer-0.1.0-SNAPSHOT-standalone.jar

You can download a pre-built Jar file from: https://github.com/fg-netzwerksicherheit/clj-jms-activemq-fifo-writer/raw/master/dist/clj-jms-activemq-fifo-writer-0.1.0-SNAPSHOT-standalone.jar


Read from the fifo:

    cat test.writer.fifo


Write into the fifo:

    echo "abcd" > test.reader.fifo

## License

Copyright © 2015 Frankfurt University of Applied Sciences

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
