package io.github.camposmdev.foursouls.model.context.store;

import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Assuming server-mom is running in the background, the tests should pass without any issues.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(VertxExtension.class)
@DisplayName("MomStore Tests")
public class MomStoreTest {
	static String testHostName = "localhost";
	static int testPort = 8080;
	static String S_NIL = "";
	String testEmail = "example@email.com";
	String testUsername = "Guest";
	String testPassword = "supersecret";

	@Nested
	@Order(1)
	@DisplayName("User End Points")
	class UserPlugin {
		static MomStore store = null;
		@BeforeAll
		static void init() {
			store = new MomStore(Vertx.vertx(), testHostName, testPort);
		}

		@Disabled("Create User: Correct Result (User already exists)")
		@Test
		@Order(1)
		@DisplayName("Create User: Correct Result")
		void testRegister(Vertx v, VertxTestContext ctx) {
			/* register a user assuming credentials are not taken */
			store.registerUser(testEmail, testUsername, testPassword).onComplete(ar -> {
				if (ar.succeeded()) {
					assertNotEquals(S_NIL, ar.result().getId());
					assertNotEquals(S_NIL, ar.result().getUsername());
					assertNotNull(store.getJwt());
					store.logoutUser().onComplete(voidAsyncResult -> {
						ctx.completeNow();
					});
				} else {
					ctx.failNow(ar.cause());
				}
			});
		}

		@Test
		@Order(2)
		@DisplayName("Login User: Correct Result")
		void testLogin(Vertx v, VertxTestContext ctx) {
			/* login assuming the user already exists */
			store.loginUser(testUsername, testPassword).onComplete(ar -> {
				if (ar.succeeded()) {
					/* ensure body fields are not null */
					assertNotEquals(S_NIL, ar.result().getId());
					assertNotEquals(S_NIL, ar.result().getUsername());
					/* ensure JWT is updated */
					assertNotNull(store.getJwt());
					ctx.completeNow();
				} else {
					ctx.failNow(ar.cause());
				}
			});
		}

		@Test
		@Order(3)
		@DisplayName("Logout User: Correct Result")
		void testLogout(Vertx v, VertxTestContext ctx) {
			store.logoutUser().onComplete(ar -> {
				if (ar.succeeded()) {
					assertNull(store.getJwt());
					ctx.completeNow();
				} else {
					ctx.failNow(ar.cause());
				}
			});
		}


		@Test
		@Order(4)
		@DisplayName("Get User By ID: Correct Result")
		void testGetUserById(Vertx v, VertxTestContext ctx) {
			/* assuming the id already exists in the database */
			store.loginUser(testUsername, testPassword).onComplete(ar1 -> {
				if (ar1.succeeded()) {
					store.getUserById(ar1.result().getId()).onComplete(ar2 -> {
						if (ar2.succeeded()) {
							/* ensure body.id matches with testId */
							assertEquals(ar1.result().getId(), ar2.result().getId());
							/* ensure body fields are not null */
							assertNotEquals(S_NIL, ar2.result().getUsername());
							assertNotEquals(S_NIL, ar2.result().getRole());
							assertNotEquals(S_NIL, ar2.result().getCreatedAt());
							assertNotEquals(S_NIL, ar2.result().getUpdatedAt());
							ctx.completeNow();
						} else ctx.failNow(ar2.cause());
					});
				} else ctx.failNow(ar1.cause());
			});
		}
	}

	@Nested
	@Order(2)
	@DisplayName("Basement End Points")
	class BasementPlugin {
		/* TODO - Implement basement end point tests */
	}

	@Nested
	@Order(3)
	@DisplayName("Chest End Points")
	class ChestPlugin {
		/* TODO - Implement chest end point tests */
	}

	@Nested
	@Order(4)
	@DisplayName("Deck End Points")
	class DeckPlugin {
		/* TODO - Implement deck end point tests */
	}
}
