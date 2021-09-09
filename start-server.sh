mvn clean install
java -jar PublisherServer/target/PublisherServer-0.0.1-SNAPSHOT.jar > publisher_logs.log &
java -jar SubscribingServer/target/SubscribingServer-0.0.1-SNAPSHOT.jar > subscriber_logs.log &