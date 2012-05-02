package de.frogurth.gotd.bashnoting.repo

import de.frogurth.gotd.bashnoting.util._
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class Repository(path: String) {
  
  def note(message: String) {
    val filename = getDateString + ".txt"
      
    val file = if(path.endsWith(File.separator)) {
      new File(path + filename)
    } else {
      new File(path + File.separator + filename)
    }
    
    if(!file.exists())
      file.createNewFile();
    
    file.text = file.text + message
  }
  
  private def getDateString = {
    val simple = new SimpleDateFormat("yyyy-MM-dd")
    simple.format(new Date)
  }
}

object Repository {
  def open(name: String) {
    val confText = getConfig.text
    if(confText.contains(name)) {
      val c = confText.split("\n").filter(!_.contains("current")).mkString("\n")
      val repoConf = c.split("\n").filter(_.contains(name)).head.replace(" ","").split(":")
      getConfig.text = c + "\ncurrent:" + repoConf(0)
    } else {
    	Error.printNoSuchRepoError(name)
    }
  }
  
  def current = {
    val confText = getConfig.text
    if(confText.contains("current")) {
      val c = confText.split("\n").filter(_.contains("current")).head.split(":")
      val path = confText.split("\n").filter(_.contains(c(1))).head.split(":")
      Some(new Repository(path(1)))
    } else {
      None
    }
  }
  
  def newRepo(name: String, path: String) {
    val c = getConfig.text
    if(c.contains(name)) {
    	Error.printRepoAlreadyExistError(name)
    } else {
      val repoDir = new File(path)
      if(!repoDir.exists)
        repoDir.mkdir
      getConfig.text = c + name + ":" + repoDir.getAbsolutePath
    }
  }
  
  def rm(name: String) {
    val cofigTest = getConfig.text
    if(!cofigTest.contains(name)) {
      Error.printNoSuchRepoError(name)
    } else {
      val c = cofigTest.split("\n").filter(!_.contains(name)).mkString("\n")
      getConfig.text = c
    }
  }
  
  def close {
    val c = getConfig.text.split("\n").filter(!_.contains("current")).mkString("\n")
    getConfig.text = c
  }
  
  def list {
    val configText = getConfig.text
    if(configText.isEmpty()) {
      Error.printNoReposError
    } else {
      configText.split("\n").filter(s => !s.contains("current") && !s.isEmpty).map { s =>
        val r = s.split(":")
        (r(0), r(1))
      } foreach { r =>
        println(r._1 + " -> " + r._2)
      }
    }
  }
}