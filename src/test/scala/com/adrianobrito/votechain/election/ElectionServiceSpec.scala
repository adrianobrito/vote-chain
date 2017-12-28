package com.adrianobrito.votechain.election

import com.adrianobrito.votechain.election.fixtures.ElectionServiceFixtures
import com.adrianobrito.votechain.exception.{ElectionCreationException, ElectionLoadingException}
import com.adrianobrito.votechain.{ElectionService, UnitSpec}

class ElectionServiceSpec extends UnitSpec {

  val electionRepository : ElectionRepository = mock[ElectionRepository]
  val electionService : ElectionService = new ElectionService(electionRepository)

  "ElectionService" should "create an Election with JSON" in {
    val electionJson : String = ElectionServiceFixtures.electionJson
    val election : Election = electionService.createElection(electionJson)

    election.candidates should not be empty
  }

  it should "throws ElectionCreationException if there's any candidate" in {
    val electionWithAnyCandidatesJson : String = ElectionServiceFixtures.electionWithAnyCandidatesJson

    assertThrows[ElectionCreationException] {
      ElectionService.createElection(electionWithAnyCandidatesJson)
    }
  }

  it should "throws ElectionCreationException if create a election that was already created" in {
    val electionJson : String = ElectionServiceFixtures.electionJson
    ElectionService.createElection(electionJson)

    assertThrows[ElectionCreationException] {
      ElectionService.createElection(electionJson)
    }
  }

  it should "load election" in {
    val electionJson : String = ElectionServiceFixtures.electionJson
    ElectionService.createElection(electionJson)

    val election : Election = ElectionService.loadElection

    election.candidates should not be empty
  }

  it should "throw ElectionLoadingException if loads election without create before" in {
    assertThrows[ElectionLoadingException] {
      ElectionService.loadElection
    }
  }

}
