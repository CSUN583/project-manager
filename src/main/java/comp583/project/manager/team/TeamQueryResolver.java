package comp583.project.manager.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
@AllArgsConstructor
public class TeamQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> teams() {
        return teamRepository.findAll();
    }

    public Team team(Long id) {
        return teamRepository.findById(id).get();
    }
}
