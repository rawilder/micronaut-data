package io.micronaut.data.tck.repositories;

import io.micronaut.context.annotation.Parameter;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.JsonRepresentation;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.QueryResult;
import io.micronaut.data.model.JsonType;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.tck.entities.JsonEntity;
import io.micronaut.data.tck.entities.SampleData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface JsonEntityRepository extends CrudRepository<JsonEntity, Long> {

    @Query("SELECT json_native AS DATA FROM json_entity WHERE id = :id")
    @QueryResult(type = QueryResult.Type.JSON, jsonType = JsonType.NATIVE)
    Optional<SampleData> findJsonNativeSampleDataByEntityId(Long id);

    @Query("SELECT json_blob AS DATA FROM json_entity WHERE id = :id")
    @QueryResult(type = QueryResult.Type.JSON, jsonType = JsonType.BLOB)
    Optional<SampleData> findJsonBlobSampleDataByEntityId(Long id);

    @Query("SELECT json_string AS DATA FROM json_entity WHERE id = :id")
    @QueryResult(type = QueryResult.Type.JSON, jsonType = JsonType.STRING)
    Optional<SampleData> findJsonStringSampleDataByEntityId(Long id);

    void updateJsonStringById(@Id Long id, @JsonRepresentation(type = JsonType.BLOB) @Parameter SampleData jsonString);

    @Query("UPDATE json_entity SET json_blob = :jsonBlob WHERE id = :id")
    void updateJsonBlobById(Long id, @JsonRepresentation(type = JsonType.BLOB) SampleData jsonBlob);

    @NonNull
    @Override
    JsonEntity save(@Valid @NotNull @NonNull JsonEntity entity);
}
