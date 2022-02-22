package comp583.project.manager;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import comp583.project.manager.project.Project;
import comp583.project.manager.project.ProjectInput;
import comp583.project.manager.project.ProjectRepository;
import comp583.project.manager.team.Team;
import comp583.project.manager.team.TeamInput;
import comp583.project.manager.team.TeamRepository;
import comp583.project.manager.ticket.Ticket;
import comp583.project.manager.ticket.TicketInput;
import comp583.project.manager.ticket.TicketRepository;
import comp583.project.manager.user.User;
import comp583.project.manager.user.UserInput;
import comp583.project.manager.user.UserRepository;
import lombok.AllArgsConstructor;
import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public Project createProject(ProjectInput input) {
        return projectRepository.saveAndFlush(
                new Project(input.getName(), input.getDescription(), input.getStartTime(), input.getEndTime()));
    }

    @Transactional
    public Team createTeam(TeamInput input) {
        return teamRepository.saveAndFlush(
                new Team(input.getName(), input.getPrefix()));
    }

    @Transactional
    public Ticket createTicket(TicketInput input) {
        return ticketRepository.saveAndFlush(
                new Ticket(input.getName(), input.getPoint(), input.getStatus(), input.getDescription()));
    }

    @Transactional
    public User createUser(UserInput input) {
        return userRepository.saveAndFlush(
                new User(input.getName(), input.getUsername(), input.getEmail(), input.getPassword()));
    }
}
