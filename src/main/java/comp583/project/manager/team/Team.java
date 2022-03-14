package comp583.project.manager.team;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import comp583.project.manager.project.Project;
import comp583.project.manager.user.User;

import javax.persistence.ManyToMany;

@Entity(name = "team")
@Table(name = "teams", uniqueConstraints = {
        @UniqueConstraint(name = "team_prefix_unique", columnNames = "prefix") })
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "prefix", nullable = false, columnDefinition = "TEXT")
    private String prefix;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = User.class, mappedBy = "teams", cascade = { CascadeType.ALL })
    private Set<User> members;

    @ManyToMany(targetEntity = Project.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.ALL
    })
    @JoinTable(name = "team_project", joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private Set<Project> projects;

    public Team() {
    }

    public Team(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;
    }

    public Team(Long id, String name, String prefix) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

}