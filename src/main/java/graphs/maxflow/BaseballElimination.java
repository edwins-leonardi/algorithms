package graphs.maxflow;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class BaseballElimination {

    private Team[] teams;
    private int n;
    private int numberOfGames;
    private int numberOfVertices;
    private int[][] matches;

    public BaseballElimination(String filename) {
        In in = new In(filename);
        n = in.readInt();
        teams = new Team[n];
        int i = 0;
        numberOfGames = calcNumberOfDifferentGames();
        numberOfVertices = calcNumberOfVertices();
        matches = new int[numberOfGames][2];
        while (in.hasNextLine() && !in.isEmpty()) {
            teams[i] = new Team(i, in.readString(), in.readInt(), in.readInt(), in.readInt());
            int[] games = new int[n];
            for (int j = 0; j < n; j++)
                games[j] = in.readInt();
            teams[i].games = games;
            i++;
        }
    }

    public int numberOfTeams() {
        // number of teams
        return n;
    }

    public Iterable<String> teams() {
        Queue<String> list = new Queue<>();
        for (Team t : teams)
            list.enqueue(t.name);
        return list;
    }

    public int wins(String team) {
        return findTeamByName(team).wins;
    }

    public int losses(String team) {
        return findTeamByName(team).losses;
    }

    public int remaining(String team) {
        return findTeamByName(team).remaining;
    }

    public int against(String team1, String team2) {
        Team t1 = findTeamByName(team1);
        Team t2 = findTeamByName(team2);
        return t1.games[t2.id];
    }

    public boolean isEliminated(String name) {

        Team team = findTeamByName(name);
        if (checkTrivialElimination(team))
            return true;

        return isEliminatedByMaxFlow(team);
    }

    private boolean isEliminatedByMaxFlow(Team team) {
        FlowNetwork flowNetwork = createFlowNetWork(team);
        FordFulkerson ff = new FordFulkerson(flowNetwork, getSourceIndex(), getSinkIndex());
        if (getMaxFlowForOtherTeams() > ff.value()) {
            calcInCut(team, ff);
            return true;
        }
        return false;
    }

    public Iterable<String> certificateOfElimination(String name) {
        // subset R of teams that eliminates given team; null if not eliminated
        Queue<String> list = null;
        if (isEliminated(name)) {
            list = new Queue<>();
            for (Team t : teams)
                if (t.inCut)
                    list.enqueue(t.name);
        }
        return list;
    }

    private Team findTeamByName(String name) {
        for (Team t : teams) {
            if (t.name.equals(name))
                return t;
        }
        throw new IllegalArgumentException("Invalid team name " + name);
    }

    private boolean checkTrivialElimination(Team team) {
        boolean result = false;
        for (Team otherTeam : teams) {
            if (!otherTeam.equals(team)) {
                int capacity = team.wins + team.remaining - otherTeam.wins;
                if (capacity < 0) {
                    result = true;
                    otherTeam.inCut = true;
                    return true;
                }
            }
        }
        return result;
    }

    private void calcInCut(Team t, FordFulkerson ff) {
        for (int i = 0; i < teams.length; i++) {
            if (!t.equals(teams[i]))
                teams[i].inCut = ff.inCut(i);
        }
    }

    private int calcNumberOfVertices() {
        return n + 2 + numberOfGames;
    }

    private int calcNumberOfDifferentGames() {
        int total = 0;
        int games = n - 2;
        while (games > 0) {
            total += games;
            games--;
        }

        return total;
    }

    private FlowNetwork createFlowNetWork(Team team) {
        FlowNetwork flowNetwork = new FlowNetwork(numberOfVertices);
        addEdgesFromSourceToGames(team, flowNetwork);
        addEdgesFromGamesToTeams(flowNetwork);
        addEdgesFromTeamsToSink(flowNetwork, team);
        return flowNetwork;
    }

    private void addEdgesFromSourceToGames(Team team, FlowNetwork flowNetwork) {
        int numberOfMatches = 0;
        for (int i = 0; i < teams.length; i++) {
            if (team.equals(teams[i]))
                continue;
            for (int j = i + 1; j < teams.length; j++) {
                if (team.equals(teams[j]))
                    continue;
                String t1 = teams[i].name;
                String t2 = teams[j].name;
                matches[numberOfMatches][0] = i;
                matches[numberOfMatches][1] = j;
                flowNetwork.addEdge(new FlowEdge(getSourceIndex(), n + numberOfMatches, against(t1, t2)));
                numberOfMatches++;
            }
        }
    }

    private void addEdgesFromGamesToTeams(FlowNetwork flowNetwork) {
        for (int i = 0; i < matches.length; i++) {
            int t1 = matches[i][0];
            int t2 = matches[i][1];
            flowNetwork.addEdge(new FlowEdge(n + i, t1, Double.POSITIVE_INFINITY));
            flowNetwork.addEdge(new FlowEdge(n + i, t2, Double.POSITIVE_INFINITY));
        }
    }

    private int getMaxFlowForOtherTeams() {
        int maxFlow = 0;
        for (int i = 0; i < matches.length; i++) {
            Team t1 = teams[matches[i][0]];
            Team t2 = teams[matches[i][1]];
            maxFlow += against(t1.name, t2.name);
        }
        return maxFlow;
    }

    private void addEdgesFromTeamsToSink(FlowNetwork flowNetwork, Team team) {
        for (Team otherTeam : teams) {
            if (!otherTeam.equals(team)) {
                int capacity = team.wins + team.remaining - otherTeam.wins;
                if (capacity < 0)
                    capacity = 0;
                flowNetwork.addEdge(new FlowEdge(otherTeam.id, getSinkIndex(), capacity));
            }
        }
    }

    private int getSourceIndex() {
        return n + numberOfGames;
    }

    private int getSinkIndex() {
        return n + numberOfGames + 1;
    }

    private class Team {
        private int id;
        private String name;
        private int wins;
        private int losses;
        private int remaining;
        private int[] games;
        private boolean inCut;

        Team(int id, String name, int wins, int losses, int remaining) {
            this.id = id;
            this.name = name;
            this.wins = wins;
            this.losses = losses;
            this.remaining = remaining;
        }

        @Override
        public String toString() {
            return name + " " + wins + " " + losses + " " + remaining;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            Team other = (Team) obj;
            return this.name.equals(other.name);
        }
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination("/maxflow/teams5.txt");
        System.out.println();
        System.out.println("Is Detroit eliminated?");
        boolean b = division.isEliminated("Detroit");
        System.out.println(b);
        for (String t : division.certificateOfElimination("Detroit")) {
            System.out.println(t);
        }
    }
}
