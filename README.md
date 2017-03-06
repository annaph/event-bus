### Description
Event bus demo application that demonstrates how can Akka be used to implement an event bus.

An event bus defines the following three abstract types:
  - `Event` - the type of all events published on the bus
  - `Classifier` - defines the classifier to be used in selecting subscribers for dispatching events
  - `Subscriber` - the type of subscribers allowed to register on that event bus
  
Using an event bus requires choosing and extending a classifier that will describe the event type and how to target subscribers. For this purpose Lookup Classification is used.

### Motivation
This demo application is created primarily for educational purpose and is not an original work. It is based on Event bus example from book:
  - *Learning Akka, Jason Goodwin, ISBN 978-1-78439-300-7*


### Licence
  **Free software**
  