package ir.e4Team.e4minigamelib.Arena.ScoreBoard;

import ir.e4Team.e4minigamelib.Team.Team;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;

public class ArenaBoard {

    Scoreboard waitingBoard;
    HashMap<Team,Scoreboard> teamBoards = new HashMap<>();

    public Scoreboard getWaitingBoard() {
        return waitingBoard;
    }

    public void setWaitingBoard(Scoreboard waitingBoard) {
        this.waitingBoard = waitingBoard;
    }

    public HashMap<Team, Scoreboard> getTeamBoards() {
        return teamBoards;
    }

    public void setTeamBoards(HashMap<Team, Scoreboard> teamBoards) {
        this.teamBoards = teamBoards;
    }
}
