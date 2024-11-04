# Event Driven Architecture Based on CQRS and Event Sourcing Patterns

## Overview

Event Driven Architecture (EDA) is a design paradigm that focuses on the production, detection, and reaction to events. Within this architecture, two key patterns are often employed: Command Query Responsibility Segregation (CQRS) and Event Sourcing. Together, these patterns facilitate the creation of scalable and maintainable applications by clearly separating read and write operations and capturing state changes as a series of events.

## Key Concepts

### Aggregate

An **aggregate** is the main class where business logic is executed. It serves as a boundary for managing the state and behavior of a specific domain entity. In CQRS, aggregates are responsible for enforcing business rules and maintaining invariants.

### Event Store

The **event store** acts as the backbone for auditing and persistence. It functions similarly to a Kafka topic, where all events related to state changes are stored. This ensures a reliable and durable record of all actions taken within the application.

### Commands and Events

- **Commands**: Commands are requests sent from the outside world to initiate an action within the application. They represent intentions to change the state and are typically issued by users or other systems.

- **Events**: Events represent the internal state changes that occur as a result of command processing. They are immutable and can only be accessed via getters, ensuring a clear history of what has transpired in the system.

### CQRS

CQRS is a pattern that separates the read and write sides of an application:

- **Write Side**: Responsible for processing commands and executing business logic. This includes validating the command, making changes to the aggregate, and emitting events.

- **Read Side**: Focused on responding to queries without modifying the application's state. This separation allows for optimized read and write operations.

## Axon Server

Axon Server serves as both an event store and a message delivery system. It coordinates communication between various components of the application, taking into account the expectations associated with different message types: commands, events, and queries.

### Application Flow

The typical flow of the application is as follows:

1. **Command**: A command is issued, representing a user’s intention.
2. **Business Logic Processing**: The command handler within the aggregate processes the command, applying business logic.
3. **Emit Events**: Upon successful processing, events are emitted to the event store, capturing the state change.
4. **Change Application State**: The application state is updated based on the emitted events.

### Decision Function and Evolution Function

- **Decision Function**: The command handler within the aggregate determines the outcome of the command by applying business rules.
  
- **Evolution Function**: The event sourcing handler is responsible for evolving the application state based on the events stored in the event store. Event sourcing captures all state changes as a sequence of events, enabling reconstruction of the application state at any point in time.

### Asynchronous Operations

In an event-driven architecture, operations are often asynchronous. An object such as `CompletableFuture` can be used to represent an operation that may be completed at a later time. This allows for the execution of tasks in parallel without blocking the main thread, and it enables the chaining of multiple asynchronous operations.

### Aggregate Constructor

In an aggregate, it is mandatory to have a **no-argument constructor**. This is essential for deserialization when events are replayed from the event store.

### Axon server
If the Axon server doesn’t work, please run it using Docker with the following command:
```
docker run --name axonserver -p 8024:8024 -p 8124:8124 -v "/c/Users/allak/Downloads/AxonServer/docker-data/data":/data -v "/c/Users/allak/Downloads/AxonServer/docker-data/eventdata":/eventdata -v "/c/Users/allak/Downloads/AxonServer/docker-data/config":/config axoniq/axonserver
```

