package com.akkabus

import scala.concurrent.duration.DurationInt

import org.scalatest.FunSpec

import akka.actor.ActorSystem
import akka.testkit.TestProbe

class EventBusSpec extends FunSpec {

  implicit val system = ActorSystem("eventbus-test")

  describe("Given an Event Bus") {
    val greetingActor = TestProbe("greetingActor")
    val topic = "greetings"
    val eventBus = LookupEventBus(Map(topic -> List(greetingActor.ref)))

    describe("When 'greeting' topic message is published") {
      val msg = "Hello"
      eventBus.lookupClassifier publish EventBusMessage(topic, msg)

      it("(Greeting actor) should receive that message") {
        greetingActor expectMsg (7 seconds, msg)
      }
    }
  }

  describe("Given an Event Bus") {
    val greetingActor = TestProbe("greetingActor")
    val topic = "greetings"
    val eventBus = LookupEventBus(Map(topic -> List(greetingActor.ref)))

    describe("When 'time' topic message is published") {
      val msg = System.currentTimeMillis.toString
      eventBus.lookupClassifier publish EventBusMessage("time", msg)

      it("(Greeting actor) should not receive that message") {
        greetingActor expectNoMsg (7 seconds)
      }
    }
  }

}
