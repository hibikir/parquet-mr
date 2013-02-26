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
package parquet.io.convert;

/**
 *
 * top level of the conversion.
 * returns the record converted
 *
 * @author Julien Le Dem
 *
 * @param <T>
 */
abstract public class RecordConverter<T> extends GroupConverter {

  /**
   *
   * @return the result of the conversion
   */
  abstract public T getCurrentRecord();

}
