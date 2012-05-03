package de.frogurth.gotd.bashnoting

import java.io.File
import scala.io.Source
import java.io.PrintWriter
import java.io.FileWriter
package object util {
  
  val REPO_OPEN = "open"
  val REPO_CLOSE = "close"
  val REPO_NEW = "new"
  val REPO_DELETE = "rm"
  val REPO_SHOW = "ls"
  val NOTE = "note"
    
  val CONFIG_PATH = System.getProperty("user.home") + "/.bashnoting"
    
  def createConfig = {
    val config = new File(CONFIG_PATH)
    if(!config.createNewFile) {
      println("Could not create .bashconfig in home directory.")
      None
    } else {
      Some(config)
    }
  }
  
  def getConfig: File = {
    var config = new File(CONFIG_PATH)
    if(!config.exists) {
     config = createConfig.getOrElse(null);
    }
    config
  }
  
  class RichFile( file: File ) {

    def text = Source.fromFile( file ).mkString

    def text_=(s: String) {
      val out = new PrintWriter(new FileWriter(file, false));
      try{ out println s }
      finally{ out.close }
    }
  
    def println(s: String) {
	  val out = new PrintWriter(new FileWriter(file, true));
      try { out println (s) } 
      finally { out.close }
    }
  }
  
  implicit def enrichFile( file: File ) = new RichFile( file )

}