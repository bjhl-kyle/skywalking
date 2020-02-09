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
 *
 */

package org.apache.skywalking.oap.server.core.profile;

import java.io.InputStream;
import org.apache.skywalking.oap.server.core.profile.bean.ProfileStackAnalyze;
import org.apache.skywalking.oap.server.core.profile.bean.ProfileStackAnalyzeHolder;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

public class ProfileAnalyzerTest {

    @Test
    public void testAnalyze() {
        ProfileStackAnalyzeHolder holder = loadYaml("thread-snapshot.yml", ProfileStackAnalyzeHolder.class);

        for (ProfileStackAnalyze analyze : holder.getList()) {
            analyze.analyzeAndAssert();
        }
    }

    private <T> T loadYaml(String file, Class<T> cls) {
        InputStream expectedInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
        return new Yaml().loadAs(expectedInputStream, cls);
    }

}
