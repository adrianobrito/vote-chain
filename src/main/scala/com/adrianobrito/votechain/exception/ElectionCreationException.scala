package com.adrianobrito.votechain.exception

case class ElectionCreationException(message:String, cause:Throwable) extends Exception {

  def this(message:String) = this(message, null)

}