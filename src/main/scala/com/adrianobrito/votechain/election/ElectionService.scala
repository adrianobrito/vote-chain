package com.adrianobrito.votechain

import com.adrianobrito.votechain.election.{Candidate, Election, ElectionRepository}
import com.adrianobrito.votechain.exception.{ElectionCreationException, ElectionLoadingException}
import play.api.libs.json._

class ElectionService(val electionRepository: ElectionRepository) {

  implicit val candidateFormat = Json.format[Candidate]
  implicit val electionFormat = Json.format[Election]

  def createElection(electionJson:String) : Election = {
    if(electionRepository.electionFileExists) {
      throw new ElectionCreationException("The election was already created")
    }

    electionRepository.saveElectionFile(electionJson)
    val election : Election = Json.fromJson[Election](Json.parse(electionJson)).get

    if(election.candidates.isEmpty) {
      throw new ElectionCreationException("The election has any candidates")
    }

    election
  }

  def loadElection : Election = {
    if(!electionRepository.electionFileExists) {
      throw new ElectionLoadingException("There's any election to load")
    }

    val loadedElectionFile : String = electionRepository.loadElectionFile
    Json.fromJson[Election](Json.parse(loadedElectionFile)).get
  }

}

object ElectionService extends ElectionService(new ElectionRepository)
