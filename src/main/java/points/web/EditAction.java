package points.web;

import static points.web.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * This action edits a user according to the
 * information in the incoming form bean.
 */

public class EditAction extends
    RegressionServiceActionSupport {

	@Override
	public ActionForward execute(
	    ActionMapping mapping,
	    ActionForm pointForm,
	    HttpServletRequest request,
	    HttpServletResponse response)
	    throws Exception {

		// obtain arguments from form bean
		int id = Integer.parseInt(BeanUtils
		  .getProperty(pointForm, PROPERTY_ID));
		double x = Double.parseDouble(BeanUtils
		  .getProperty(pointForm, PROPERTY_X));
		double y = Double.parseDouble(BeanUtils
		  .getProperty(pointForm, PROPERTY_Y));
		String color = BeanUtils.getProperty(
		  pointForm, PROPERTY_COLOR);

		// interact with model
		getRegressionService()
			.editPoint(id, x, y, Colors.asColor(color));

		request.setAttribute(
			ATTRIBUTE_MESSAGE_KEY, "edit.message");
		return mapping.findForward(FORWARD_SUCCESS);
	}
}