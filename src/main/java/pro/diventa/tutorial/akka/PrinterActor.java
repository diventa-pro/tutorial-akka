package pro.diventa.tutorial.akka;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PrinterActor extends AbstractActor {

    static public Props props() {
        return Props.create(PrinterActor.class, () -> new PrinterActor());
    }

    public PrinterActor() {
    }

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(GreetingMessage.class, greetingMessage -> {
                    //log.info(greetingMessage.message);
                    System.out.println(greetingMessage.message);
                })
                .build();
    }

    static public class GreetingMessage {

        public final String message;
        public GreetingMessage(String message) {
            this.message = message;
        }

    }
}
