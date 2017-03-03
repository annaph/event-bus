package com.akkabus

import java.io.File

import scala.util.Failure
import scala.util.Success
import scala.util.Try

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

import akka.actor.ActorSystem
import akka.actor.Props

object EventBusMain extends App {

  val log: Logger = LoggerFactory.getLogger(getClass)

  val configFile: Try[String] = Try {
    getClass.getClassLoader.getResource("eventbus.conf").getFile
  }
  val config: Try[Config] = configFile map { f =>
    ConfigFactory.parseFile(new File(f))
  }

  config match {
    case Success(c) =>
      val system = ActorSystem("eventbus", c)
      val greetingActor = system.actorOf(Props.create(classOf[GreetingActor]))

      val eventBus = LookupEventBus(Map("greetings" -> List(greetingActor)))
      eventBus.lookupClassifier publish EventBusMessage("greetings", "Hello!")
    case Failure(e) =>
      log.error("Error starting actor system: " + e.getMessage)
  }

}
