package pro.diventa.tutorial.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class GreeterActor extends AbstractActor {

    private final String message;
    private final ActorRef printerActorRef;
    private String greeting = "";

    public GreeterActor(String message, ActorRef printerActorRef) {
        this.message = message;
        this.printerActorRef = printerActorRef;
    }

    @Override
    public Receive createReceive() {

        return receiveBuilder()
                .match(WhoToGreetMessage.class, wtg -> {
                    this.greeting = message + ", " + wtg.who;
                })
                .match(GreetMessage.class, x -> {
                    printerActorRef.tell(new GreetingMessage(greeting), getSelf());
                })
                .build();

    }

    static public Props props(String message, ActorRef printerActor) {
        return Props.create(GreeterActor.class, () -> new GreeterActor(message, printerActor));
    }

    static public class WhoToGreetMessage {
        public final String who;

        public WhoToGreetMessage(String who) {
            this.who = who;
        }
    }

    static public class GreetMessage {
        public GreetMessage() {
        }
    }
}
