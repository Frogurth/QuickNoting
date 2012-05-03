package de.frogurth.gotd.quicknoting.util

object Error {
  def printNoSuchRepoError(repo: String) {
    println("Not found repository " + repo)
  }
  
  def printRepoAlreadyExistError(repo: String) {
    println("Repository with the name " + repo + " already exist")
  }
  
  def printNoOpenRepoError {
    println("No open repository")
  }
  
  def printNoReposError {
    println("No repositories found")  
  }
}
