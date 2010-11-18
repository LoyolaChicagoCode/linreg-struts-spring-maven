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
import points.service.RegressionResult;
import points.service.RegressionServiceAware;

/**
 * This action extracts a list of points from
 * the model and populates the outgoing
 * listForm bean.
 */
public class ListAction 
extends RegressionServiceActionSupport 
implements RegressionServiceAware {

	@Override
	public ActionForward execute(
	    ActionMapping mapping,
	    ActionForm listForm,
	    HttpServletRequest request,
	    HttpServletResponse response)
	    throws Exception {

		// interact with model
		RegressionResult result = 
			getRegressionService().getResult();
		List<Point> points = 
			new ArrayList<Point>(result.getPoints());

		// populate form bean
		BeanUtils.setProperty(listForm,
		  PROPERTY_POINTS, points);
		BeanUtils.setProperty(listForm,
		  PROPERTY_SLOPE, 
		  new Double(result.getSlope()));
		BeanUtils.setProperty(listForm,
		  PROPERTY_Y_INTERCEPT, 
		  new Double(result.getYIntercept()));

		return mapping.findForward(FORWARD_SUCCESS);
	}
}