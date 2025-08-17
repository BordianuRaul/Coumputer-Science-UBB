object Main {

  val add1 = (x: Int, y: Int) => x + y

  def add(x: Int, y: Int): Int = x + y

  def main(args: Array[String]): Unit = {
    println("Hello world!")
    println(add1(1,5))
    println(add(1,5))
  }
}

//online runner: https://scastie.scala-lang.org/