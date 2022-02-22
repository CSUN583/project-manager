package comp583.project.manager.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
@AllArgsConstructor
public class ProjectQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> projects() {
        return projectRepository.findAll();
    }

    public Project project(Long id) {
        return projectRepository.findById(id).get();
    }
}
