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

import points.domain.Point;

/**
 * This action finds the point whose id is
 * given as a request parameter and populates
 * the outging form bean with the point's
 * information.
 */
public class FindAction 
extends RegressionServiceActionSupport {

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

		// interact with model
		Point pt = getRegressionService()
		    .getPoint(id);
		List<String> colors = 
			new ArrayList<String>(
				Colors.asStrings(getRegressionService().getColors()));

		// populate form bean
		BeanUtils.setProperty(pointForm,
		  PROPERTY_X, new Double(pt.getX()));
		BeanUtils.setProperty(pointForm,
		  PROPERTY_Y, new Double(pt.getY()));
		BeanUtils.setProperty(pointForm,
		  PROPERTY_COLOR, Colors.asString(pt.getColor()));
		BeanUtils.setProperty(pointForm,
		  PROPERTY_COLORS, colors);

		return mapping.findForward(FORWARD_SUCCESS);
	}
}