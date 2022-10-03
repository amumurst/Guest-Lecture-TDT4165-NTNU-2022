package e_7_extra
import cats.effect.{IO, IOApp, Ref}
import scala.concurrent.duration._
/*
  App that mutates mutable value 100k times with 1k concurrent modifications.
  Subject to race conditions!
 */
object MutableApp extends IOApp.Simple:
  private var x     = 0
  val changeX       = IO { x = x + 1 }
  val print         = IO.println(x)
  val program       = IO.parReplicateAN(1000)(100000, changeX).void
  val run: IO[Unit] = program >> print

/*
  App that mutates Ref value 100k times with 1k concurrent modifications.
  Not subject to race conditions!
 */
object RefApp extends IOApp.Simple:
  val x             = Ref.unsafe[IO, Int](0)
  val changeX       = x.update(_ + 1)
  val print         = x.get.flatMap(IO.println)
  val program       = IO.parReplicateAN(1000)(100000, changeX).void
  val run: IO[Unit] = program >> print

/*
  App that mutates Ref value every 100ms for 5 seconds
 */
object TimedApp extends IOApp.Simple:
  val x             = Ref.unsafe[IO, Int](0)
  val changeX       = x.update(_ + 1)
  val print         = x.get.flatMap(IO.println)
  val program       = changeX.delayBy(100.millis).foreverM.race(IO.sleep(5.seconds))
  val run: IO[Unit] = program >> print
