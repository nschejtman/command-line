package org.cli

import org.scalatest.funsuite.AnyFunSuite

class FileSystemTest extends AnyFunSuite {
  test("Create directory") {
    val fs = FileSystem()
    fs.createDirectory("/test/directory")
    assert(fs.listDirectory("/test") == Seq("directory"))
  }

}
