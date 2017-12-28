package com.adrianobrito.votechain.election

import com.adrianobrito.votechain.election.fixtures.ElectionFixtures
import com.adrianobrito.votechain.exception.{ElectionCreationException, ElectionLoadingException}
import com.adrianobrito.votechain.{ElectionService, UnitSpec}

class ElectionServiceSpec extends UnitSpec {

  val mockedElectionRepository : ElectionRepository = mock[ElectionRepository]
  val electionService : ElectionService = new ElectionService(mockedElectionRepository)

  "ElectionService" should "create an Election with JSON" in {
    (mockedElectionRepository.saveElectionFile _) expects(*)
    (mockedElectionRepository.electionFileExists _) expects() returning(false)

    val electionJson : String = ElectionFixtures.electionJson
    val election : Election = electionService.createElection(electionJson)

    election.candidates should not be empty
  }

  it should "throws ElectionCreationException if there's any candidate" in {
    val electionWithAnyCandidatesJson : String = ElectionFixtures.electionWithAnyCandidatesJson
    (mockedElectionRepository.saveElectionFile _) expects(*)
    (mockedElectionRepository.electionFileExists _) expects() returning(false)

    assertThrows[ElectionCreationException] {
      electionService.createElection(electionWithAnyCandidatesJson)
    }
  }

  it should "throws ElectionCreationException if create a election that was already created" in {
    val electionJson : String = ElectionFixtures.electionJson
    (mockedElectionRepository.electionFileExists _) expects() returning(true)

    assertThrows[ElectionCreationException] {
      electionService.createElection(electionJson)
    }
  }

  it should "load election" in {
    val electionJson : String = ElectionFixtures.electionJson
    (mockedElectionRepository.loadElectionFile _) expects() returning(electionJson)
    (mockedElectionRepository.electionFileExists _) expects() returning(true)

    val election : Election = electionService.loadElection

    election.candidates should not be empty
  }

  it should "throw ElectionLoadingException if loads election without create before" in {
    (mockedElectionRepository.electionFileExists _) expects() returning(false)

    assertThrows[ElectionLoadingException] {
      electionService.loadElection
    }
  }

}
