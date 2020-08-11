package connected.city.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import connected.city.svc.ConnectedCitySvc;

@RestController
public class ConnectedCity {

	@Autowired
	private ConnectedCitySvc connectedCitySvc;

	@GetMapping("/connected")
	public ResponseEntity<String> cityConnection(String origin, String destination) {
		return new ResponseEntity<String>(connectedCitySvc.checkConnection(origin, destination), HttpStatus.OK);
	}
}
