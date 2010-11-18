package points.web;

import static points.web.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * This action deletes the point whose id is
 * given as a request parameter.
 */
public class DeleteAction 
extends RegressionServiceActionSupport {

	@Override
	public ActionForward execute(
	    ActionMapping mapping, ActionForm form,
	    HttpServletRequest request,
	    HttpServletResponse response)
	    throws Exception {

		// obtain arguments from request
		int id = Integer.parseInt(
			request.getParameter(PARAMETER_ID));

		// interact with model
		getRegressionService().removePoint(id);

		request.setAttribute(
			ATTRIBUTE_MESSAGE_KEY, "delete.message");
		return mapping.findForward(FORWARD_SUCCESS);
	}
}