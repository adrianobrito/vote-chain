package com.adrianobrito.votechain.exception

case class ElectionLoadingException(message:String, cause:Throwable) extends Exception {

  def this(message:String) = this(message, null)

}
