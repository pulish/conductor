/*
 * Copyright 2025 Conductor Authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package io.orkes.conductor.client.model.integration;

import org.junit.jupiter.api.Test;

import io.orkes.conductor.client.util.JsonTemplateSerDeserResolverUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


//todo : missing field : private List<IntegrationDefFormField> configuration; from IntegrationDef
class TestSerDerIntegrationDef {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSerializationDeserialization() throws Exception {
        // 1. Unmarshal SERVER_JSON to SDK POJO
        String SERVER_JSON = JsonTemplateSerDeserResolverUtil.getJsonString("IntegrationDef");
        IntegrationDef integrationDef = objectMapper.readValue(SERVER_JSON, IntegrationDef.class);

        // 2. Assert that the fields are all correctly populated
        assertNotNull(integrationDef);
        assertNotNull(integrationDef.getCategory());
        assertEquals("sample_categoryLabel", integrationDef.getCategoryLabel());
        assertEquals("sample_description", integrationDef.getDescription());
        assertTrue(integrationDef.getEnabled());
        assertEquals("sample_iconName", integrationDef.getIconName());
        assertEquals("sample_name", integrationDef.getName());

        // Check list
        assertNotNull(integrationDef.getTags());
        assertEquals(1, integrationDef.getTags().size());
        assertEquals("sample_tags", integrationDef.getTags().get(0));

        assertEquals("sample_type", integrationDef.getType());

        // 3. Marshall this POJO to JSON again
        String serializedJson = objectMapper.writeValueAsString(integrationDef);

        // 4. Compare the JSONs - nothing should be lost
        assertEquals(objectMapper.readTree(SERVER_JSON), objectMapper.readTree(serializedJson));
    }
}