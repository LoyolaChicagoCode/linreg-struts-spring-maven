package points.web;

import java.awt.Color;
import java.io.File;
import java.util.Collection;

import org.apache.struts.action.DynaActionForm;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.struts.ContextLoaderPlugIn;

import points.domain.Point;
import points.service.RegressionService;
import servletunit.struts.MockStrutsTestCase;

/**
 * A Testcase Superclass (see xUnit Patterns) for testing the Struts actions
 * that constitute the web tier against different configuration of the lower
 * tiers.
 */
public abstract class TestActions extends MockStrutsTestCase {

	private RegressionService service;

	protected abstract String getConfigFileLocation();

	@Override
	public void setUp() throws Exception {
		super.setUp();
		setContextDirectory(new File("src/main/webapp"));
		setConfigFile(getConfigFileLocation());

		// force initialization of the Struts
		// action servlet; in conjunction with
		// Spring, this causes creation and
		// population of the WebApplicationContext!
		getActionServlet().init();

		// look up any beans we need
		WebApplicationContext ctx = (WebApplicationContext) getSession()
				.getServletContext().getAttribute(
						ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX);
		service = (RegressionService) ctx.getBean("regressionService");
		// since we are not using
		// AbstractTransactionalDataSourceSpringContextTests
		// transactions are not automatically rolled back!
		service.reset();
	}

	@Override
	public void tearDown() throws Exception {
		service = null;
		super.tearDown();
	}

	protected void populateService() {
		int size = service.getResult().getPoints().size();
		service.addPoint(1, 2, Color.BLUE);
		service.addPoint(2, 3, Color.RED);
		service.addPoint(3, 4, Color.GREEN);
		service.addPoint(4, 5, Color.BLUE);
		service.addPoint(5, 6, Color.BLUE);
		assertEquals(size + 5, service.getResult().getPoints().size());
	}

	public void testAdd() {
		int size = service.getResult().getPoints().size();
		setRequestPathInfo("/add");
		actionPerform();
		verifyForward("success");
		verifyNoActionErrors();
		assertEquals(size, service.getResult().getPoints().size());
	}

	public void testAddSubmit() {
		int size = service.getResult().getPoints().size();
		setRequestPathInfo("/addSubmit");
		addRequestParameter("x", "7.7");
		addRequestParameter("y", "8.8");
		addRequestParameter("color", "red");
		actionPerform();
		verifyForward("success");
		verifyNoActionErrors();
		Collection<Point> points = service.getResult().getPoints();
		assertEquals(size + 1, points.size());
		Point p = null;
		for (Point q : points) {
			p = q;
		}
		assertEquals(7.7, p.getX());
		assertEquals(8.8, p.getY());
		assertEquals(Color.RED, p.getColor());
	}

	public void testDelete() {
		populateService();
		int size = service.getResult().getPoints().size();
		setRequestPathInfo("/delete");
		addRequestParameter("id", "4");
		actionPerform();
		verifyForward("success");
		verifyNoActionErrors();
		assertEquals(size - 1, service.getResult().getPoints().size());
	}

	public void testDeleteNonexistent() {
		populateService();
		int size = service.getResult().getPoints().size();
		assertNull(service.getPoint(44));
		setRequestPathInfo("/delete");
		addRequestParameter("id", "44");
		actionPerform();
		verifyForward("success");
		assertEquals(size, service.getResult().getPoints().size());
	}

	public void testInit() {
		populateService();
		setRequestPathInfo("/init");
		actionPerform();
		verifyNoActionErrors();
		verifyForward("success");
		assertEquals(0, service.getResult().getPoints().size());
	}

	public void testEdit() {
		populateService();
		int size = service.getResult().getPoints().size();
		setRequestPathInfo("/edit");
		addRequestParameter("id", "4");
		actionPerform();
		verifyForward("success");
		verifyNoActionErrors();
		assertEquals(size, service.getResult().getPoints().size());
		DynaActionForm form = (DynaActionForm) getActionForm();
		assertEquals(4.0, form.get("x"));
		assertEquals(5.0, form.get("y"));
		assertEquals("blue", form.get("color"));
	}

	public void testEditSubmit() {
		populateService();
		int size = service.getResult().getPoints().size();
		setRequestPathInfo("/editSubmit");
		addRequestParameter("id", "4");
		addRequestParameter("x", "7.7");
		addRequestParameter("y", "8.8");
		addRequestParameter("color", "green");
		actionPerform();
		verifyForward("success");
		verifyNoActionErrors();
		Collection<Point> points = service.getResult().getPoints();
		assertEquals(size, points.size());
		Point p = service.getPoint(4);
		assertEquals(7.7, p.getX());
		assertEquals(8.8, p.getY());
		assertEquals(Color.GREEN, p.getColor());
	}

	public void testList() throws Exception {
		populateService();
		int size = service.getResult().getPoints().size();
		setRequestPathInfo("/list");
		actionPerform();
		verifyNoActionErrors();
		verifyForward("success");
		DynaActionForm form = (DynaActionForm) getActionForm();
		Collection<?> points = (Collection<?>) form.get("points");
		assertEquals(size, service.getResult().getPoints().size());
		assertEquals(size, points.size());
	}
}
