package com.codigo.aplios.sdk.core;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.MapBinder;

public class ReusableModule implements Module {

	public static MapBinder<String, Command> contributeCommands(final Binder binder) {

		return MapBinder.newMapBinder(binder, String.class, Command.class);
	}

	@Override
	public void configure(final Binder binder) {

		// we don't have any commands in this module,
		// but still need to call 'contribute*' once to ensure
		// that an empty map is always available for injection.
		ReusableModule.contributeCommands(binder);
	}

	@Provides
	@Singleton
	CommandExecutor provideCommandExecutor(final Map<String, Command> commandMap) {

		return name -> commandMap.get(name)
				.exec();
	}

}

class AppModule1 implements Module {

	@Override
	public void configure(final Binder binder) {

		ReusableModule.contributeCommands(binder)
				.addBinding("a")
				.to(CommandA.class);
	}

}

class AppModule2 implements Module {

	@Override
	public void configure(final Binder binder) {

		ReusableModule.contributeCommands(binder)
				.addBinding("b")
				.to(CommandB.class);
	}

}

class CommandA implements Command {

	@Override
	public String exec() {

		return "a_result";
	}

}

class CommandB implements Command {

	@Override
	public String exec() {

		return "b_result";
	}

}

class MyModule implements Module {

	@Provides
	@Singleton
	CommandExecutor provideCommandExecutor(final CommandA ca, final CommandB cb) {

		final Map<String, Command> commands = new HashMap<>();
		commands.put("a", ca);
		commands.put("b", cb);
		return commandName -> commands.get(commandName)
				.exec();
	}

	@Override
	public void configure(final Binder arg0) {
		// TODO Auto-generated method stub

	}

}

interface Command {

	String exec();

}

interface CommandExecutor {

	String exec(String commandName);

}
