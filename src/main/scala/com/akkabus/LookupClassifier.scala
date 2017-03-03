package com.akkabus

import akka.actor.ActorRef
import akka.event.EventBus
import akka.event.LookupClassification

case class EventBusMessage(topic: String, msg: String)

class LookupClassifier extends EventBus with LookupClassification {

  type Event = EventBusMessage
  type Classifier = String
  type Subscriber = ActorRef

  override protected def classify(event: Event): Classifier =
    event.topic

  override protected def publish(event: Event, subscriber: Subscriber): Unit =
    subscriber ! event.msg

  override protected def compareSubscribers(s1: Subscriber, s2: Subscriber): Int =
    s1 compareTo s2

  override protected def mapSize: Int = 128

}

case class LookupEventBus(subscriptions: Map[String, List[ActorRef]]) {

  private val _lookupClassifier = new LookupClassifier

  subscriptions foreach {
    case (topic, actors) =>
      actors foreach { _lookupClassifier subscribe (_, topic) }
  }

  def lookupClassifier: LookupClassifier = _lookupClassifier

}
