package org.cli

case class FileSystem() {
  type AbsolutePath = String

  def createDirectory(path: AbsolutePath): Nothing = ???

  def createFile(path: AbsolutePath): Nothing = ???

  def listDirectory(name: AbsolutePath): Seq[AbsolutePath] = ???
}
