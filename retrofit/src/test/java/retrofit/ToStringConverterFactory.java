/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package retrofit;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

class ToStringConverterFactory extends Converter.Factory {
  private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");

  @Override
  public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
      Retrofit retrofit) {
    if (String.class.equals(type)) {
      return new Converter<ResponseBody, String>() {
        @Override public String convert(ResponseBody value) throws IOException {
          return value.string();
        }
      };
    }
    return null;
  }

  @Override public Converter<?, RequestBody> requestBodyConverter(Type type,
      Annotation[] annotations, Retrofit retrofit) {
    if (String.class.equals(type)) {
      return new Converter<String, RequestBody>() {
        @Override public RequestBody convert(String value) throws IOException {
          return RequestBody.create(MEDIA_TYPE, value);
        }
      };
    }
    return null;
  }
}
