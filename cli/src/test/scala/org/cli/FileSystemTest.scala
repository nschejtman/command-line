package org.cli

import org.scalatest.funsuite.AnyFunSuite

class FileSystemTest extends AnyFunSuite {
  test("Create file") {
    val fs = FileSystem()
    fs.createFile("/test/directory/A")
    fs.createFile("/test/directory/B")
    assert(fs.listDirectory("/test/directory") == Seq("/test/directory/A", "/test/directory/B"))

  }
  test("create Directory simple"){
    val fs = FileSystem()
    fs.createDirectory( "/test/directory")
    assert(fs.listDirectory(name = "/test") == Seq("/test/directory"))
  }

  test(testName = "create Directory Complex"){
    val fs =FileSystem()
    fs.createDirectory(path = "/test/directory/A")
    fs.createDirectory(path = "/test/directory/B")

    assert(fs.listDirectory(name = "/test/directory") == Seq("/test/directory/A", "/test/directory/B"))
  }

  test("List empty directory"){
    val fs =FileSystem()
    assert(fs.listDirectory(name = "/test/directory") == Seq())
     fs.createDirectory("/test/directory")
    assert(fs.listDirectory(name = "/test") == Seq("/test/directory"))
    assert(fs.listDirectory(name = "/test/directory") == Seq())
  }

  test(testName = "Delete directory"){
    val fs =FileSystem()
    fs.createDirectory(path = "/test/directory")
    assert(fs.listDirectory("/test") == Seq("/test/directory"))
    fs.deleteDirectory("/test/directory")
    assert(fs.listDirectory("/test") == Seq())

  }

}
