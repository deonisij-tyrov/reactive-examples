package guru.springframework.reactiveexamples;

import guru.springframework.reactiveexamples.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Created by jt on 2/27/21.
 */
public class PersonRepositoryImpl implements PersonRepository {

    private static final Map<Integer, Person> map
            = Map.of(1, new Person(1, "firstname1", "lastName1"),
            2, new Person(2, "firstname2", "lastName2"),
            3, new Person(3, "firstname3", "lastName3"));

    @Override
    public Mono<Person> getById(Integer id) {
        return findAll().filter(p -> p.getId().equals(id)).next();
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.fromIterable(map.values());
    }
}
