package com.adrianobrito.votechain

import com.adrianobrito.votechain.election.Vote
import com.adrianobrito.votechain.peers.{Result, Peer}

class VotechainService {

  def up(electionJson:String) = {}
  def down(electionId:String) = {}
  def election : String = ""
  def fetchPeers : Set[Peer] = null
  def registerPeer(peer:Peer) = {}
  def mine(vote:Vote) = {}
  def votes : Seq[Vote] = null
  def currentResult : Result = null

  def isUp = false

}

object VotechainService extends VotechainService
