package com.akkabus

import akka.actor.Actor
import akka.actor.ActorLogging

class GreetingActor extends Actor with ActorLogging {

  override def receive = {
    case x =>
      log.debug("guess we received a greeting! msg: " + x)
  }

}
