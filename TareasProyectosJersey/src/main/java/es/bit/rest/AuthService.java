package es.bit.rest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import es.bit.models.StatusMessage;
import es.bit.models.Usuario;
import es.bit.persistence.UsuariosManager;

@Path("/authenticate")
public class AuthService extends ServiceUtility {

	private static Logger logger = Logger.getLogger("JSONService");

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateCredentials(
			@HeaderParam("email") String email,
			@HeaderParam("password") String password) throws JsonGenerationException, JsonMappingException, IOException {
		
		if (email == null) {
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.PRECONDITION_FAILED.getStatusCode());
			statusMessage.setMessage("Username value is missing!!!");
			return Response.status(Status.PRECONDITION_FAILED.getStatusCode()).entity(statusMessage).build();
		}

		if (password == null) {
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.PRECONDITION_FAILED.getStatusCode());
			statusMessage.setMessage("Password value is missing!!!");
			return Response.status(Status.PRECONDITION_FAILED.getStatusCode()).entity(statusMessage).build();
		}

		Usuario user = null;
		
		try {			
			user = UsuariosManager.getInstance().authUsuario(email, password);		
			logger.log(Level.INFO, "user:" + user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (user == null) {
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMessage.setMessage("Access Denied for this functionality !!!");
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}

		RsaJsonWebKey senderJwk = (RsaJsonWebKey) jwkList.get(0);

		senderJwk.setKeyId("1");
		logger.info("JWK (1) ===> " + senderJwk.toJson());

		// Create the Claims, which will be the content of the JWT
		JwtClaims claims = new JwtClaims();
		claims.setIssuer("bit.es"); // who creates the token and signs it
		claims.setExpirationTimeMinutesInTheFuture(10); // token will expire (10
														// minutes from now)
		claims.setGeneratedJwtId(); // a unique identifier for the token
		claims.setIssuedAtToNow(); // when the token was issued/created (now)
		claims.setNotBeforeMinutesInThePast(2); // time before which the token
												// is not yet valid (2 minutes
												// ago)
		claims.setSubject(user.getEmail()); // the subject/principal is whom
											// the token is about
		claims.setStringListClaim("roles", "client"); //
		// multi-valued claims for roles
		JsonWebSignature jws = new JsonWebSignature();

		jws.setPayload(claims.toJson());

		jws.setKeyIdHeaderValue(senderJwk.getKeyId());
		jws.setKey(senderJwk.getPrivateKey());

		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

		String jwt = null;
		try {
			jwt = jws.getCompactSerialization();
		} catch (JoseException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(new StatusMessage(200,jwt)).build();
	}
}
