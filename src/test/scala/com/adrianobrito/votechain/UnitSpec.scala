package com.adrianobrito.votechain

import org.scalamock.scalatest.MockFactory
import org.scalatest._

abstract class UnitSpec extends FlatSpec
  with Matchers
  with OptionValues
  with Inside
  with Inspectors
  with BeforeAndAfter
  with MockFactory