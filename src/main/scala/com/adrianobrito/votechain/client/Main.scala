package com.adrianobrito.votechain.client

import java.util.Scanner

object Main {

  def main(args: Array[String]) {
    val scanner = new Scanner(System.in)
    while(true) {
      print("Type your message to server: ")
      val serverMessage = scanner.nextLine
      Client.send(serverMessage)

      println
    }
  }

}
