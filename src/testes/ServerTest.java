package testes;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.jupiter.api.Test;

import server.SERVER_INFO;

class ServerTest {

	@Test
	void testServerOpened() throws NumberFormatException, IOException {
		ServerSocket serverSocket = new ServerSocket(Integer.parseInt(SERVER_INFO.PORT.get()));
		assertNotNull(serverSocket);
	}

}
