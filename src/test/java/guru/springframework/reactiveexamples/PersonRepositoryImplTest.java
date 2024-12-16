package guru.springframework.reactiveexamples;

import guru.springframework.reactiveexamples.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryImplTest {

    private final PersonRepository personRepository = new PersonRepositoryImpl();

    @Test
    void getById() {
        Person block = personRepository.getById(1).block();

        assertEquals(1, block.getId());
        assertEquals(block.getFirstName(), "firstname1");
    }
    @Test
    void getByIdIfNotExists() {
        Mono<Person> byId = personRepository.getById(100);
        assertNotEquals(Boolean.TRUE, byId.hasElement().block());
        assertNull(byId.block());
    }

    @Test
    void findAll() {
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.subscribe(System.out::println);
    }
}