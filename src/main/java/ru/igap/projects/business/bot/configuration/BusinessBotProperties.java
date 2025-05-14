package ru.igap.projects.business.bot.configuration;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ru.igap.projects.business.bot")
public record BusinessBotProperties (@NotEmpty String token,@NotEmpty String name){}
