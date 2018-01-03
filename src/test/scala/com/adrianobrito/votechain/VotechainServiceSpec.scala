package com.adrianobrito.votechain

import com.adrianobrito.votechain.election.{Election, Candidate, Vote}
import com.adrianobrito.votechain.fixtures.VotechainFixtures
import com.adrianobrito.votechain.p2p.Peer
import play.api.libs.json.Json

class VotechainServiceSpec extends UnitSpec {

  implicit val candidateFormat = Json.format[Candidate]
  implicit val electionFormat = Json.format[Election]

  "VotechainService" should "perform election startup" in {
    val electionJson = VotechainFixtures.electionJson

    Votechain.up(electionJson)
    val election : Election = Json.fromJson[Election](Json.parse(electionJson)).get

    Votechain.isUp shouldBe true
    Votechain.election shouldBe equal(election)
  }

  it should "discovery new peers" in {
    val peers : Set[Peer] = Votechain.fetchPeers

    peers.size should be <= 100
  }

  it should "perform peer registry" in {
    val peer : Peer = Peer("127.0.0.1", 8080)

    Votechain.registerPeer(peer)
    val peers : Set[Peer] = Votechain.fetchPeers

    peers should contain (peer)
  }

  it should "mine a new vote" in {
    val vote = Vote("6768687", 45)

    Votechain.mine(vote)

    Votechain.votes should contain (vote)
  }

  it should "perform election shutdown" in {
    val supposedCode = "END"

    Votechain.down(supposedCode)

    Votechain.isUp shouldBe false
  }

  it should "get the current result" in {
    val result = Votechain.currentResult

    result should not be (null)
  }

}
