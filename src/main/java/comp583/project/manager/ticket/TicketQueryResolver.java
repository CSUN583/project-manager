package comp583.project.manager.ticket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
@AllArgsConstructor
public class TicketQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> tickets() {
        return ticketRepository.findAll();
    }

    public Ticket ticket(Long id) {
        return ticketRepository.findById(id).get();
    }
}
