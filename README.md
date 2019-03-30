To run the individual programs from Exchange Examples. Create the below environment variable
RMQ_CLASSPATH=.;D:\RabbitMq\jars\amqp-client-5.5.1.jar;D:\RabbitMq\jars\slf4j-simple-1.7.25.jar;D:\RabbitMq\jars\slf4j-api-1.7.25.jar

for compiling:
javac -cp %RMQ_CLASSPATH% Recv.java

for Executing:
java -cp %RMQ_CLASSPATH% Recv
