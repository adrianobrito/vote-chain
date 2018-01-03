package com.adrianobrito.votechain

import com.adrianobrito.votechain.election.{Election, Vote}
import com.adrianobrito.votechain.p2p.{Result, Peer}

class Votechain(var running:Boolean = false) {

  def up(electionJson:String) = {
    // create election
    // startup actors
    // generate code
    ElectionService.createElection(electionJson)
    this.running = true
  }

  def election : Election = ElectionService.loadElection

  def down(electionId:String) = {}
  def fetchPeers : Set[Peer] = null
  def registerPeer(peer:Peer) = {}
  def mine(vote:Vote) = {}
  def votes : Seq[Vote] = null
  def currentResult : Result = null

  def isUp = false

}

object Votechain extends Votechain(false)
