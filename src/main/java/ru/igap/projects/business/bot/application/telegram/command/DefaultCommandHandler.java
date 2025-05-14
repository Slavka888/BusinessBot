package ru.igap.projects.business.bot.application.telegram.command;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.igap.projects.business.bot.application.telegram.CommandHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultCommandHandler implements CommandHandler {
    Map<String, Command> commandMap;

    @Autowired
    public DefaultCommandHandler(List<Command> commands){
        this.commandMap = new HashMap<>();
        commands.forEach(command ->{commandMap.put(command.getType().getName(), command);});
    }

    @Override
    public void handle(Update update) {
        String commandName = update.getMessage().getText().trim().strip().toLowerCase().split(" ")[0];
        log.debug("[{}] получена команда {}", update.getUpdateId(), commandName);
        var command= commandMap.get(commandName);
        if (command!=null){
            command.apply(update);
        }
        else {
            long userId = update.getMessage().getFrom().getId();
            String userName = update.getMessage().getFrom().getUserName();
            log.warn("[{}] Неизвестная команда {} от пользователя {} с id = {}", update.getUpdateId(), commandName, userName, userId);
        }
    }
}
