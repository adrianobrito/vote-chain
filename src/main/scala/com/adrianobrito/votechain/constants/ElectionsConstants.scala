package com.adrianobrito.votechain.constants

import java.io.File

object ElectionsConstants {

  val ELETIONS_FILE_PATH = "./election.json"

  def electionsFile : File = new File(ElectionsConstants.ELETIONS_FILE_PATH)

}
