package ir.e4Team.e4minigamelib.Team;

import ir.e4Team.e4minigamelib.Arena.Arena;
import ir.e4Team.e4minigamelib.Utilities.Colors;

public class TeamManager {

    public Team createTeam(Arena arena, String name, Colors color){
        return new Team(color,name);
    }

}
