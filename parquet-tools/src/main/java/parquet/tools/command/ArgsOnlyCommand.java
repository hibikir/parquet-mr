/**
 * Copyright 2013 ARRIS, Inc.
 *
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
 */
package parquet.tools.command;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.UnrecognizedOptionException;

public abstract class ArgsOnlyCommand implements Command {
  private final int min;
  private final int max;

  public ArgsOnlyCommand(int min, int max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public Options getOptions() {
    return null;
  }

  @Override
  public boolean supportsExtraArgs() {
    return true;
  }

  @Override
  public void execute(CommandLine options) throws Exception {
    String[] args = options.getArgs();
    if (args.length < min) {
      throw new MissingArgumentException("missing required arguments");
    }

    if (args.length > max) {
      throw new UnrecognizedOptionException("unknown extra argument \"" + args[max] + "\"");
    }
  }
}
