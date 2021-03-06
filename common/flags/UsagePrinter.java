/*
 * Copyright 2018 The StartupOS Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.startupos.common.flags;

import java.io.PrintStream;
import java.util.Map;

public class UsagePrinter {
  public void printUsage(Map<String, FlagData> flags, PrintStream out) {
    for (String flagName : flags.keySet()) {
      FlagData flagData = flags.get(flagName);
      out.println(String.format("\t%s\t%s\n", flagData.getName(), flagData.getDescription()));
    }
  }
}
