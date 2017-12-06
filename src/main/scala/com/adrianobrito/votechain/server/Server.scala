package com.adrianobrito.votechain.server

import java.net.ServerSocket
import com.adrianobrito.votechain.constants.ServerConstant
import scala.io.Source
import scala.util.control.Breaks._

object Server {

  def execute = {
    val server:ServerSocket = new ServerSocket(ServerConstant.PORT);

    breakable {
      while(true) {
        println("Waiting for client request...")
        val socket  = server.accept
        val message = Source
          .fromInputStream(socket.getInputStream())
          .mkString

        socket.close()

        if(message == "exit") break
        println(message)
      }
    }

    server.close()
  }

}