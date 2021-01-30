package fr.sorbonne.paris.nord.university.api;

import fr.sorbonne.paris.nord.university.api.controller.TeamController;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.mapper.TeamMapper;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;


@SpringBootTest
public class TeamControllerTest {

    @Mock
    private TeamService teamService ;

    @Autowired
     private TeamMapper teamMapper;
    MockMvc mockMvc ;

    List<TeamEntity> listTeam = new ArrayList<>();

    TeamEntity  PSG = new TeamEntity(1, "Paris","Dream Bigger");
    TeamEntity  LFC = new TeamEntity(2,"Liverpool","You never walks alone");
    TeamEntity  BFC = new TeamEntity(3,"Barcelona","Mes qu'un  club");

    private Optional<TeamEntity> teamEntity ;

    public List<TeamEntity> virtualDB(){
         listTeam.add(PSG);
         listTeam.add(LFC);
         listTeam.add(BFC);
         return listTeam;
    }

    @BeforeEach
    public void initialiseRestAssuredMockMvcStandalone() {
        RestAssuredMockMvc.standaloneSetup(new TeamController(teamService,teamMapper));
    }

    @Test
    public void givenUrlWhenRequestGetThenOK(){
        Mockito.when(teamService.getTeams()).thenReturn(virtualDB());

        given().when()
                .get("teams")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void shouldReturnStatus200WhenIDGivenExist(){
        Mockito.when(teamService.getTeamByID(1L)).thenReturn(teamEntity);

        given().when()
                .get("/teamByID/{id}",1L)
                .then()
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void shouldReturnStatus200WhenTheAddIsDone(){

        TeamEntity teamToADD = new TeamEntity(6,"Lens", "les nulls");
        Mockito.when(teamService.addTeam(Mockito.any())).thenReturn(teamToADD);

        given()
                .contentType("application/json")
                .body(teamMapper.toDto(teamToADD))
                .when()
                .post("teamToAdd")
                .then()
                .statusCode(200);
    }

    @Test
    public void soldReturnTrueWhenUpdateIsDone(){

     TeamEntity teamToUpdate = new TeamEntity(1,"RealMadrid","Y Nada Mas");
     Mockito.when(teamService.updateTeam(Mockito.any())).thenReturn(teamToUpdate);

     given().contentType(ContentType.JSON)
             .body(teamMapper.toDto(teamToUpdate))
             .when()
             .put("teamToUpdate")
             .then()
             .statusCode(200);
    }

    @Test
    public void shouldReturnTrueWhenTeamIsDeleted() throws Exception {

        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/teamToDeleteByID/2")
                .then()
                .extract().response();

       // Assertions.assertEquals(200,response.statusCode());
    }

}
