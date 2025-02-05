/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.metadata.persist.node;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

// TODO Rename DatabaseMetaDataNodeTest when metadata structure adjustment completed. #25485
class NewDatabaseMetaDataNodeTest {
    
    @Test
    void assertGetMetaDataDataSourcesPath() {
        assertThat(NewDatabaseMetaDataNode.getDataSourcesPath("foo_db"), is("/metadata/foo_db/data_sources"));
    }
    
    @Test
    void assertGetMetaDataDataSourcePath() {
        assertThat(NewDatabaseMetaDataNode.getDataSourcePath("foo_db", "foo_ds", "0"), is("/metadata/foo_db/data_sources/foo_ds/versions/0"));
    }
    
    @Test
    void assertGetDatabaseRuleActiveVersionPath() {
        assertThat(NewDatabaseMetaDataNode.getDatabaseRuleActiveVersionPath("foo_db", "foo_rule", "foo_tables"), is("/metadata/foo_db/foo_rule/foo_tables/active_version"));
    }
    
    @Test
    void assertGetDatabaseRuleVersionPath() {
        assertThat(NewDatabaseMetaDataNode.getDatabaseRuleVersionPath("foo_db", "foo_rule", "foo_tables", "1"), is("/metadata/foo_db/foo_rule/foo_tables/versions/1"));
    }
}
