package business;

import dataaccess.Auth;

import java.util.List;

public interface ControllerInterface {
	public Auth login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
 }
