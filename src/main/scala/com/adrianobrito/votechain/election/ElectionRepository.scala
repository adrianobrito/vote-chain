package com.adrianobrito.votechain.election

import java.io.{File, PrintWriter, Writer}

import com.adrianobrito.votechain.constants.ElectionsConstants

import scala.io.Source

class ElectionRepository(
  val fileWriter:Writer = new PrintWriter(ElectionsConstants.electionsFile)
) {

  def saveElectionFile(electionJson:String) = {
    fileWriter.write(electionJson)
    fileWriter.close()
  }

  def loadElectionFile : String = {
    Source.fromFile(ElectionsConstants.electionsFile).mkString
  }

  def electionFileExists : Boolean = {
    val electionFile = ElectionsConstants.electionsFile
    electionFile.exists()
  }

}
