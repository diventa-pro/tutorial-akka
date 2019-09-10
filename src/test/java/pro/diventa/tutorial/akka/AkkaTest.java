package pro.diventa.tutorial.akka;

import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.actor.ActorSystem;
import akka.actor.Terminated;
import org.junit.jupiter.api.Test;
import scala.concurrent.Future;

public class AkkaTest {

    @Test public void run() throws InterruptedException {

        ActorSystem system = ActorSystem.create("test");

        final ActorRef printerActor =
                system.actorOf(PrinterActor.props(), "printerActor");

        final ActorRef howdyGreeter =
                system.actorOf(GreeterActor.props("Howdy", printerActor), "howdyGreeter");

        final ActorRef helloGreeter =
                system.actorOf(GreeterActor.props("Hello", printerActor), "helloGreeter");

        final ActorRef goodDayGreeter =
                system.actorOf(GreeterActor.props("Good day", printerActor), "goodDayGreeter");

        howdyGreeter.tell(new GreeterActor.WhoToGreetMessage("Akka"), ActorRef.noSender());
        howdyGreeter.tell(new GreeterActor.GreetMessage(), ActorRef.noSender());

        howdyGreeter.tell(new GreeterActor.WhoToGreetMessage("Lightbend"), ActorRef.noSender());
        howdyGreeter.tell(new GreeterActor.GreetMessage(), ActorRef.noSender());

        helloGreeter.tell(new GreeterActor.WhoToGreetMessage("Java"), ActorRef.noSender());
        helloGreeter.tell(new GreeterActor.GreetMessage(), ActorRef.noSender());

        goodDayGreeter.tell(new GreeterActor.WhoToGreetMessage("Play"), ActorRef.noSender());
        goodDayGreeter.tell(new GreeterActor.GreetMessage(), ActorRef.noSender());

        Thread.sleep(50000l);
        system.terminate();

    }

}
