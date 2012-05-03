package de.frogurth.gotd.quicknoting

import de.frogurth.gotd.quicknoting.util._
import java.io.File
import de.frogurth.gotd.quicknoting.repo.Repository

object Main {
  def main(args: Array[String]): Unit = {
    
    args(0) match {
      case REPO_OPEN => Repository.open(args(1))
      case REPO_CLOSE => Repository.close
      case REPO_NEW => Repository.newRepo(args(1), args(2))
      case REPO_DELETE => Repository.rm(args(1))
      case REPO_SHOW => Repository.list
      case NOTE => Repository.current.map(_.note(args(1))).getOrElse(Error.printNoOpenRepoError)
      case _ =>
    }
  }
}