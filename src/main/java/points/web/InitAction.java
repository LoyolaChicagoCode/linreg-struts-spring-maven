package points.web;

import static points.web.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * This action (re)creates and initializes the
 * persistent model.
 */
public class InitAction 
extends RegressionServiceActionSupport {

	@Override
	public ActionForward execute(
	    ActionMapping mapping,
	    ActionForm listForm,
	    HttpServletRequest request,
	    HttpServletResponse response)
	    throws Exception {

		// interact with model
		getRegressionService().reset();

		return mapping.findForward(FORWARD_SUCCESS);
	}
}