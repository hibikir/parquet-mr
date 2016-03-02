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
import org.apache.hadoop.fs.Path;
import parquet.hadoop.ParquetReader;
import parquet.tools.Main;
import parquet.tools.read.SimpleReadSupport;
import parquet.tools.read.SimpleRecord;

import java.io.PrintWriter;

public class CountCommand extends ArgsOnlyCommand {
  public static final String[] USAGE = new String[] {
    "<input>",
    "where <input> is the parquet file to print to stdout"
  };

  public CountCommand() {
    super(1, 100);
  }

  @Override
  public String[] getUsageDescription() {
    return USAGE;
  }

  @Override
  public void execute(CommandLine options) throws Exception {
    super.execute(options);

    String[] args = options.getArgs();
    int count = 0;
    PrintWriter writer = new PrintWriter(Main.out, true);
    for (String arg : args) {
      int thisCount = doTheThing(arg);
      writer.println(arg +" "+thisCount);
      count += thisCount;
    }
    writer.println(""+count);
  }

  public int doTheThing(String input) throws Exception {
    int count = 0;
    ParquetReader<SimpleRecord> reader = null;
    try {
      reader = new ParquetReader<SimpleRecord>(new Path(input), new SimpleReadSupport());
      for (SimpleRecord value = reader.read(); value != null; value = reader.read()) {
         count++;
      }
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (Exception ex) {
        }
      }
    }
    return count;
  }

}
