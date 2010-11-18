package points.web;

import static points.web.Constants.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * This action looks up the valid color
 * choices in the model and puts them in the
 * outgoing form bean.
 */
public class NewAction 
extends RegressionServiceActionSupport {

	@Override
	public ActionForward execute(
	    ActionMapping mapping,
	    ActionForm pointForm,
	    HttpServletRequest request,
	    HttpServletResponse response)
	    throws Exception {

		// interact with model
		List<String> colors = 
			new ArrayList<String>(
				Colors.asStrings(getRegressionService().getColors()));

		// populate form bean
		BeanUtils.setProperty(pointForm,
		  PROPERTY_COLORS, colors);

		return mapping.findForward(FORWARD_SUCCESS);
	}
}