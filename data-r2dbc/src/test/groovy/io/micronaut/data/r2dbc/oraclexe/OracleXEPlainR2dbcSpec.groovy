/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.data.r2dbc.oraclexe

import groovy.transform.Memoized
import io.micronaut.data.r2dbc.PlainR2dbcSpec
import io.micronaut.data.tck.repositories.AuthorRepository
import spock.lang.IgnoreIf

import java.util.stream.Collectors

class OracleXEPlainR2dbcSpec extends PlainR2dbcSpec implements OracleXETestPropertyProvider {

    @Memoized
    @Override
    AuthorRepository getAuthorRepository() {
        return context.getBean(OracleXEAuthorRepository)
    }

    @Override
    String getInsertQuery() {
        return 'INSERT INTO author (id, name) VALUES (\"AUTHOR_SEQ\".nextval, ?)'
    }

    @Override
    protected String getSelectByIdQuery() {
        return 'SELECT * FROM author WHERE id=?'
    }

    @Override
    protected String correctOutput(String output) {
        // Investigate
        return output.lines().filter(l -> !l.contains("Operator called default onErrorDropped") && !l.contains("Operator has been terminated")).collect(Collectors.joining(System.lineSeparator()))
    }
}
