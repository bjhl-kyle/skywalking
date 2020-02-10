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

package org.apache.skywalking.oap.server.core.profile.bean;

import com.google.common.base.Splitter;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.apache.skywalking.oap.server.core.profile.analyze.ProfileStack;

@Data
public class ProfileStackData {

    private int limit;
    private List<String> snapshots;

    public List<ProfileStack> transform() {
        ArrayList<ProfileStack> result = new ArrayList<>(snapshots.size());

        for (int i = 0; i < snapshots.size(); i++) {
            ProfileStack stack = new ProfileStack();
            stack.setSequence(i);
            stack.setDumpTime(i * limit);
            stack.setStack(Splitter.on("-").splitToList(snapshots.get(i)));
            result.add(stack);
        }

        return result;
    }

}
