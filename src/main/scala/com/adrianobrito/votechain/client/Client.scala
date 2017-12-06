package com.adrianobrito.votechain.client

import java.io.OutputStreamWriter
import java.net.{InetAddress, Socket}

import com.adrianobrito.votechain.constants.ServerConstant

object Client {

  def send(message:String) = {
    val inetAddress = InetAddress.getLocalHost
    val messageSocket = new Socket(inetAddress, ServerConstant.PORT)
    val messageWriter = new OutputStreamWriter(messageSocket.getOutputStream())

    println("Sending message to server app...")
    println(s"[MESSAGE] $message")
    messageWriter.write(message)
    messageWriter.close
    messageSocket.close
  }

}
