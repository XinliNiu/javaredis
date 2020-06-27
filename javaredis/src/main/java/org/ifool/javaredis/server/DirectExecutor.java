package org.ifool.javaredis.server;

import java.util.concurrent.Executor;

public class DirectExecutor implements Executor {

	public void execute(Runnable command) {
		command.run();
	}

}
  