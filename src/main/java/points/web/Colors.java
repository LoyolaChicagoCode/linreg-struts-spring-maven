package points.web;

import java.awt.Color;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

public class Colors {

	private Colors() {
	}

	public static String asString(Color c) {
		if (Color.RED.equals(c))
			return "red";
		if (Color.GREEN.equals(c))
			return "green";
		if (Color.BLUE.equals(c))
			return "blue";
		throw new IllegalArgumentException("unknown color value");
	}

	public static Color asColor(String s) {
		if ("red".equals(s))
			return Color.RED;
		if ("green".equals(s))
			return Color.GREEN;
		if ("blue".equals(s))
			return Color.BLUE;
		throw new IllegalArgumentException("unknown color string");
	}

	@SuppressWarnings("unchecked")
	public static List<String> asStrings(Collection<Color> colors) {
		return (List<String>) CollectionUtils.collect(colors,
				new Transformer() {
					public Object transform(Object input) {
						return Colors.asString((Color) input);
					}
				});
	}
}
