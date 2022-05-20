package org.cli

case class FileSystem() {
  type AbsolutePath = String
  private val root = Directory("/")

  def createDirectory(path: AbsolutePath): Unit = {
    val paths = path.split('/').tail // /source/damian
    var currentDir: Directory = root //empieza siendo el root
    paths.foreach { name => //temp var
      val maybeNode: Option[Node] = currentDir.children.find(_.name == name) //busca el nombre en el hijo del current. option[node], si lo encuentra devuelve nodo sino none
      if (maybeNode.isDefined && maybeNode.get.isInstanceOf[Directory]) {//chequeo si esta definido y si devuelve un directorio
        currentDir = maybeNode.get.asInstanceOf[Directory]
      } else if(name == paths.last) {
        val newDirectory = Directory(name)
        currentDir.children = currentDir.children :+ newDirectory // children = lista de nodos
      } else {
        val newDirectory = Directory(name)
        currentDir.children = currentDir.children :+ newDirectory
        currentDir = newDirectory
      }
    }

  }

  def createFile(path: AbsolutePath): Unit = {
    val paths = path.split("/").tail
    var currentDir: Directory = root
    paths.foreach { name =>
      val maybeNode: Option[Node] = currentDir.children.find(_.name == name)
      if (maybeNode.isDefined && maybeNode.get.isInstanceOf[Directory]) {
        currentDir = maybeNode.get.asInstanceOf[Directory]
      } else if(name == paths.last) {
        val newFile = File(name)
        currentDir.children = currentDir.children :+ newFile
      } else {
        val newDirectory = Directory(name)
        currentDir.children = currentDir.children :+ newDirectory
        currentDir = newDirectory
      }
    }
  }

  def listDirectory(name: AbsolutePath): Seq[AbsolutePath] = {
    var currentDir = root
    val paths = name.split("/")
    paths.foreach{ name =>
      val maybeNode = currentDir.children.find(_.name == name)
      if (maybeNode.isDefined && maybeNode.get.isInstanceOf[Directory]) {
        currentDir = maybeNode.get.asInstanceOf[Directory]
      }
    }
    currentDir.children.map(name + "/" + _.name)
  }

  def deleteDirectory(path: AbsolutePath): Unit = {
    val paths = path.split('/').tail // /source/damian
    var i = 0
    var currentDir: Directory = root //empieza siendo el root
    paths.foreach { name => //temp var
      val maybeNode: Option[Node] = currentDir.children.find(_.name == name) //busca el nombre en el hijo del current. option[node], si lo encuentra devuelve nodo sino none
      if (maybeNode.isDefined && maybeNode.get.isInstanceOf[Directory] && paths.length - 1 == i) {
        currentDir.children = currentDir.children.filter(_.name != name)
      } else if (maybeNode.isDefined && maybeNode.get.isInstanceOf[Directory]) {
        currentDir = maybeNode.get.asInstanceOf[Directory]
      }
      i = i + 1


      /*else if(name == paths.last) {//aca tenes que borrar el elemento
        //val newDirectory = Directory(name)
        currentDir.children = currentDir.children.filter(elem => elem ==name)// children = lista de nodos
      }*/

    }
  }



}






trait Node {
  val name: String
}

case class Directory(name: String) extends Node {
  var children: List[Node] = List.empty
}

case class File(name: String) extends Node