//package points.jwebunit;
//
//import net.sourceforge.jwebunit.WebTestCase;
//
//public class TestAcceptance 
//extends WebTestCase {
//
//	public void setUp() throws Exception {
//		super.setUp();
//		getTestContext().setBaseUrl(
//			"http://localhost:8080/LinearRegression");
//		getTestContext().setResourceBundleName(
//			"points/web/ApplicationResources");
//	}
//
//	public void tearDown() throws Exception {
//		super.tearDown();
//	}
//
//	public void testMainPage() {
//		// start at the default page
//		beginAt("/");
//		assertKeyPresent("index.title");
//	}
//	
//	public void testInit() {
//		// start at the default page
//		beginAt("/");
//		assertKeyPresent("index.title");
//		// follow the "Display results" link
//		clickLinkWithText(
//			getMessage("navigation.link.init"));
//		assertKeyPresent("list.noentries");
//	}
//
//	public void testAddPoint() {
//		// start at the default page
//		beginAt("/");
//		assertKeyPresent("index.heading");
//		clickLinkWithText(
//			getMessage("navigation.link.init"));
//		assertKeyPresent("list.noentries");
//		// follow the "Add point" link
//		clickLinkWithText(
//			getMessage("navigation.link.add"));
//		assertKeyPresent("add.heading");
//		// fill out and submit the form
//		setTextField("x", "7.7");
//		setTextField("y", "8.8");
//		selectOption("color", "red");
//		submit();
//		// ensure that the point was added
//		assertKeyPresent("add.message");
//		assertTablePresent("pointsTable");
//		assertTextInTable("pointsTable", 
//			new String[] { 
//				"1", "7.7", "8.8", "red" });
//	}
//}
