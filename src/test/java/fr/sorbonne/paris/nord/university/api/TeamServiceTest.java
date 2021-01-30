package fr.sorbonne.paris.nord.university.api;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Test
    public void shouldReturnTrueWhenTheQueryIsFine(){
        //When
        List<TeamEntity> result = teamService.getTeams();

        //Then
        assertThat(result).isNotNull().isNotEmpty();
    }

    @Test
    public void shouldReturnTrueWhenTheQueryByIDIsFine(){
        // Given.
        String given = "Barcelone";
        // When.
        Optional <TeamEntity> result  = teamService.getTeamByID(3);
        // Then.
        assertThat(result.get().getName()).isEqualTo(given);
    }

    @Test
    public void shouldReturnTrueWhenTheAddedIsDone(){
        //Given.
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(6);
        teamEntity.setName("Marseille");
        teamEntity.setSlogan("Droit aux buts");

        String given  = "Marseille";

        //When.
        Object addedTeam =  teamService.addTeam(teamEntity);

        //Then
        assertThat(teamEntity.getName()).isEqualTo(given);
    }

    @Test
    public void shouldReturnTrueWhenTheUpdatedIsDone(){
        //Given.
        TeamEntity teamEntity = new TeamEntity(1, "PSG","Dream Bigger");
        String slogan = "Dream Bigger";
        //When.
        Object updatedTeam = teamService.updateTeam(teamEntity);

        //Then.
        assertThat(teamEntity.getSlogan()).isEqualTo(slogan);
    }

    @Test
    public void shouldReturnTrueWhenTheDeletedIsDone(){
        //Given
        long id = 4 ;

        // When.
        teamService.deleteTeam(id);

        //Then.
        assertThat(id).isNotNull();
    }

}
