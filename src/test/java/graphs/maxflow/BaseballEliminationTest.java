package graphs.maxflow;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class BaseballEliminationTest {
    @Test
    public void isBostonEliminated() {
        BaseballElimination division = new BaseballElimination("/maxflow/teams5.txt");
        Assert.assertThat(division.isEliminated("Boston"), CoreMatchers.equalTo(false));
    }

    @Test
    public void isNewYorkEliminated() {
        BaseballElimination division = new BaseballElimination("/maxflow/teams5.txt");
        Assert.assertThat(division.isEliminated("New_York"), CoreMatchers.equalTo(false));
    }

    @Test
    public void isBaltimoreEliminated() {
        BaseballElimination division = new BaseballElimination("/maxflow/teams5.txt");
        Assert.assertThat(division.isEliminated("Baltimore"), CoreMatchers.equalTo(false));
    }

    @Test
    public void isDetroitEliminated() {
        BaseballElimination division = new BaseballElimination("/maxflow/teams5.txt");
        Assert.assertThat(division.isEliminated("Detroit"), CoreMatchers.equalTo(true));
    }

    @Test
    public void isTorontoEliminated() {
        BaseballElimination division = new BaseballElimination("/maxflow/teams5.txt");
        Assert.assertThat(division.isEliminated("Toronto"), CoreMatchers.equalTo(false));
    }

    @Test
    public void isAtlantaEliminated() {
        BaseballElimination division = new BaseballElimination("/maxflow/teams4.txt");
        Assert.assertThat(division.isEliminated("Atlanta"), CoreMatchers.equalTo(false));
    }

    @Test
    public void isPhiladelphiaEliminated() {
        BaseballElimination division = new BaseballElimination("/maxflow/teams4.txt");
        Assert.assertThat(division.isEliminated("Philadelphia"), CoreMatchers.equalTo(true));
    }

    @Test
    public void isNewYorkEliminatedLeague2() {
        BaseballElimination division = new BaseballElimination("/maxflow/teams4.txt");
        Assert.assertThat(division.isEliminated("New_York"), CoreMatchers.equalTo(false));
    }

    @Test
    public void isMontrealEliminated() {
        BaseballElimination division = new BaseballElimination("/maxflow/teams4.txt");
        Assert.assertThat(division.isEliminated("Montreal"), CoreMatchers.equalTo(true));
    }

    @Test
    public void isHoustonEliminatedInFile10Teams() {
        BaseballElimination division = new BaseballElimination("/maxflow/teams10.txt");
        Assert.assertThat(division.isEliminated("Houston"), CoreMatchers.equalTo(true));
    }

    @Test
    public void isTeam4EliminatedInFile48Teams() {
        BaseballElimination division = new BaseballElimination("/maxflow/teams48.txt");
        Assert.assertThat(division.isEliminated("Team4"), CoreMatchers.equalTo(true));
        // for (String s : division.certificateOfElimination("Team4"))
        // System.out.println(s);
    }

    @Test
    public void isTeam7EliminatedInFile12TeamsAllGames() {
        BaseballElimination division = new BaseballElimination("/maxflow/teams12-allgames.txt");
        Assert.assertThat(division.isEliminated("Team7"), CoreMatchers.equalTo(true));
        for (String s : division.certificateOfElimination("Team7"))
            System.out.println(s);
    }
}
