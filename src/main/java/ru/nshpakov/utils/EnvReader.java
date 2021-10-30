package ru.nshpakov.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvReader {
    //загружаем проперти из .env файла
    public static Dotenv getEnvProperties() {
        return Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
    }
}