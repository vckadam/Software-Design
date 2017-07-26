package com.vckadam.oopdesign.taxitrip.dao.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.vckadam.oopdesign.taxitrip.model.User;

public class UserDaoImpl implements UserDao{
	
	Map<Integer,User> userMap;
	private static final String fileName = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\taxitrip\\dao\\user\\userdata";
	
	public UserDaoImpl() throws IOException {
		this.userMap = new HashMap<Integer,User>();
		loadMap();
	}

	@Override
	public User getUserById(int id) {
		if(this.userMap.containsKey(id)) {
			return this.userMap.get(id);
		}
		return null;
	}
	
	private void loadMap() throws IOException {
		
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		for(String line = null; (line = br.readLine()) != null ; ) {
			String[] strA = line.substring(1,line.length()-2).split("\\|");
			User user = new User(
					Integer.valueOf(strA[0].trim()),
					strA[1].trim().equals("Yes")?true:false,
					strA[2].trim());
			this.userMap.put(user.getId(), user);
		}
	}
	
}


