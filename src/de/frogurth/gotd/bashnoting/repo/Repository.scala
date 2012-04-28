package de.frogurth.gotd.bashnoting.repo

import de.frogurth.gotd.bashnoting.util._
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class Repository(path: String) {
  
  def note(message: String) {
    val filename = toDateString
      
    val file = if(path.endsWith(File.separator)) {
      new File(path + filename)
    } else {
      new File(path + File.separator + filename)
    }
    
    if(!file.exists())
      file.createNewFile();
    
    file.text = file.text + message
  }
  
  private def toDateString = {
    val simple = new SimpleDateFormat("yyyy-MM-dd")
    simple.format(new Date)
  }
}

object Repository {
  def open(name: String) {
    val c = getConfig.text.split("\n").filter(!_.contains("current")).mkString("\n")
    println(c)
    val repoConf = c.split("\n").filter(_.contains(name)).head.replace(" ","").split(":")
    getConfig.text = c + "\ncurrent:" + repoConf(1)
  }
  
  def current = {
    val c = getConfig.text.split("\n").filter(_.contains("current")).head.split(":")
    new Repository(c(1))
  }
  
  def newRepo(name: String, path: String) {
    val c = getConfig.text
    if(c.contains(name)) {
    	println("Repository " + name + " already exist.");
    } else {
      val repoDir = new File(path)
      if(!repoDir.exists)
        repoDir.mkdir
      getConfig.text = c + name + ":" + repoDir.getAbsolutePath
    }
  }
  
  def rm(name: String) {
    val c = getConfig.text.split("\n").filter(!_.contains(name)).mkString("\n")
    getConfig.text = c
  }
  
  def close {
    val c = getConfig.text.split("\n").filter(!_.contains("current")).mkString("\n")
    getConfig.text = c
  }
  
  def list {
    getConfig.text.split("\n").filter(!_.contains("current")).map { s =>
      val r = s.split(":")
      (r(0), r(1))
    } foreach { r =>
      println(r._1 + " -> " + r._2)
    }
  }
}