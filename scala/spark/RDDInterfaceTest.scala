import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object RDDInterfaceTest {

  def main(args: Array[String]): Unit = {
    val data: Seq[Animal] = List(
      new UnknownColorDog("ud1"),
      new BlackDog("bd1"),
      new Cat("c1"),
      new UnknownColorDog("ud2"),
      new UnknownColorDog("ud3"),
      new BlackDog("bd2"),
      new Cat("c2"),
      new UnknownColorDog("ud4"),
    )

    val spark = SparkSession.builder().getOrCreate()

    val rdd: RDD[Animal] = spark.sparkContext.makeRDD(data, 2)

    val resultRDD = rdd.filter(_.isInstanceOf[Dog]).map {
      case a if a.isInstanceOf[UnknownColorDog] => a.asInstanceOf[UnknownColorDog].asBlack()
      case a => a
    }.map(d => s"${d.name()} ${d.sounds()}")

    //Success!!
    // Output: ud1 black wang wang, bd1 black wang wang, ud2 black wang wang, ud3 black wang wang, bd2 black wang wang, ud4 black wang wang
    println(resultRDD.collect().mkString(", "))
  }
}

trait Animal extends Serializable {
  def name(): String

  def sounds(): String
}

abstract class Dog(name: String) extends Animal {
  def color(): String

  override def name(): String = name

  override def sounds(): String = "wang wang"
}

class UnknownColorDog(name: String) extends Dog(name) {
  override def color(): String = "unknown"

  def asBlack(): BlackDog = new BlackDog(name)
}

class BlackDog(name: String) extends Dog(name) {
  override def color(): String = "black"

  override def sounds(): String = "black wang wang"
}

class Cat(name: String) extends Animal {
  override def name(): String = name

  override def sounds(): String = "miao miao"
}
