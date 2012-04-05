/*
 * Copyright (C) 2011, the original authors
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.jclouds.karaf.commands.compute.completer;

import org.apache.karaf.shell.console.Completer;
import org.apache.karaf.shell.console.completer.StringsCompleter;
import org.jclouds.compute.ComputeService;

import java.util.List;

public class ComputeProviderCompleter implements Completer {

    private final StringsCompleter delegate = new StringsCompleter();
    private List<? extends ComputeService> computeServices;

    @Override
    public int complete(String buffer, int cursor, List<String> candidates) {
        try {
            if (computeServices != null) {
                for (ComputeService computeService : computeServices) {
                    delegate.getStrings().add(computeService.getContext().getProviderSpecificContext().getId());
                }
            }
        } catch (Exception ex) {
            //noop
        }
        return delegate.complete(buffer,cursor,candidates);
    }

    public List<? extends ComputeService> getComputeServices() {
        return computeServices;
    }

    public void setComputeServices(List<? extends ComputeService> computeServices) {
        this.computeServices = computeServices;
    }
}