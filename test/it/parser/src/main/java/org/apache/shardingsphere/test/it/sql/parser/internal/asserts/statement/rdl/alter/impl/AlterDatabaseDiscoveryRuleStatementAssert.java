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

package org.apache.shardingsphere.test.it.sql.parser.internal.asserts.statement.rdl.alter.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.dbdiscovery.distsql.parser.segment.DatabaseDiscoveryDefinitionSegment;
import org.apache.shardingsphere.dbdiscovery.distsql.parser.statement.AlterDatabaseDiscoveryRuleStatement;
import org.apache.shardingsphere.test.it.sql.parser.internal.asserts.SQLCaseAssertContext;
import org.apache.shardingsphere.test.it.sql.parser.internal.asserts.segment.distsql.AlgorithmAssert;
import org.apache.shardingsphere.test.it.sql.parser.internal.asserts.segment.distsql.PropertiesAssert;
import org.apache.shardingsphere.test.it.sql.parser.internal.cases.parser.jaxb.segment.impl.distsql.rdl.ExpectedDatabaseDiscoveryDefinitionRule;
import org.apache.shardingsphere.test.it.sql.parser.internal.cases.parser.jaxb.statement.rdl.rule.dbdiscovery.AlterDatabaseDiscoveryDefinitionRuleStatementTestCase;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Alter database discovery rule statement assert.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AlterDatabaseDiscoveryRuleStatementAssert {
    
    /**
     * Assert alter database discovery rule statement is correct with expected parser result.
     *
     * @param assertContext assert context
     * @param actual actual alter database discovery rule statement
     * @param expected expected alter database discovery rule statement test case
     */
    public static void assertIs(final SQLCaseAssertContext assertContext, final AlterDatabaseDiscoveryRuleStatement actual, final AlterDatabaseDiscoveryDefinitionRuleStatementTestCase expected) {
        if (null == expected) {
            assertNull(assertContext.getText("Actual statement should not exist."), actual);
        } else {
            Collection<DatabaseDiscoveryDefinitionSegment> actualDBDiscoveryRule = actual.getRules().stream().map(each -> (DatabaseDiscoveryDefinitionSegment) each).collect(Collectors.toList());
            assertNotNull(assertContext.getText("Actual statement should exist."), actual);
            assertDatabaseDiscoveryRules(assertContext, actualDBDiscoveryRule, expected.getRules());
        }
    }
    
    private static void assertDatabaseDiscoveryRules(final SQLCaseAssertContext assertContext, final Collection<DatabaseDiscoveryDefinitionSegment> actual,
                                                     final List<ExpectedDatabaseDiscoveryDefinitionRule> expected) {
        assertThat(assertContext.getText(String.format("Actual database discovery rule size should be %s , but it was %s", expected.size(),
                actual.size())), actual.size(), is(expected.size()));
        int count = 0;
        for (DatabaseDiscoveryDefinitionSegment each : actual) {
            assertDiscoveryDefinitionRule(assertContext, each, expected.get(count));
            count++;
        }
    }
    
    private static void assertDiscoveryDefinitionRule(final SQLCaseAssertContext assertContext, final DatabaseDiscoveryDefinitionSegment actual,
                                                      final ExpectedDatabaseDiscoveryDefinitionRule expected) {
        assertThat(actual.getName(), is(expected.getName()));
        assertThat(actual.getDataSources(), is(expected.getDataSources()));
        PropertiesAssert.assertIs(assertContext, actual.getDiscoveryHeartbeat(), expected.getProperties());
        AlgorithmAssert.assertIs(assertContext, actual.getDiscoveryType(), expected.getDiscoveryType());
    }
}
