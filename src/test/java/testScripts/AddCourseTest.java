package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.Baseclass;
import genericLibraries.IConstantPath;

public class AddCourseTest extends Baseclass {
	@Test
	public void addCourseTest() {
		SoftAssert soft =new SoftAssert();
		home.clickCoursesTab();
		home.clickCourseListLink();
		soft.assertTrue(course.getPageHeader().contains("Course List"));
		course.clickNewButton();
		jutil.pause(2000);
		soft.assertEquals(addCourse.getPageHeader(), "Add New Course");
		
		Map<String,String>map=excel.readFromExcel("Sheet1","Add Course");
		addCourse.setName(map.get("Name"));
		addCourse.selectCategory(web,map.get("Category"));
		addCourse.setPrice(map.get("Price"));
		addCourse.uploadPhoto(map.get("Photo"));
		addCourse.addDescription(web, map.get("Description"));
		addCourse.clickSave();
		
		soft.assertTrue(course.getSuccessAlertMessage().contains("Course added successfully"));
		course.deleteCourse(web,map.get("Name"));
		soft.assertTrue(course.getSuccessAlertMessage().contains("Product deleted successfully"));
		if(course.getSuccessAlertMessage().contains("Product deleted successfully"))excel.updateTestStatus("Sheet1", "Addcourse", "pass", IConstantPath.EXCEL_PATH);
		else
			excel.updateTestStatus("Sheet1","Add Course","Fail",IConstantPath.EXCEL_PATH);
		
		soft.assertAll();
	}

}
