package points.web;

public class TestActionsInMemory extends TestActions {

	@Override
	protected String getConfigFileLocation() {
		return "/WEB-INF/struts/struts-config.xml, /WEB-INF/struts/struts-config-spring-test-inmemory.xml";
	}
}
