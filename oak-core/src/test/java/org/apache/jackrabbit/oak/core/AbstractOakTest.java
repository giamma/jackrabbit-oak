/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jackrabbit.oak.core;

import org.apache.jackrabbit.mk.api.MicroKernel;
import org.apache.jackrabbit.mk.core.MicroKernelImpl;
import org.apache.jackrabbit.oak.api.CoreValueFactory;
import org.apache.jackrabbit.oak.kernel.KernelNodeStore;
import org.apache.jackrabbit.oak.spi.commit.CommitHook;
import org.apache.jackrabbit.oak.spi.commit.EmptyCommitHook;
import org.apache.jackrabbit.oak.spi.state.NodeState;
import org.apache.jackrabbit.oak.spi.state.NodeStore;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AbstractOakTest...
 */
public abstract class AbstractOakTest {

    /**
     * logger instance
     */
    private static final Logger log = LoggerFactory.getLogger(AbstractOakTest.class);

    // TODO: use regular oak-repo setup
    protected NodeStore store;
    protected CoreValueFactory valueFactory;

    protected NodeState state;

    @Before
    public void setUp() {
        MicroKernel microKernel = new MicroKernelImpl();
        store = new KernelNodeStore(microKernel, createCommitHook());
        valueFactory = store.getValueFactory();

        state = createInitialState(microKernel);
    }

    protected CommitHook createCommitHook() {
        return new EmptyCommitHook();
    }

    protected abstract NodeState createInitialState(MicroKernel microKernel);

}