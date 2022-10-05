package io.micronaut.data.azure

import io.micronaut.test.support.TestPropertyProvider
import org.testcontainers.containers.CosmosDBEmulatorContainer
import org.testcontainers.utility.DockerImageName

import java.nio.file.Files
import java.nio.file.Path
import java.security.KeyStore

trait AzureCosmosTestProperties implements TestPropertyProvider {

    @Override
    Map<String, String> getProperties() {
        CosmosDBEmulatorContainer emulator = new CosmosDBEmulatorContainer(
                DockerImageName.parse("mcr.microsoft.com/cosmosdb/linux/azure-cosmos-emulator:latest")
        )
        emulator.start()
        Path keyStoreFile = Files.createTempFile("azure-cosmos-emulator", ".keystore")
        KeyStore keyStore = emulator.buildNewKeyStore()
        keyStore.store(new FileOutputStream(keyStoreFile.toFile()), emulator.getEmulatorKey().toCharArray())

        System.setProperty("javax.net.ssl.trustStore", keyStoreFile.toString())
        System.setProperty("javax.net.ssl.trustStorePassword", emulator.getEmulatorKey())
        System.setProperty("javax.net.ssl.trustStoreType", "PKCS12")

        return [
                'azure.cosmos.default-gateway-mode'               : 'true',
                'azure.cosmos.endpoint-discovery-enabled'         : 'false',
                'azure.cosmos.endpoint'                           : emulator.getEmulatorEndpoint(),
                'azure.cosmos.key'                                : emulator.getEmulatorKey(),
                'azure.cosmos.database.throughput-settings.request-units'  : '1000',
                'azure.cosmos.database.throughput-settings.auto-scale'     : 'true',
                'azure.cosmos.database.database-name'             : 'mydb',
                'azure.cosmos.database.packages'                  : 'io.micronaut.data.azure.entities',
                'azure.cosmos.database.update-policy'             : 'CREATE_IF_NOT_EXISTS',
                'azure.cosmos.database.container-settings[0].container-name' : 'family',
                'azure.cosmos.database.container-settings[0].partition-key-path' : '/lastName',
                'azure.cosmos.database.container-settings[0].throughput-settings.request-units' : '1000',
                'azure.cosmos.database.container-settings[0].throughput-settings.auto-scale' : 'false',
                'azure.cosmos.database.container-settings[1].container-name' : 'cosmos_book',
                'azure.cosmos.database.container-settings[1].partition-key-path' : '/id',
                'azure.cosmos.database.container-settings[1].throughput-settings.request-units' : '1200',
                'azure.cosmos.database.container-settings[1].throughput-settings.auto-scale' : 'false',
        ]
    }
}