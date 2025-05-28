package com.accenture.example.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.commands.ServerAddress;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.transitions.ImmutableMongod;
import de.flapdoodle.embed.mongo.transitions.Mongod;
import de.flapdoodle.embed.mongo.transitions.RunningMongodProcess;
import de.flapdoodle.embed.mongo.types.DatabaseDir;
import de.flapdoodle.reverse.TransitionWalker;
import de.flapdoodle.reverse.transitions.Start;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Embedded MongoDB singleton.
 * Add @Import(EmbeddedMongoDb.class) to Test* classes
 */
@Configuration
public class EmbeddedMongoDb implements Closeable {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";
    private static TransitionWalker.ReachedState<RunningMongodProcess> mongod;
    private static MongoClient client;
    private static MongoTemplate mongoTemplate;

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {

        if (mongoTemplate == null) {

            int port = 17027;

            //Store database in temp folder
            //More info: https://stackoverflow.com/a/1924576/3710490
            String tmpdir    = System.getProperty("java.io.tmpdir");
            File databaseDir = new File(tmpdir, "database");
            if(!databaseDir.exists()) {
                Files.createDirectory(databaseDir.toPath());
            }

            ImmutableMongod mongodWithoutAuth = Mongod.builder()
                    .net(Start.to(Net.class).initializedWith(Net.defaults().withPort(port)))
                    .databaseDir(Start.to(DatabaseDir.class).initializedWith(DatabaseDir.of(databaseDir.toPath()))).build();

            mongod                = mongodWithoutAuth.start(Version.Main.V6_0);
            ServerAddress address = mongod.current().getServerAddress();
            client = MongoClients.create(String.format(CONNECTION_STRING, address.getHost(), address.getPort()));

            mongoTemplate = new MongoTemplate(client, "test");
        }

        return mongoTemplate;
    }

    @Override
    public void close() throws IOException {

        if (mongod.current().isAlive()) {
            client.close();
            mongod.current().stop();
            mongoTemplate = null;
        }
    }
}