package com.adrianobrito.votechain.actors

import akka.actor.{ActorSystem, Props, Actor}

import scala.io.StdIn

class PrintMyRefActor extends Actor {

  override def receive : Receive = {
    case "listen" =>
      val secondRef = context.actorOf(Props.empty, "second-actor")
      println(s"Second: $secondRef")
  }

}

object ActorHierarchyExperiments extends App{
  val system = ActorSystem("test")
  val firstRef = system.actorOf(Props[PrintMyRefActor], "first-actor")

  firstRef ! "listen"

  try StdIn.readLine()
  finally system.terminate()
}
