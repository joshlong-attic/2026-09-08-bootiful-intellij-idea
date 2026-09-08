package com.example.idea4;

import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@Controller
class CustomerGraphqlController {

    private final CustomerRepository customerRepository;

    CustomerGraphqlController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @QueryMapping
    Collection<Customer> customers() {
        return customerRepository.findAll();
    }

    @QueryMapping
    Collection <Customer> customersByName(@Argument String name) {
        return customerRepository.findByNameContaining(name);
    }
}

@RestController
class CustomerRestController {

    private final CustomerRepository customerRepository;

    CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    Collection<Customer> customers(@RequestParam Optional<String> name) {
        return name.map(customerRepository::findByNameContaining)
                .orElse(customerRepository.findAll());
    }
}

interface CustomerRepository extends ListCrudRepository<Customer, Integer> {

    Collection<Customer> findByNameContaining(String name);
}

@Component
class MyRunner implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    MyRunner(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(@NonNull ApplicationArguments args) throws Exception {
        var siva = customerRepository.findByNameContaining("Siva");
        siva.forEach(IO::println);
    }
}