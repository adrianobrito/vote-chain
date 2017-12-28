package com.adrianobrito.votechain.election

import java.io.{File, PrintWriter}

import com.adrianobrito.votechain.constants.ElectionsConstants

import scala.io.Source

class ElectionRepository {

  def saveElectionFile(electionJson:String) = {
    val writer = new PrintWriter(new File(ElectionsConstants.ELETIONS_FILE_PATH))
    writer.write(electionJson)
    writer.close()
  }

  def loadElectionFile : String = {
    Source.fromFile(ElectionsConstants.ELETIONS_FILE_PATH).mkString
  }

}
