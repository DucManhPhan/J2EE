## Reactive Streams

Reactive streams provide an abstraction for highly concurrent, asynchronous applications with support for backpressure.

Reactive streams use the concepts of publisher and subscriber, along with various strategies for backpressure to model concurrency.

So, we will cover some above concepts:
- A publisher emits events at some rate.
- A subscriber observers those events on possibly a different thread and does something with them.
- Some frameworks use other words (such as Source and Sink) to mean the same thing as publisher and subscriber.

Many Reactive Streams frameworks allow
interoperation with other existing models of concurrency, such as futures,
to allow a smooth transition between the two.

### Hot and Cold

A hot observable is one that cannot be repeated. It starts creating data immediately regardless of whether it has subscribers.
Typically it involves interacting with data from the outside world such as mouse inputs, data readings, or web requests.

A cold Observable is one that can be repeated and does not start until
subscribed to. This could be things like a range, file data, or a cached
recording of data from a hot Observable.

Hot Observables typically are candidates for using backpressure flow
control strategies such as throttling, buffers, or windows.