/**
 * Copyright 2012 Twitter, Inc.
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
package parquet.column;

import java.util.Arrays;

import parquet.schema.PrimitiveType.PrimitiveTypeName;

/**
 * Describes a column
 *
 * @author Julien Le Dem
 *
 */
public class ColumnDescriptor implements Comparable<ColumnDescriptor> {

  private final String[] path;
  private final PrimitiveTypeName type;
  private final int maxRep;
  private final int maxDef;

  /**
   *
   * @param path the path to the leaf field in the schema
   * @param type the type of the field
   * @param maxRep the maximum repetition level for that path
   * @param maxDef the maximum definition level for that path
   */
  public ColumnDescriptor(String[] path, PrimitiveTypeName type, int maxRep, int maxDef) {
    super();
    this.path = path;
    this.type = type;
    this.maxRep = maxRep;
    this.maxDef = maxDef;
  }

  /**
   *
   * @return the path to the leaf field in the schema
   */
  public String[] getPath() {
    return path;
  }

  /**
   *
   * @return the maximum repetition level for that path
   */
  public int getRepetitionLevel() {
    return maxRep;
  }

  /**
   *
   * @return  the maximum definition level for that path
   */
  public int getDefinitionLevel() {
    return maxDef;
  }

  /**
   *
   * @return the type of that column
   */
  public PrimitiveTypeName getType() {
    return type;
  }

  /**
   *
   * {@inheritDoc}
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return Arrays.hashCode(path);
  }

  /**
   *
   * {@inheritDoc}
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    return Arrays.equals(path, ((ColumnDescriptor)obj).path);
  }

  /**
   *
   * {@inheritDoc}
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(ColumnDescriptor o) {
    for (int i = 0; i < path.length; i++) {
      int compareTo = path[i].compareTo(o.path[i]);
      if (compareTo != 0) {
        return compareTo;
      }
    }
    return 0;
  }
  /**
   *
   * {@inheritDoc}
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return Arrays.toString(path)+" "+type;
  }
}