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

package org.apache.shardingsphere.metadata.persist.service.config.database;

/**
 * TODO Rename DatabaseBasedPersistService when metadata structure adjustment completed. #25485
 * Database based persist service.
 *
 * @param <T> type of configuration
 */
public interface NewDatabaseBasedPersistService<T> {
    
    /**
     * Persist configurations.
     *
     * @param databaseName database name
     * @param configs configurations
     */
    void persist(String databaseName, T configs);
    
    /**
     * Load configurations.
     *
     * @param databaseName database name
     * @param ruleName rule name
     * @return configurations
     */
    T load(String databaseName, String ruleName);
}
