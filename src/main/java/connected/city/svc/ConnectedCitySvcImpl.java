package connected.city.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import connected.city.Routes;

@Service
public class ConnectedCitySvcImpl implements ConnectedCitySvc {

	private static final String YES = "yes";
	private static final String NO = "no";

	@Autowired
	private Routes routes;

	public String checkConnection(String origin, String destination) {
		if (routes.getCityRoutes().isRouteAvailable(origin.trim().toLowerCase(),
				destination.trim().toLowerCase())) {
			return YES;
		} else {
			return NO;
		}
	}

}
