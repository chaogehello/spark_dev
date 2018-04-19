/*
package com.hcb.spark

import java.io.File

/**
  * TODO 功能描述
  *
  * @author xu.zhang5
  * @date 2018/4/19 10:22
  * @since V1.0
  *
  */
object TestRichFile {

  implicit def d(file:File)=new RichFile(file)

  def main(args: Array[String]): Unit = {
    val richFile=new File("C://word.txt")
    richFile.read()

    Array(1,2,3,4,5).map(num=>num*3)

  }
}
*/
