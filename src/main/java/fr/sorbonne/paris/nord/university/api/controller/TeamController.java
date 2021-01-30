package fr.sorbonne.paris.nord.university.api.controller;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.mapper.TeamMapper;
import fr.sorbonne.paris.nord.university.api.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import java.util.stream.Collectors;

@RestController
public class TeamController {

    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamController(TeamService teamService, TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @GetMapping("/teams")
    public List<TeamDto> getTeams() {

        return teamService.getTeams()
                .stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }

    @RequestMapping("/teamByID/{id}")

    public TeamDto getTeamByID(@PathVariable long id) {

        return teamService.getTeamByID(id)
                .map(teamMapper::toDto)
                .orElse(null);
    }

    @PostMapping("/teamToAdd")
    public TeamDto addTeam(@RequestBody TeamDto teamDto) {
        TeamEntity teamEntityToSave = teamMapper.toEntity(teamDto);
        teamService.addTeam(teamEntityToSave);
        return teamMapper.toDto(teamEntityToSave);

    }

    @PutMapping("/teamToUpdate")
    public TeamDto updateTeam(@RequestBody TeamDto teamDto) {
        TeamEntity teamEntityToUpdate = teamMapper.toEntity(teamDto);
        teamService.updateTeam(teamEntityToUpdate);
        return teamMapper.toDto(teamEntityToUpdate);
    }

    @DeleteMapping("/teamToDeleteByID/{id}")
    public void teamToDeleteByID(@PathVariable long id) {
        teamService.deleteTeam(id);
    }

}