package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.Baseclass;
import genericLibraries.IConstantPath;

public class AddCategoryTest extends Baseclass{
	@Test
	public void addCategoryTest() {
		SoftAssert soft = new SoftAssert();
		home.clickCoursesTab();
		home.clickCategoryLink();
		soft.assertTrue(category.getPageHeader().contains("Category"));
		category.clickNewButton();
		jutil.pause(2000);
		soft.assertEquals(addCategory.getPageHeader(),"Add New Category");
		
		Map<String ,String>map =excel.readFromExcel("Sheet1","Add category");
		addCategory.setName(map.get("name"));
		addCategory.clickSave();
		
		soft.assertTrue(course.getSuccessAlertMessage().contains("Category added successfully"));
		category.deleteCategory(web,map.get("name"));
		soft.assertTrue(category.getSuccessAlertMessage().contains("Category deleted successfully"));
		if(category.getSuccessAlertMessage().contains("Category deleted successfully"))
			excel.updateTestStatus("Sheet1", "Add category", "pass", IConstantPath.EXCEL_PATH);
		else
			excel.updateTestStatus("Sheet1", "Add category", "Fail", IConstantPath.EXCEL_PATH);
		soft.assertAll();
	}

}
