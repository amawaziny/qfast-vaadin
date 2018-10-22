/*
 * Copyright 2018 QFast
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.qfast.vaadin.ui.utilities;

import com.vaadin.ui.UI;
import io.qfast.vaadin.ui.Constants;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * @author Mawaziny
 */
public final class Localization {

    public static String getCaption(String key, Object... params) {
        UI ui = UI.getCurrent();
        Locale locale = ui != null ? ui.getLocale() : Locale.US;
        return MessageFormat.format(getBundleMessage(Constants.BASE_NAME, key, (locale == null) ? Locale.US : locale), params);
    }

    private static String getBundleMessage(String baseName, String key, Locale locale) {
        return ResourceBundle.getBundle(baseName, locale).getString(key);
    }
}
