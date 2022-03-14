## Second Spring Boot Project (featuring Reactive Spring Boot, Reactive Kafka and Mongo DB)
First forays into reactive programming, Kafka and NoSQL, this project is based off on this tutorial [here](https://www.youtube.com/watch?v=0ryrlJfSgfc).

As with the first project, it's not a straight adoption but adds a bit of more data. Also, this project now is a Kafka consumer - whenever a new guest lecturer gets registered with [my first Spring Boot project](https://github.com/ak-73/Spring-Boot-Tutorial-1), that web service will post a message, requesting this reservation service here to book suitable lodgings for the guest lecturer. (You can also do manually via the new `/book_room` endpoint (see below).)

#### In order to facilitate communication with the service of springboottutorial-1, you will therefore need Zookeeper running on Port 2181 and Apache Kafka on Port 9092!

### REST interface
Following there is an overview over the (very simple) functionality provided by the project's REST interface. All below functionality uses JSON as exchange data format: 
- `GET: localhost:8081/reservations` - retrieves a list of all reservations
- `GET: localhost:8081/reservation/{id}` - retrieves the reservation matching the passed `id` path variable
- `POST: localhost:8081/book_room` - creates a reservation for the person passed in via JSON. Required fields are title, firstName, lastName, checkInDate and checkOutDate.

(Note that we're using port 8081 here since the first project already used port 8080 and we'd like to keep things cleanly sepearated here.)


## License
The project is available under the **[EPL 2.0 License]**


[EPL 2.0 License]: https://www.eclipse.org/legal/epl-2.0/