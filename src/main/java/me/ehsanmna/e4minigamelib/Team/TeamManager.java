package me.ehsanmna.e4minigamelib.Team;

import me.ehsanmna.e4minigamelib.Arena.Arena;
import me.ehsanmna.e4minigamelib.Utilities.Colors;

public class TeamManager {

    public Team createTeam(Arena arena, String name, Colors color){
        return new Team(color,name);
    }

}
