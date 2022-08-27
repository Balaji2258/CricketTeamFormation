package com.test.aug27.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.test.aug27.exceptions.InvalidNameException;
import com.test.aug27.exceptions.PlayerNotFoundException;
import com.test.aug27.exceptions.PlayerValidator;
import com.test.aug27.exceptions.ValidNameException;
import com.test.aug27.pojo.Player;

class NameComparator implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		return o1.getName().compareToIgnoreCase(o2.getName());
	}
	
}

public class TeamSelection {
	
//	int id;
//	String name;
//	int matchesPlayed;
//	int totRuns;
//	int highestScore;
//	int wktsTaken;
//	int outOnZero;
//	String type;
//	double avgScore;
	
	public static List<Player> playerList, tempList;
	public static List<Player> team = new ArrayList<>();
	public static Scanner sc = new Scanner(System.in);
	public static PlayerValidator pv = new PlayerValidator();
	public static Random rand = new Random();
	
	static int count = 20;
	static int bowlerCount = 0;
	static int wktKeeper = 0;
	
	public static boolean validateName(String name) {
		Iterator<Player> itr = playerList.iterator();
		boolean ret = true;

		while(itr.hasNext()) {
			Player pl = itr.next();
			try {
//				uv.checkUserName(r.getUserName(), rx.getUserName());
				pv.checkName(name, pl.getName());
			} catch (InvalidNameException e) {
				System.out.println("Player already exists!!");
				ret = false;
				break;
			} catch (ValidNameException e) {
				
			}
		}
		return ret;
	}
	
	public void getPlayerList() {
		playerList = new ArrayList<>();
		tempList = new ArrayList<>();
		System.out.println("\nEnter details of 20 players: ");
		
		int id;
		String name;
		int matchesPlayed;
		int totRuns;
		int highestScore;
		int wktsTaken;
		int outOnZero;
		String type;
		double avgScore;
		
//		int j = 0;
//		while(j < 4) {
//		outer:
//			id = j+1;
//			System.out.println("Enter name, matched played, total runs scored, highest score, no. of wkts taken, no. of ducked out matches, player type: ");
//			name = sc.next();
//			matchesPlayed = sc.nextInt();
//			totRuns = sc.nextInt();
//			highestScore = sc.nextInt();
//			wktsTaken = sc.nextInt();
//			outOnZero = sc.nextInt();
//			type = sc.next();
////		outer:
//			while(true) {
//				if(validateName(name))
//					break;
//				break outer;
//			}
//			if(type.equalsIgnoreCase("bowler")) {
//				bowlerCount++;
//			}
//			if(type.equalsIgnoreCase("wicketKeeper")) {
//				if(wktKeeper == 1) {
//					System.out.println("Limit reached for wicket keeper!");
//					continue;
//				} else {
//					wktKeeper = 1;
//				}
//			}
//			avgScore = (double)totRuns/matchesPlayed;
//			playerList.add(new Player(id, name, matchesPlayed, totRuns, highestScore, wktsTaken, outOnZero, type, avgScore));
//			++j;
//		}
		
		int  i = 0;
		while(i < 15) {
			id = i+1;
			
			while(true) {
				System.out.println("\nEnter name: ");
				name = sc.next();
				if(validateName(name))
					break;
			}
			
			System.out.println("Enter no. of matches played: ");
			matchesPlayed = sc.nextInt();
			
			System.out.println("Enter total runs scored: ");
			totRuns = sc.nextInt();
			
			System.out.println("Enter highest score: ");
			highestScore = sc.nextInt();
			
			System.out.println("Enter no. of wickets taken: ");
			wktsTaken = sc.nextInt();
			
			System.out.println("Enter no. of matched ducked out: ");
			outOnZero = sc.nextInt();
			
			System.out.println("Enter the player type (batsman/bowler/wicketKeeper/allRounder): ");
			type = sc.next();
			
			if(type.equalsIgnoreCase("bowler")) {
				bowlerCount++;
			}
			if(type.equalsIgnoreCase("wicketKeeper")) {
				if(wktKeeper == 1) {
					System.out.println("Limit reached for wicket keeper!");
					continue;
				} else {
					wktKeeper = 1;
				}
			}
			
			avgScore = totRuns/matchesPlayed;
			Player pl = new Player(id, name, matchesPlayed, totRuns, highestScore, wktsTaken, outOnZero, type, avgScore);
			Player pl2 = new Player(id, name, matchesPlayed, totRuns, highestScore, wktsTaken, outOnZero, type, avgScore);
			playerList.add(pl);
			tempList.add(pl2);
			
			++i;
		}
	}
	
	public boolean checkBowlers() {
//		Iterator<Player> itr = playerList.listIterator();
//		bowlerCount = 0;
//		while(itr.hasNext()) {
//			Player p = itr.next();
//			if(p.getType().equalsIgnoreCase("bowler"))
//				bowlerCount++;
//		}
		
		boolean ret = false;
		if(bowlerCount >= 3)
			ret = true;
		return ret;
	}
	
	public void teamFormation() {
		team = new ArrayList<>();
		int noBowlers;
		while(true) {
			System.out.println("Enter number of bowlers: ");
			noBowlers = sc.nextInt();
			if(noBowlers > bowlerCount) {
				System.out.println("No. of bowlers exceeds actual bowler count!");
			} else {
				break;
			}
		}
		Collections.sort(tempList);
		Iterator itr = tempList.listIterator();
		int i = 0;
		while(i < noBowlers && itr.hasNext()) {
			Player pl = (Player) itr.next();
			if(pl.getType().equalsIgnoreCase("bowler")) {
				team.add(pl);
				tempList.remove(pl);
			}
		}
		i = 0;
		Iterator<Player> itr1 = tempList.listIterator();
		while(i < 11 && itr1.hasNext()) {
			team.add(itr1.next());
			++i;
		}
	}
	
	public void updatePlayer() {
		String name;
		while(true) {
			System.out.println("Enter player name to update: ");
			name = sc.next();
			try {
				pv.checkNameExists(name, playerList);
				break;
			} catch(PlayerNotFoundException pe) {
				System.out.println("Name not found!");
				break;
			}
		}
		Iterator<Player> itr = playerList.listIterator();
		while(itr.hasNext()) {
			Player p = itr.next();
			if(p.getName().equalsIgnoreCase(name)) {
				System.out.println("Enter no. of matches played: ");
				int matchesPlayed = sc.nextInt();
				p.setMatchesPlayed(matchesPlayed);
				
				System.out.println("Enter total runs scored: ");
				int totRuns = sc.nextInt();
				p.setTotRuns(totRuns);
				
				System.out.println("Enter highest score: ");
				p.setHighestScore(sc.nextInt());
				
				System.out.println("Enter no. of wickets taken: ");
				p.setWktsTaken(sc.nextInt());
				
				System.out.println("Enter no. of matched ducked out: ");
				p.setOutOnZero(sc.nextInt());
				
				p.setAvgScore(totRuns/matchesPlayed);
			}
		}
	}
	
	public void displayAll() {
		for(Player p : playerList) {
			System.out.println(p.toString());
		}
	}
	
	public void displayTeam() {
		for(Player p : team) {
			System.out.println(p.toString());
		}
	}

	public static void main(String[] args) {	
		TeamSelection ts = new TeamSelection();
//		ts.getPlayerList();
		
//		playerList.add(new Player(1, "abc", 6, 420, 85, 2, 0, "batsman", 420/6));
//		playerList.add(new Player(2, "zef", 10, 850, 92, 3, 2, "batsman", 850/10));
//		playerList.add(new Player(3, "ghi", 6, 400, 76, 8, 1, "bowler", 400/6));
//		playerList.add(new Player(4, "jkl", 9, 450, 69, 12, 2, "bowler", 450/9));
//		playerList.add(new Player(5, "mno", 15, 650, 76, 15, 3, "bowler", 650/15));
		
//		playerList.add(new Player(6, "pqr", 6, 420, 85, 2, 0, "batsman", 420/6));
//		playerList.add(new Player(7, "stu", 6, 420, 85, 2, 0, "batsman", 420/6));
//		while(true) {
//			ts.getPlayerList();
//			if(!ts.checkBowlers()) {
//				System.out.println("No. of bowlers enterd is: " + bowlerCount + ". Minimum required is 3...");
//				continue;
//			}
//			else
//				break;
//		}
		
//		boolean boolVal = true;
		while(true) {
			System.out.println("Select a choice: \n1. Display all players \n2. Update player information by name");
			System.out.println("3. Display final team \n4. Add player information \n5. Exit");
			int ch = sc.nextInt();
			
			switch(ch) {
				case 1: {
					Collections.sort(playerList, new NameComparator());
					ts.displayAll();
					break;
				}
				case 2: {
					ts.updatePlayer();
					break;
				}
				case 3: { 
					Collections.sort(team, new NameComparator());
					ts.teamFormation();
					ts.displayTeam();
					break;
				}
				case 4: {
					while(true) {
						ts.getPlayerList();
						if(!ts.checkBowlers()) {
							System.out.println("No. of bowlers enterd is: " + bowlerCount + ". Minimum required is 3...");
							continue;
						}
						else
							break;
					}
					break;
				}
				case 5: 
					System.exit(1);
				default: 
					System.out.println("Select a valid choice!");
			}
		}
		
//		//sort by avg score
//		Collections.sort(playerList);
//		for(Player p : playerList) {
//			System.out.println(p.toString());
//		}
//		System.out.println();
//		
//		//sort by name
//		Collections.sort(playerList, new NameComparator());
//		for(Player p : playerList) {
//			System.out.println(p.toString());
//		}
//		
////		while(true) {
////			System.out.println("Enter player name to update: ");
////			boolean up = ts.updatePlayer(sc.next());
////			if(up) {
////				break;
////			}
////		}
//		for(Player p : playerList) {
//			System.out.println(p.toString());
//		}
	}
}
