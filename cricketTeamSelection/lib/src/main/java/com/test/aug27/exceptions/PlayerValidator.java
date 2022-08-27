package com.test.aug27.exceptions;

import java.util.Iterator;
import java.util.List;

import com.test.aug27.pojo.Player;

public class PlayerValidator {
	public void checkName(String name, String objName) throws InvalidNameException, ValidNameException {
		if(name.equals(objName)) {
			throw new InvalidNameException("Player already exists! Enter different player details!");
		}
		else {
			throw new ValidNameException("Valid Player name!");
		}
	}
	
	public void checkNameExists(String name, List<Player> playerList) throws PlayerNotFoundException {
		Iterator<Player> itr = playerList.listIterator();
		int flag = 0;
		while(itr.hasNext()) {
			Player p = itr.next();
			if(p.getName().equalsIgnoreCase(name)) {
				flag = 1;
				break;
			}
		}
		if(flag == 0) {
			throw new PlayerNotFoundException("Player not found!");
		}
	}
}
