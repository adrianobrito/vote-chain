package com.adrianobrito.votechain

import com.adrianobrito.votechain.election.Vote
import com.adrianobrito.votechain.fixtures.VotechainFixtures
import com.adrianobrito.votechain.peers.Peer

class VotechainServiceSpec extends UnitSpec {

  "VotechainService" should "perform election startup" in {
    val election = VotechainFixtures.electionJson
    VotechainService.up(election)

    VotechainService.isUp shouldBe true
    VotechainService.election shouldBe equal(election)
  }

  it should "discovery new peers" in {
    val peers : Set[Peer] = VotechainService.fetchPeers

    peers.size should be <= 100
  }

  it should "perform peer registry" in {
    val peer : Peer = Peer("127.0.0.1", 8080)

    VotechainService.registerPeer(peer)
    val peers : Set[Peer] = VotechainService.fetchPeers

    peers should contain (peer)
  }

  it should "mine a new vote" in {
    val vote = Vote("6768687", 45)

    VotechainService.mine(vote)

    VotechainService.votes should contain (vote)
  }

  it should "perform election shutdown" in {
    val supposedCode = "END"

    VotechainService.down(supposedCode)

    VotechainService.isUp shouldBe false
  }

  it should "get the current result" in {
    val result = VotechainService.currentResult

    result should not be (null)
  }

}
