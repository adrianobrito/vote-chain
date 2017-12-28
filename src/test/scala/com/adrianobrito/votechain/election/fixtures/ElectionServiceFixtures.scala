package com.adrianobrito.votechain.election.fixtures

object ElectionServiceFixtures {

  def electionJson = {
    "{ \"candidates\" : [{ \"id\" : 45, \"name\": \"Aecio\" }, { \"id\" : 13, \"name\": \"Dilma\" }]}"
  }

  def electionWithAnyCandidatesJson = {
    "{ \"candidates\" : [] }"
  }

}
