package example.repository;

import example.domain.Usr;
import example.domain.view.UsrView;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.QueryResult;
import io.micronaut.data.annotation.TypeDef;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.DataType;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.Optional;

@JdbcRepository(dialect = Dialect.ORACLE)
public interface UsrRepository extends PageableRepository<Usr, Long> {

    @Query("SELECT uv.* FROM usr_view uv WHERE uv.DATA.usrId=:usrId")
    @QueryResult(type = QueryResult.Type.JSON)
    Optional<UsrView> findByUsrId(Long usrId);

    @Query("UPDATE USR_VIEW uv SET uv.data = :data WHERE uv.data.usrId = :usrId")
    void update(@TypeDef(type = DataType.JSON) UsrView data, Long usrId);

    @Query("INSERT INTO USR_VIEW VALUES (:data)")
    void insert(@TypeDef(type = DataType.JSON) @Parameter("data") UsrView usrView);
}