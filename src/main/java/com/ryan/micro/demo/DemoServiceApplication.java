package com.ryan.micro.demo;

import com.ryan.micro.demo.entity.User;
import com.ryan.micro.demo.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

@SpringBootApplication
public class DemoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {

            User user = new User();
            user.setUserId("James");
            user.setRealityName("James.S.Trump");
            System.out.println(">>>> " + userRepository.save(user));

//            userRepository.findAll((Specification<User>) (root, query, criteriaBuilder) -> query
//                    .where(criteriaBuilder.equal(root.get("id"), 1),
//                            criteriaBuilder.or(criteriaBuilder.equal(root.get("userId"), "James"))).getRestriction());

            userRepository.findAll(new Specification<User>() {
                @Nullable
                @Override
                public Predicate toPredicate(Root<User> root, @Nullable CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.or(criteriaBuilder.equal(root.get("id"), 1), criteriaBuilder.equal(root.get("userId"), "James"));
                }
            });
            Page<User> p = userRepository.findAll(PageRequest.of(0, 10));
            System.out.println(">>>> " + p);
            System.exit(0);
        };
    }
}
