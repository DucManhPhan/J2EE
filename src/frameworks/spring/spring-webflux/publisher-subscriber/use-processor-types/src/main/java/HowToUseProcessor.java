import java.util.concurrent.Executors;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.ReplayProcessor;
import reactor.core.publisher.TopicProcessor;
import reactor.core.publisher.UnicastProcessor;
import reactor.core.publisher.WorkQueueProcessor;

public class HowToUseProcessor {

	/**
	 * Each processor provides a sink() method. A Sink is the preferrerd way of publishing events to the subscriber.
	 * It provides methods to publish next, error, and complete events.
	 * Sink provides a thread-safe manner of handling these events, instead of directly publishing them by 
	 * using the Subscriber.onNext() method calls.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// testDirectProcessor_1();

		// testDirectProcessor_2();

		// testUnicastProcessor();

		// testEmitterProcessor();
		
		// testTopicProcessor();
		
		testWorkQueueProcessor();
	}

	/**
	 * DirectProcessor has no backpressure capability at all
	 */
	public static void testDirectProcessor_1() {
		DirectProcessor<Long> data = DirectProcessor.create();
		data.subscribe(t -> System.out.println(t),
						e -> e.printStackTrace(), 
						() -> System.out.println("Finished 1")); // Create subscriber 1
		data.onNext(10L); // Add data event to subscriber 1
		data.onNext(11L);
		data.onComplete(); // Add completion event to subscriber 1. After completion event, no data event
							// will be created and reject.

		data.subscribe(t -> System.out.println(t), 
						e -> e.printStackTrace(), 
						() -> System.out.println("Finished 2")); // Create subscriber 2
		data.onNext(12L);
		data.onComplete();
	}

	public static void testDirectProcessor_2() {
		DirectProcessor<Long> data = DirectProcessor.create();
		data.subscribe(t -> System.out.println(t), 	// data event
						e -> e.printStackTrace(), 	// error event
						() -> System.out.println("Finished"),	// completion event
						s -> s.request(1));

		data.onNext(10L);
		data.onNext(11L);
		data.onNext(12L);
	}
	
	// Unlike the DirectProcessor, the UnicastProcessor is capable of backpressure.
	// Internally, it creates a queue to hold undelivered events. We can also provide an optional queue to buffer the events.
	// After the buffer is full, the processor starts to reject elements.
	// The processor also makes it possible to perform cleanup for every rejected element.
	// --> UnicastProcessor allows only a single Subscriber.
	public static void testUnicastProcessor() {
		UnicastProcessor<Long> data = UnicastProcessor.create();
		data.subscribe(t -> {
					System.out.println(t);
				},
				e -> e.printStackTrace(),
				() -> System.out.println("Finished"));
//		data.sink().next(10L);
		data.onNext(10L);

		data.subscribe(t -> System.out.println(t),
						e -> e.printStackTrace(),
						() -> System.out.println("Finished"));
		data.onNext(11L);
	}

	/**
	 * EmitterProcessor is a processor that can be used with several subscribers.
	 * Multiple subscribers can ask for the next value event, based on their individual rate of consumption.
	 * The processor provides the necessary backpressure support for each subscriber.
	 * The processor is also capable of publishing events from an external publisher.
	 * It consumes an event from the injected publisher and synchronously passes it to the subscribers.
	 */
	
	/**
	 * The processor delivers events to a subscriber after its subscription. This is different from Flux,
	 * which delivers all items to all subscribers, irrespective of the time of subscription.
	 */
	public static void testEmitterProcessor() {
		EmitterProcessor<Long> data = EmitterProcessor.create();
		data.subscribe(t -> System.out.println(t));
		FluxSink<Long> sink = data.sink();
		sink.next(10L);
		sink.next(11L);
		sink.next(12L);

		data.subscribe(t -> System.out.println(t));
		sink.next(13L);
		sink.next(14L);
		sink.next(15L);
	}
	
	/**
	 * ReplayProcessor is a special-purpose processor, capable of caching and replaying events to its subscribers.
	 * The processor also has the capability of publishing events from an external publisher.
	 * It consumes an event from the injected publisher and synchronously passes it to the subscribers.
	 * 
	 */
	public static void testReplayProcessor() {
		ReplayProcessor<Long> data = ReplayProcessor.create();
		data.subscribe(t -> System.out.println(t));
		FluxSink<Long> sink = data.sink();
		sink.next(10L);
		sink.next(11L);
		sink.next(12L);
		sink.next(13L);
		sink.next(14L);
		data.subscribe(t -> System.out.println(t));
	}

	/**
	 * TopicProcessor is a processor capable of working with multiple subscribers, using an event loop architecture.
	 * The processor delivers events from a publisher to the attached subscribers in an asynchronous manner,
	 * and honors backpressure for each subscriber by using the RingBuffer data structure.
	 * 
	 */
	public static void testTopicProcessor() {
		TopicProcessor<Long> data = TopicProcessor.<Long>builder().executor(Executors.newFixedThreadPool(2)).build();
		data.subscribe(t -> System.out.println(t));
		data.subscribe(t -> System.out.println(t));
		FluxSink<Long> sink= data.sink();
		sink.next(10L);
		sink.next(11L);
		sink.next(12L);
	}
	
	/**
	 * The WorkQueueProcessor type is similar to the TopicProcessor, in that it can connect to multiple subscribers.
	 * However, it does not deliver all events to each subscriber.
	 * The demand from every subscriber is added to a queue, and events from a publisher are sent to any of the subscribers.
	 * The model is more like having listeners on a JMS queue; each listener consumes a message when finished.
	 * The processor delivers messages to each of the subscribers in a round-robin manner.
	 * The processor is also capable of listening to events from multiple publishers. 
	 * 
	 */
	public static void testWorkQueueProcessor() {
		WorkQueueProcessor<Long> data = WorkQueueProcessor.<Long>builder().build();
		data.subscribe(t -> System.out.println("1. " + t));
		data.subscribe(t -> System.out.println("2. " + t));
		data.subscribe(t -> System.out.println("3. " + t));
		data.subscribe(t -> System.out.println("4. " + t));
		FluxSink<Long> sink = data.sink();
		sink.next(10L);
		sink.next(11L);
		sink.next(12L);
		sink.next(13L);
	}
}
