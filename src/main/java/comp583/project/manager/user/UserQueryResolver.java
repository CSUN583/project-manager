package comp583.project.manager.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
@AllArgsConstructor
public class UserQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private UserRepository userRepository;

    public List<User> users() {
        return userRepository.findAll();
    }

    public User user(Long id) {
        return userRepository.findById(id).get();
    }
}
