package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.Baseclass;

public class AddUserTest extends Baseclass{
	@Test
	public void addUserTest() {
		SoftAssert soft =new SoftAssert();
		home.clickUsersTab();
		soft.assertTrue(users.getPageHeader().contains("Users"));
		users.clickNewbutton();
		soft.assertEquals(addUser.pageHeader(),"Add New User");
		
		Map<String,String> map=excel.readFromExcel("Sheet1","Add User");
		addUser.setEmail(map.get("Email"));
		addUser.setPwd(map.get("Password"));
		addUser.setFirstName(map.get("Firstname"));
		addUser.setLastName(map.get("Lastname"));
		addUser.setAddress(map.get("Address"));
		addUser.setContact(map.get("Contact Info"));
		addUser.uploadPhoto(map.get("Photo"));
		addUser.clickSave();
		
		soft.assertTrue(users.getSuccessAlertMessage().contains("Success"));
		soft.assertAll();
	}

}
