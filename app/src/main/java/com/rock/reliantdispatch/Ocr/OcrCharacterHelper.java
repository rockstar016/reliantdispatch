/*
 * Copyright 2011 Robert Theis
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
package com.rock.reliantdispatch.Ocr;

import android.content.SharedPreferences;

/**
 * Helper class to enable language-specific character blacklists/whitelists.
 */
public class OcrCharacterHelper {
  
  private OcrCharacterHelper() {} // Private constructor to enforce noninstantiability
  
  public static String getDefaultBlacklist(String languageCode) {
    return "";
  }
  
  public static String getDefaultWhitelist(String languageCode) {
    return "ABCDEFGHJKLMNPRSTUVWXYZabcdefghjklmnprstuvwxyz0123456789";
  }

  public static String getBlacklist(SharedPreferences prefs, String languageCode) {
    return getDefaultBlacklist(languageCode);
  }
  
  public static String getWhitelist(SharedPreferences prefs, String languageCode) {
    return getDefaultBlacklist(languageCode);
  }
}
