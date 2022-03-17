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

    /*TEAM CREATE AND REMOVE*/
    @Transactional
    public Team createTeam(TeamInput input) {
        return teamRepository.saveAndFlush(
                new Team(input.getName(), input.getPrefix()));
    }

    @Transactional
    public Long removeTeam(Long team_id) {
        teamRepository.deleteById(team_id);

        return team_id;
    }

    /*TEAM ADD AND REMOVE USER*/
    @Transactional
    public Team addUserToTeam(Long user_id, Long team_id) {
        Team currentTeam = teamRepository.getById(team_id);
        User currentUser = userRepository.getById(user_id);
        
        currentTeam.addMember(currentUser);
        currentUser.addToTeam(currentTeam);

        teamRepository.save(currentTeam);
        userRepository.save(currentUser);

        return currentTeam;
    }

    @Transactional
    public Team removeUserFromTeam(Long user_id, Long team_id) {
        Team currentTeam = teamRepository.getById(team_id);
        User currentUser = userRepository.getById(user_id);

        currentTeam.removeMember(currentUser);
        currentUser.removeFromTeam(currentTeam);

        teamRepository.save(currentTeam);
        userRepository.save(currentUser);

        return currentTeam;
        
    }

    /*PROJECT ADD AND REMOVE TEAM*/
    @Transactional
    public Project addTeamToProject(Long team_id, Long project_id) {

        Team currentTeam = teamRepository.getById(team_id);
        Project currentProject = projectRepository.getById(project_id);
        
        currentProject.addTeam(currentTeam);
        currentTeam.addProject(currentProject);

        teamRepository.save(currentTeam);
        projectRepository.save(currentProject);
        
        return currentProject;
        
    }

    @Transactional
    public Project removeTeamFromProject(Long team_id, Long project_id) {
        Team currentTeam = teamRepository.getById(team_id);
        Project currentProject = projectRepository.getById(project_id);
        
        currentProject.removeTeam(currentTeam);
        currentTeam.removeProject(currentProject);

        teamRepository.save(currentTeam);
        projectRepository.save(currentProject);
        
        return currentProject;
        
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
